package com.example.pmdm.nivel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun LevelPanel(
    color: Color = Color.Green,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10))
            .background(color)
    )
}

class ColorPreviewProvider : PreviewParameterProvider<Color> {
    override val values: Sequence<Color>
        get() = sequenceOf(Color.Red, Color.Green)
}

@Preview
@Composable
fun levelPanelPreview(
    @PreviewParameter(ColorPreviewProvider::class)
    color: Color
) {
    LevelPanel(color = color)
}