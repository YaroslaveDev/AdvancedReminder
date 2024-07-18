package com.pfv.advancedreminder.ui.anim

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun PulsatingAnimation(
    pulsarColor: Color,
    nbPulsar: Int = 2,
    fab: @Composable (Modifier) -> Unit = {}
) {

    var fabSize by remember { mutableStateOf(IntSize(0, 0)) }
    val animationCanvasSize = LocalDensity.current.run { fabSize.width.toDp() }
    val pulsarRadius = LocalDensity.current.run { 12.dp.toPx() }
    val effects: List<Pair<Float, Float>> = List(nbPulsar) {
        pulsarBuilder(pulsarRadius = pulsarRadius, size = fabSize.width, it * 500)
    }

    Box(contentAlignment = Alignment.Center) {


        Canvas(modifier = Modifier.requiredSize(animationCanvasSize), onDraw = {
            for (i in 0 until nbPulsar) {
                val (radius, alpha) = effects[i]

                drawRoundRect(
                    color = pulsarColor,
                    size = Size(radius * 2, radius * 2),
                    cornerRadius = CornerRadius(pulsarRadius, pulsarRadius),
                    alpha = alpha,
                    topLeft = Offset(-radius, -radius)
                )
            }
        })

        fab(
            Modifier
                .padding(pulsarRadius.dp)
                .onGloballyPositioned {
                    if (it.isAttached) {
                        fabSize = it.size
                    }
                }
        )
    }
}