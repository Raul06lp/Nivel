package com.example.pmdm.nivel.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EffectsDemo() {
    var counter by remember { mutableIntStateOf(0) }

    suspend fun timer() {
        while (true){
            delay(1000L)
            counter++
        }
    }

    println("EFFECTDEMO")

    Scaffold {
        println("SCAFFOLD")
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            //CounterDisplay(counter)

            CircleDisplay(counter)

            Text("$counter", fontSize = 128.sp)

            Button(onClick = { counter++ }) {
                Text("Incrementar")
            }

            val scope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                timer()
            }

            var job by remember { mutableStateOf<Job?>(null) }

            Button(onClick = {
                job?.cancel()
                job = scope.launch{
                    timer()
                }
            }) {
                Text("Start")
            }


            var checked by remember {mutableStateOf(false)}

            Switch(
                checked = checked,
                onCheckedChange = {checked = !checked}
            )
        }
    }


}

@Composable
fun CircleDisplay(counter: Int){
    println("CIRCLE DISPLAY, counter = $counter")
    val color = remember(counter) {
        println("COLOR")
        if (counter % 5 == 0) Color.Green else Color.Red
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(3f)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun CounterDisplay(counter: Int) {
    Text("$counter", fontSize = 128.sp)
}

@Preview
@Composable
fun EffectsDemoPreview() {
    EffectsDemo()
}