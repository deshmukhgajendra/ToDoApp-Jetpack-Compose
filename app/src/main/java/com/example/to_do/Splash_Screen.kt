package com.example.to_do

import android.content.Intent
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.to_do.ui.theme.TODoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class Splash_Screen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODoTheme {
                splashscreen()

            }
        }
    }

}


@Composable
fun splashscreen(){

    val context = LocalContext.current

    // Navigate to Main Activity after delay
    runBlocking {
        delay(3000) // 3-second delay
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as ComponentActivity).finish() // Close SplashScreenActivity
    }

    // Display the GIF using Glide
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = { ctx ->
            android.widget.ImageView(ctx).apply {
                Glide.with(ctx)
                    .asGif()
                    .load("android.resource://${context.packageName}/raw/dinozo") // Replace `splash` with your GIF file name
                    .into(this)
            }
        })
    }
}


