package com.example.composeeffecthandlers.derived_state_of

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

// DerivedStateOf is a way to cache a value instead of recomputing it every time it is needed.

@Composable
fun DerivedStateOfDemo() {
    var counter by remember { mutableStateOf(0) }

    // every time counter changes, counterText is recomputed (placeholder for complex action)
    val counterText = "Counter: $counter"

    // First time called, counterText2 is computed and cached. Subsequent calls return cached value.
    val counterText2 = derivedStateOf { "Counter: $counter" }

    Button(onClick = {counter++}) {
        Text(counterText)
    }
}