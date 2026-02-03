package com.example.shopshere.data.repository

import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

///*
//Firebase Authentication - handles login/signup
//Firestore Database - stores user data (NoSQL, schema-less)
//Converts Firebase Tasks to suspend functions for coroutines
//*/
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.tasks.await
//
///*
//Repository Pattern: Acts as single source of truth for authentication operations
//Separates business logic from UI (ViewModel calls this, not Firebase directly)
//*/
//class AuthRepository{
//    /*
//    Firebase Auth instance (singleton pattern - one instance for entire app)
//    */
//    private val auth=FirebaseAuth.getInstance()
//
//    /*
//    Firestore instance (singleton) - NoSQL database
//    Schema-less: No need to pre-define tables/collections, they auto-create when referenced
//    */
//    private val fireStore=FirebaseFirestore.getInstance()
//
//    /*
//    Getter-only property that returns currently logged-in user
//    Returns null if no user is logged in
//    Recalculated every time you access it (not stored)
//    */
//    val currentUser get()=auth.currentUser
//
//
//    /*
//    LOGIN FUNCTION
//    suspend keyword: This function runs in a coroutine (can pause/resume)
//    await(): Converts Firebase Task<AuthResult> to suspend function
//    Throws exception if login fails (wrong password, user not found, etc.)
//    */
//    suspend fun login(email:String,password:String){
//        auth.signInWithEmailAndPassword(email,password).await()
//    }
//
//
//    /*
//    REGISTER FUNCTION
//    Creates new user in Firebase Auth AND stores additional data in Firestore
//    Firebase Auth only stores email/password, so we use Firestore for name, role, etc.
//    Role: "buyer" or "seller" - determines app permissions/features
//    */
//    suspend fun register(
//        name:String,
//        email:String,
//        password: String,
//        role: String
//    ){
//        /*
//        STEP 1: Create user account in Firebase Authentication
//        */
//        val result=auth.createUserWithEmailAndPassword(email,password).await()
//
//        /*
//        STEP 2: Extract auto-generated UID (Unique User ID)
//        Firebase automatically generates a unique UID for each user
//        !! operator: Asserts result.user is not null (risky - app crashes if null)
//        Better approach: result.user?.uid ?: throw Exception("User creation failed")
//        */
//        val uid=result.user!!.uid
//
//        /*
//        STEP 3: Save user info to Firestore database
//        Collection "users" will auto-create if it doesn't exist (NoSQL feature)
//        Document ID = UID (makes it easy to find user data: users/{uid})
//        */
//        fireStore.collection("users")
//            .document(uid)
//            .set(
//                mapOf(
//                    "name" to name,
//                    "email" to email,
//                    "role" to role
//                )
//            ).await()
//
//        /*
//        ALTERNATIVES TO ABOVE CODE:
//
//        Option 1: Using data class (RECOMMENDED - type-safe, cleaner)
//        data class User(val name: String, val email: String, val role: String)
//        fireStore.collection("users").document(uid).set(User(name, email, role)).await()
//
//        Option 2: Using hashMapOf instead of mapOf
//        fireStore.collection("users").document(uid).set(hashMapOf("name" to name, "email" to email, "role" to role)).await()
//
//        Option 3: Using set with merge (merges with existing data instead of overwriting)
//        fireStore.collection("users").document(uid).set(mapOf(...), SetOptions.merge()).await()
//
//        Option 4: Using update (only updates specified fields, fails if document doesn't exist)
//        fireStore.collection("users").document(uid).update("name", name, "email", email, "role", role).await()
//
//        WHY set()?
//        - Creates document if doesn't exist
//        - Overwrites entire document if exists
//        - Use update() if you only want to modify specific fields
//        - Use merge() if you want to combine with existing data
//        */
//    }
//
//
//    /*
//    LOGOUT FUNCTION
//    Not suspend because signOut() is synchronous (instant, no network call)
//    Clears authentication session - user will need to login again
//    */
//    fun logout(){
//        auth.signOut()
//    }
//}



class AuthRepository{
    private val auth=FirebaseAuth.getInstance()

    private val fireStore= FirebaseFirestore.getInstance()

    val currentUser get()=auth.currentUser

    //Email validation function
    private fun  isValidEmail(email:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //send otp to email
    suspend fun sentOtpToEmail(
        name:String,
        email:String,
        password:String,
        role: String
    ){
        if(name.isBlank())throw Exception("Name cannot be empty")
        if(!isValidEmail(email))throw Exception("Invalid email format")
        if(password.length<6)throw Exception("Password must be at least 6 characters long")
        if(role!="buyer" && role!="seller")throw Exception("Invalid role")


        val result=auth.createUserWithEmailAndPassword(email,password).await()

        //sending verification email with otp link
        result.user?.sendEmailVerification()?.await()
            ?:throw Exception("Failed to send the verification email")

        //store use data as unverified
        val uid=result.user!!.uid
        fireStore.collection("users")
            .document(uid)
            .set(mapOf(
                "name" to name,
                "email" to email,
                "role" to role,
                "emailVerified" to false,
                "createdAt" to System.currentTimeMillis()

            )).await()

        //sign out user(they must verify email before logging in)

    }

    //step 2-->Verify the otp and complete registration
    suspend fun verifyEmailAndLogin(email:String,password: String){

        //login with credentials
        auth.signInWithEmailAndPassword(email,password).await()

        //reload user to get the latest verification status
        currentUser?.reload()?.await()

        //check if email is verified or not
        if(currentUser?.isEmailVerified==true){
            //update firestore to mark as verified
            fireStore.collection("users")
                .document(currentUser!!.uid)
                .update("emailVerified",true)
                .await()
        }else{
            auth.signOut()
            throw Exception("Please verify your email first. Check your inbox")
        }
    }

    //resending verification email
    suspend fun resendVerificationEmail(){
        currentUser?.sendEmailVerification()?.await()
            ?:throw Exception("No user logged in")
    }

    //login (only for the verified users)
    suspend fun login(email:String,password: String){
        if(!isValidEmail(email))throw Exception("Invalid email format")
        if(password.isBlank())throw Exception("Password cannot be empty")

        auth.signInWithEmailAndPassword(email,password).await()

        //reload to get the latest verification status
        currentUser?.reload()?.await()

        //check email verification
        if(currentUser?.isEmailVerified==false){
            auth.signOut()
            throw Exception("Email not verified. Get that verified first")
        }
    }

    fun logout(){
        auth.signOut()
    }
}