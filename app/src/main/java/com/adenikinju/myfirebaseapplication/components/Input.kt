package com.adenikinju.myfirebaseapplication.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
//import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.adenikinju.myfirebaseapplication.R


@Composable
fun CustomInput(
    icon: ImageVector,
    value: String,
    placeHolder: String,
    onvaluechange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(1f)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            "icon",
            modifier = Modifier
                .fillMaxWidth(.1f)
        )
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        TextField(
            value = value, onValueChange = onvaluechange,
            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            placeholder = { Text(text = placeHolder) },
            modifier = Modifier
                .fillMaxWidth(.9f)
        )
    }
}