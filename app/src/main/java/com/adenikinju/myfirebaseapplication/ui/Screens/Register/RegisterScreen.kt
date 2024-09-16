package com.adenikinju.myfirebaseapplication.ui.Screens.Register

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adenikinju.myfirebaseapplication.Screen.Screen
import com.adenikinju.myfirebaseapplication.components.Form
import com.google.firebase.auth.FirebaseAuth


@Composable
fun RegisterScreen(modifier: Modifier = Modifier, context: Context, navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val auth = remember { FirebaseAuth.getInstance() }

    Column(
        modifier = modifier
            .padding(bottom = 40.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Form(
            modifier = Modifier,
            email = email.value,
            password = password.value,
            getPasswordInput = { password.value = it },
            getEmailInput = { email.value = it },
            func = { registerUser(context, navController, email.value, password.value, newPassword.value, auth) },
            signingFacebook = { },
            navHostController = navController,
            formName = "Register",
            newPassword = newPassword.value,
            getNewPasswordInput = { newPassword.value = it }
        )
    }
}

fun isValidEmail(target: CharSequence): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

private fun registerUser(
    context: Context,
    navController: NavHostController,
    email: String,
    password: String,
    newPassword: String,
    auth: FirebaseAuth
) {
    if (password != newPassword) {
        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
        return
    }
    if (!isValidEmail(email)) {
        Toast.makeText(context, "Email is invalid", Toast.LENGTH_LONG).show()
        return
    }

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate(Screen.Movies.route){
                    popUpTo(Screen.Register.route) { inclusive = true }
                }
            } else {
                Log.w("TAG", "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    context,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
}