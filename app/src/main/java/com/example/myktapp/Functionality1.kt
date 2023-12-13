package com.example.myktapp

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*

class Functionality1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Functionality1", fontSize = 18.sp)
            }

            LazyRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item { playAudio(this@Functionality1, R.raw.sound1) }
                item { playAudio(this@Functionality1, R.raw.sound2) }
                item { playAudio(this@Functionality1, R.raw.sound3) }
            }

        }
    }
}


@Composable
fun playAudio(context: Context, soundId: Int){
    val mp: MediaPlayer = MediaPlayer.create(context, soundId)

    val isPlaying = remember { mutableStateOf(false) }
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_music),
            contentDescription = "",
            modifier = Modifier
                .height(160.dp)
                .width(160.dp)
                .padding(32.dp)
                .background(Color.Blue)
                .clickable {
                    if (isPlaying.value) {
                        mp.pause()
                    } else {
                        mp.start()
                    }
                    isPlaying.value = !isPlaying.value
                }
        )

    }
}

