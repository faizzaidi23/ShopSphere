package com.example.shopshere.data.repository

/*
Firebase Authentication - handles login/signup
Firestore Database - stores user data (NoSQL, schema-less)
Converts Firebase Tasks to suspend functions for coroutines
*/
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/*
Repository Pattern: Acts as single source of truth for authentication operations
Separates business logic from UI (ViewModel calls this, not Firebase directly)
*/
class AuthRepository{
    /*
    Firebase Auth instance (singleton pattern - one instance for entire app)
    */
    private val auth=FirebaseAuth.getInstance()

    /*
    Firestore instance (singleton) - NoSQL database
    Schema-less: No need to pre-define tables/collections, they auto-create when referenced
    */
    private val fireStore=FirebaseFirestore.getInstance()

    /*
    Getter-only property that returns currently logged-in user
    Returns null if no user is logged in
    Recalculated every time you access it (not stored)
    */
    val currentUser get()=auth.currentUser


    /*
    LOGIN FUNCTION
    suspend keyword: This function runs in a coroutine (can pause/resume)
    await(): Converts Firebase Task<AuthResult> to suspend function
    Throws exception if login fails (wrong password, user not found, etc.)
    */
    suspend fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password).await()
    }


    /*
    REGISTER FUNCTION
    Creates new user in Firebase Auth AND stores additional data in Firestore
    Firebase Auth only stores email/password, so we use Firestore for name, role, etc.
    Role: "buyer" or "seller" - determines app permissions/features
    */
    suspend fun register(
        name:String,
        email:String,
        password: String,
        role: String
    ){
        /*
        STEP 1: Create user account in Firebase Authentication
        */
        val result=auth.createUserWithEmailAndPassword(email,password).await()

        /*
        STEP 2: Extract auto-generated UID (Unique User ID)
        Firebase automatically generates a unique UID for each user
        !! operator: Asserts result.user is not null (risky - app crashes if null)
        Better approach: result.user?.uid ?: throw Exception("User creation failed")
        */
        val uid=result.user!!.uid

        /*
        STEP 3: Save user info to Firestore database
        Collection "users" will auto-create if it doesn't exist (NoSQL feature)
        Document ID = UID (makes it easy to find user data: users/{uid})
        */
        fireStore.collection("users")
            .document(uid)
            .set(
                mapOf(
                    "name" to name,
                    "email" to email,
                    "role" to role
                )
            ).await()

        /*
        ALTERNATIVES TO ABOVE CODE:

        Option 1: Using data class (RECOMMENDED - type-safe, cleaner)
        data class User(val name: String, val email: String, val role: String)
        fireStore.collection("users").document(uid).set(User(name, email, role)).await()

        Option 2: Using hashMapOf instead of mapOf
        fireStore.collection("users").document(uid).set(hashMapOf("name" to name, "email" to email, "role" to role)).await()

        Option 3: Using set with merge (merges with existing data instead of overwriting)
        fireStore.collection("users").document(uid).set(mapOf(...), SetOptions.merge()).await()

        Option 4: Using update (only updates specified fields, fails if document doesn't exist)
        fireStore.collection("users").document(uid).update("name", name, "email", email, "role", role).await()

        WHY set()?
        - Creates document if doesn't exist
        - Overwrites entire document if exists
        - Use update() if you only want to modify specific fields
        - Use merge() if you want to combine with existing data
        */
    }


    /*
    LOGOUT FUNCTION
    Not suspend because signOut() is synchronous (instant, no network call)
    Clears authentication session - user will need to login again
    */
    fun logout(){
        auth.signOut()
    }
}