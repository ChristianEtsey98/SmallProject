package com.example.myktapp

import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import com.example.myktapp.ui.theme.MyKTAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class TimeCalendar : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                ShowTimeCalendarAndWeather();
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowTimeCalendarAndWeather() {
    val parisDateTime = LocalDateTime.now(ZoneId.of("Europe/Paris"))
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy HH:mm:ss")
    val formattedDateTime = parisDateTime.format(formatter)
    val parisCalendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"))
    val year = parisCalendar.get(Calendar.YEAR)
    val month = parisCalendar.get(Calendar.MONTH) + 1 // Months are zero-based
    val day = parisCalendar.get(Calendar.DAY_OF_MONTH)
    val hour = parisCalendar.get(Calendar.HOUR_OF_DAY)
    val minute = parisCalendar.get(Calendar.MINUTE)
    val second = parisCalendar.get(Calendar.SECOND)


    Text(
        text = "The current date and time in Paris is:\n$formattedDateTime\n\nThe current calendar in Paris is:\nYear: $year\nMonth: $month\nDay: $day\nHour: $hour\nMinute: $minute\nSecond: $second",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(16.dp)
            .zIndex(1f)
    )

    AndroidView(
        factory = { WebView(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            //.align(Alignment.BottomCenter)
            .zIndex(1f),
        update = { webView ->
            webView.loadUrl("https://open-meteo.com/en/docs")
        }
    )

    Image(
        painter = painterResource(id = R.drawable.eiffel_tower),
        contentDescription = "Eiffel tower",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .zIndex(-1f),
        contentScale = ContentScale.Crop
    )

}
