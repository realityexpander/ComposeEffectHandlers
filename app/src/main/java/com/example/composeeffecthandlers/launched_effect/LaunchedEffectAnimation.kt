package com.example.composeeffecthandlers.launched_effect

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun LaunchedEffectAnimation(
    counter: Int
) {
    val animatable = remember { Animatable(0f) }

    LaunchedEffect(key1 = counter) {
        animatable.animateTo(counter.toFloat())  // this coroutine gets cancelled when counter changes
    }
}