package com.example.snakegame

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.abs

fun Modifier.swipeToControl(
    onSwipe: (Direction) -> Unit
): Modifier = this.pointerInput(Unit) {
    detectDragGestures { _, dragAmount ->
        val (x, y) = dragAmount
        when {
            abs(x) > abs(y) -> {
                if (x > 0) onSwipe(Direction.RIGHT) else onSwipe(Direction.LEFT)
            }
            else -> {
                if (y > 0) onSwipe(Direction.DOWN) else onSwipe(Direction.UP)
            }
        }
    }
} 