package com.adenikinju.myfirebaseapplication.ui.Screens.Login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adenikinju.myfirebaseapplication.MainActivity
import com.adenikinju.myfirebaseapplication.Screen.Screen
import com.adenikinju.myfirebaseapplication.components.Form
import com.adenikinju.myfirebaseapplication.ui.Screens.Register.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

lateinit var firebaseAuth: FirebaseAuth


@Composable
fun LoginScreen(
    modifier: Modifier,
    context: Context,
    navController: NavHostController,
    auth: FirebaseAuth
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val currentUser = auth.currentUser
    if (currentUser != null) {
        navController.navigate(Screen.Movies.route){
            popUpTo(Screen.Movies.route) { inclusive = true }
        }    } else {
        Column(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Form(
                modifier = Modifier,
                email = email.value,
                password = password.value,
                getPasswordInput = { password.value = it },
                getEmailInput = { email.value = it },
                func = { signInWithEmail(context = context,navController = navController, email = email.value, password = password.value, auth = auth) },
                signingFacebook = { signIntoGithub(MainActivity()) },
                navHostController = navController,
                formName = "Login",
                newPassword = null,
                getNewPasswordInput = null
            )
        }
    }
}

private fun signIntoGithub(activity: MainActivity) {
    firebaseAuth = FirebaseAuth.getInstance()
    val provider = OAuthProvider.newBuilder("github.com");
    provider.scopes = listOf("user:email")
    provider.addCustomParameter("login", "your-email@gmail.com")
    val pendingResultTask = firebaseAuth.pendingAuthResult
    if (pendingResultTask != null) {
        // There's something already here! Finish the sign-in for your user.
        pendingResultTask
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser!!
                Log.d("Tag1", firebaseUser.email.toString())

            }
    } else {
        firebaseAuth
            .startActivityForSignInWithProvider(activity, provider.build())
            .addOnSuccessListener { authResult ->
                val profile = authResult.getAdditionalUserInfo()?.getProfile()
                Log.d("Tag1", profile.toString())
            }
            .addOnFailureListener {
                it.message?.let { it1 -> Log.d("Tag", it1) }
            }
    }
        .addOnFailureListener {
            it.message?.let { it1 -> Log.d("Tag", it1) }
        }
}

private fun signInWithEmail(
    context: Context,
    navController: NavHostController,
    email: String,
    password: String,
    auth: FirebaseAuth
) {
    if (password.isEmpty() || email.isEmpty()) {
        Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_LONG).show()
        return
    }

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                navController.navigate(Screen.Movies.route){
                    popUpTo(Screen.Movies.route) { inclusive = true }
                }
            } else {
                // If sign in fails, display a message to the user.
                Log.w("TAG", "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    context,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
}