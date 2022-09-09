package com.example.composeeffecthandlers.remember_updated_state

import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

// Imagine splash screen...

@Composable
fun RememberUpdatedStateDemo(
    onTimeout: () -> Unit
) {

    // ** DON'T DO IT THIS WAY **
    // Will not be called again bc onTimeout is not a key, so if a new "onTimeout" is passed in, it wont run again
//    LaunchedEffect(true) {
//        delay(3000L)
//        onTimeout()
//    }

    // ** DO IT THIS WAY **
    // Keeps the original onTimeout lambda even after recomposition, and wont be restarted.
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)
    LaunchedEffect(true) {
        delay(1000L)
        updatedOnTimeout()  // Will run again if the onTimeout function has changed
    }
}

@Composable
fun Calculation(input: Int) {
    val rememberUpdatedStateInput by rememberUpdatedState(input)
    val rememberedInput = remember { input }

    Text("updatedInput: $rememberUpdatedStateInput, rememberedInput: $rememberedInput")
}


