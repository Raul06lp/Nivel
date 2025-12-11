package com.example.pmdm.nivel.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RectangularProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    width: Dp = 6.dp,
    height: Dp = 6.dp,
    vertical: Boolean = false,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    trackColor: Color = MaterialTheme.colorScheme.primary
) {
    if (vertical) {
        VerticalProgressIndicator(progress, modifier, width, color, trackColor)
    } else {
        HorizontalProgressIndicator(progress, modifier, height, color, trackColor)

    }

}

@Composable
fun HorizontalProgressIndicator(progress: Float, modifier: Modifier, height: Dp, color: Color, trackColor: Color) {
    Box(
       modifier = modifier
           .height(height)
           .fillMaxWidth()
           .background(color)

    ) {
        Box (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(trackColor)
        )
    }
}

@Composable
fun VerticalProgressIndicator(progress: Float, modifier: Modifier, width: Dp, color: Color, trackColor: Color) {
    Box(
        modifier = modifier
            .width(width)
            .fillMaxHeight()
            .background(color),
        contentAlignment = Alignment.BottomCenter

    ) {
        Box (
            modifier = Modifier
                .fillMaxHeight(progress)
                .fillMaxWidth()
                .background(trackColor)
        )
    }
}

class BooleanPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}

@Preview(showBackground = true)
@Composable
fun RectangularProgressIndicatorPreview(
    @PreviewParameter(BooleanPreviewProvider::class)
    vertical: Boolean
) {
    RectangularProgressIndicator(0.5f, vertical = vertical)
}