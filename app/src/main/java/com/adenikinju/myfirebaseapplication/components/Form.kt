package com.adenikinju.myfirebaseapplication.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.adenikinju.myfirebaseapplication.R


val annotedString = buildAnnotatedString {
    append("By Signing in, you agree to our ")
    pushStyle(SpanStyle(color = Color.Gray))
    pop()
    pushStyle(SpanStyle(color = Color.Blue))
    append("Terms & Conditions ")
    pop()
    pushStyle(SpanStyle(color = Color.Gray))
    append("and ")
    pop()
    pushStyle(SpanStyle(color = Color.Blue))
    append("Privacy Policy")
}

@Composable
fun Form(
    modifier: Modifier,
    func: () -> Unit,
    getEmailInput: (String) -> Unit,
    email: String,
    password: String,
    newPassword: String?,
    getPasswordInput: (String) -> Unit,
    signingFacebook: () -> Unit,
    getNewPasswordInput: ((String) -> Unit)?,
    navHostController: NavHostController,
    formName: String
) {
    Surface {

    }
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
    )
    {
        Column(
            modifier = Modifier
                .background(Color(253, 253, 253))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(vertical = 50.dp),
                painter = painterResource(id = R.drawable.login),
                contentDescription = "login",
                contentScale = ContentScale.FillWidth
            )
        }
        Column(modifier = Modifier.padding(all = 20.dp)) {
            Spacer(Modifier.padding(vertical = 10.dp))
            Text(
                fontWeight = FontWeight(900),
                text = formName,
                fontSize = 35.sp
            )
            Spacer(Modifier.padding(vertical = 10.dp))
            CustomInput(
                icon = Icons.Default.Info,
                email,
                onvaluechange = getEmailInput,
                placeHolder = "Email ID"
            )
            CustomInput(
                icon = Icons.Default.AccountCircle,
                password,
                onvaluechange = getPasswordInput,
                placeHolder = "Password"
            )
            if(formName == "Register"){
                if (getNewPasswordInput != null) {
                    CustomInput(
                        icon = Icons.Default.AccountCircle,
                        newPassword!!,
                        onvaluechange = getNewPasswordInput,
                        placeHolder = "Re-Type Password"
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 17.dp))
            Text(
                text = annotedString,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(vertical = 17.dp))
            Button(
                func,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(55.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(1, 101, 254))
                // colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            ) {
                Text(
                    text = formName,
                    fontSize = 19.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                text = if(formName == "Register") "Already have an Account? Login Here"
                else "Don't have an account? Register Here"
                ,
                fontSize = 14.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
                    .clickable {
                        if(formName == "Register"){
                            navHostController.navigate("main")
                        }else{
                            navHostController.navigate("register")
                        }
                    }
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Or",
                    color = Color.Gray,
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(1f),
                onClick = signingFacebook,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    fontSize = 19.sp,
                    text = "Github",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Button(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(1f),
                onClick = signingFacebook,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(
                    fontSize = 19.sp,
                    text = "Google",
                    color = Color.White
                )
            }

        }

    }
}