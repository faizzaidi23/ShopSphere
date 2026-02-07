package com.example.shopshere.data.repository

import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository{
    private val auth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()

    val currentUser get() = auth.currentUser

    // Email validation function
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Step 1: Send OTP to email during registration
    suspend fun sentOtpToEmail(
        name: String,
        email: String,
        password: String,
        role: String
    ) {
        // Validation
        if (name.isBlank()) throw Exception("Name cannot be empty")
        if (!isValidEmail(email)) throw Exception("Invalid email format")
        if (password.length < 6) throw Exception("Password must be at least 6 characters long")
        if (role != "buyer" && role != "seller") throw Exception("Invalid role")

        // Create user in Firebase Auth
        val result = auth.createUserWithEmailAndPassword(email, password).await()

        // Send verification email with OTP link
        result.user?.sendEmailVerification()?.await()
            ?: throw Exception("Failed to send the verification email")

        // Store user data as unverified in Firestore
        val uid = result.user!!.uid
        fireStore.collection("users")
            .document(uid)
            .set(mapOf(
                "name" to name,
                "email" to email,
                "role" to role,
                "emailVerified" to false,
                "createdAt" to System.currentTimeMillis()
            )).await()

        // IMPORTANT: Sign out user (they must verify email before logging in)
        auth.signOut()
    }

    // Login (only for verified users)
    suspend fun login(email: String, password: String) {
        // Validation
        if (!isValidEmail(email)) throw Exception("Invalid email format")
        if (password.isBlank()) throw Exception("Password cannot be empty")

        // Sign in with credentials
        auth.signInWithEmailAndPassword(email, password).await()

        // Reload to get the latest verification status
        currentUser?.reload()?.await()

        // Check email verification
        if (currentUser?.isEmailVerified == false) {
            auth.signOut()
            throw Exception("Email not verified. Please check your inbox and verify your email first.")
        }

        // Update last login time in Firestore
        currentUser?.let { user ->
            fireStore.collection("users")
                .document(user.uid)
                .update(
                    mapOf(
                        "emailVerified" to true,
                        "lastLoginTime" to System.currentTimeMillis()
                    )
                ).await()
        }
    }

    // Resend verification email (for users who didn't receive it)
    suspend fun resendVerificationEmail(email: String, password: String) {
        // Sign in temporarily to send verification email
        auth.signInWithEmailAndPassword(email, password).await()

        // Check if already verified
        currentUser?.reload()?.await()
        if (currentUser?.isEmailVerified == true) {
            auth.signOut()
            throw Exception("Email is already verified. Please login.")
        }

        // Send verification email
        currentUser?.sendEmailVerification()?.await()
            ?: throw Exception("Failed to send verification email")

        // Sign out
        auth.signOut()
    }

    // Check session validity (optional - for enhanced security)
    suspend fun checkSessionValidity(maxInactiveDays: Int = 30): Boolean {
        val user = currentUser ?: return false

        // Get last login time from Firestore
        val userDoc = fireStore.collection("users")
            .document(user.uid)
            .get()
            .await()

        val lastLogin = userDoc.getLong("lastLoginTime") ?: 0L
        val daysSinceLogin = (System.currentTimeMillis() - lastLogin) / (1000 * 60 * 60 * 24)

        return if (daysSinceLogin > maxInactiveDays) {
            logout()
            false
        } else {
            // Update last login time
            fireStore.collection("users")
                .document(user.uid)
                .update("lastLoginTime", System.currentTimeMillis())
                .await()
            true // Session is valid
        }
    }

    // Get user role from Firestore
    suspend fun getUserRole(): String? {
        val user = currentUser ?: return null
        val userDoc = fireStore.collection("users")
            .document(user.uid)
            .get()
            .await()
        return userDoc.getString("role")
    }

    // Logout
    fun logout() {
        auth.signOut()
    }
}