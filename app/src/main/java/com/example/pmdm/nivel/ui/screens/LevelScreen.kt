@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)


package com.example.pmdm.nivel.ui.screens

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm.nivel.ui.components.LevelPanel
import com.example.pmdm.nivel.ui.components.RectangularProgressIndicator


@Composable
fun LevelScreen(
    xAxis: Float = 0f,
    yAxis: Float = 0f,
    color: Color
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Nivel") }
            )
        }
    ) {innerPadding ->
7
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LevelScreenContent(
                xAxis = xAxis,
                yAxis = yAxis,
                color = color
            )
        }
    }
}

@Composable
fun LevelScreenContent(
    xAxis: Float = 0f,
    yAxis: Float = 0f,
    color: Color
){

    Column() {
        Row(
            modifier = Modifier.weight(0.9f).fillMaxSize()
        ) {
            Box(modifier = Modifier.weight(0.1f).fillMaxSize()){
                AxisYPanel(yAxis)
            }
            Box(modifier = Modifier.weight(0.9f).fillMaxSize()){
                LevelPanel(
                    color,
                    Modifier.padding(16.dp)
                )
            }
        }
        Row(
            modifier = Modifier.weight(0.1f).fillMaxSize()
        ) {
            Box(modifier = Modifier.weight(0.1f).fillMaxSize())
            Box(modifier = Modifier.weight(0.9f).fillMaxSize()){
                AxisXPanel(xAxis)
            }
        }
    }
}





@Composable
fun AxisXPanel(
    axis: Float
){
    Box(modifier = Modifier.fillMaxSize()){
        Text("Eje x", modifier = Modifier.align(Alignment.Center))
        RectangularProgressIndicator(
            progress = axis,
            height = 20.dp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(.8f)
                .clip(RoundedCornerShape(40)),
            color = Color(0xFFADADAD),
            trackColor = Color(0xFF0CC0000)
        )
    }
}

@Composable
fun AxisYPanel(
    axis: Float
){
    Box(modifier = Modifier.fillMaxSize()){
        Text("Eje y", modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(top = 30.dp)
        )
        RectangularProgressIndicator(
            progress = axis,
            vertical = true,
            width = 20.dp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight(.8f)
                .clip(RoundedCornerShape(40)),
            color = Color(0xFFADADAD),
            trackColor = Color(0xFF0CC0000)
        )
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LevelScreenPreview() {
    var xAxis by remember { mutableStateOf(0.5f) }
    var yAxis by remember { mutableStateOf(0.5f) }
    var color by remember { mutableStateOf(Color.Red) }

    val context = LocalContext.current

    val activity = LocalActivity.current

    LaunchedEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    
    DisposableEffect(Unit) {

        val sensorManager = context
            .getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val listener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

            override fun onSensorChanged(event: SensorEvent) {
                xAxis = calcValue(event.values[0])
                yAxis = calcValue(event.values[1])
            }

            fun calcValue(axis: Float): Float {
                return (axis + 9.81f) / (9.81f+9.81f)
            }
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }
    color = if (0.49f < xAxis && xAxis < 0.51f && 0.49f < yAxis && yAxis < 0.51f) Color.Green else Color.Red

    LevelScreen(
            xAxis,
            yAxis,
            color
    )
}


