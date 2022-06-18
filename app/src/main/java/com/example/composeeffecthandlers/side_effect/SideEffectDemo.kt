package com.example.composeeffecthandlers.side_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

// The "non-compose, non-state" counter like from an API that needs to be treated as state

@Composable
fun SideEffectDemo(nonComposeNonStateCounter: Int) {
    SideEffect {
        println("Called after every successful recomposition")
    }
}