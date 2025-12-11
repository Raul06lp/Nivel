package com.example.pmdm.nivel.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlin.time.Duration.Companion.seconds

suspend fun square(number: Int): Int {
    delay(1000)
    return number * number
}


@SuppressLint("FlowOperatorInvokedInComposition")
@OptIn(FlowPreview::class)
@Preview
@Composable
fun DebounceDemo() {

    var number by remember {mutableStateOf("")}

    val debouncedNumber by snapshotFlow { number }
        .debounce(250)
        .collectAsState(initial = number)

    var squared by remember {mutableStateOf(0)}

    var isComputing by remember {mutableStateOf(false)}



    LaunchedEffect(debouncedNumber){
        isComputing = true
        squared = debouncedNumber.toIntOrNull()?.let { square(it) } ?: 0
        isComputing = false
    }




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("Calcular el cuadrado de un número")

        TextField(
            value = number,
            onValueChange = {
                number = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (isComputing)
            Text("Calculando...")
        else
            Text("El cuadrado es $squared")

    }
}