package com.example.to_do

import androidx.compose.foundation.background
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ActionIcon(
    onClick:()->Unit,
    backgrooundColor: Color,
    icon:ImageVector,
    contentDescription: String?=null,
    modifier: Modifier= Modifier,
    tint:Color=Color.White
){
    IconButton(onClick = onClick,
        modifier=Modifier
            .background(backgrooundColor)
    ) {

        Icon(imageVector = icon,
            contentDescription = contentDescription,
            tint = tint)
}

}