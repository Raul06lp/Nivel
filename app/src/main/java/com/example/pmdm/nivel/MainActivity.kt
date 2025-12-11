package com.example.pmdm.nivel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pmdm.nivel.ui.screens.LevelScreenPreview
import com.example.pmdm.nivel.ui.theme.NivelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NivelTheme {
                LevelScreenPreview()
            }
        }
        }

}