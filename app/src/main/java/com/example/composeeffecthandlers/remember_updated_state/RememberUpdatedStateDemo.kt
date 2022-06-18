package com.example.composeeffecthandlers.remember_updated_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay

// Imagine splash screen...

@Composable
fun RememberUpdatedStateDemo(
    onTimeout: () -> Unit
) {

    // ** DONT DO IT THIS WAY **
    // Will not be called again bc onTimeout is not a key, so if a new "onTimeout" is passed in, it wont run again
//    LaunchedEffect(true) {
//        delay(3000L)
//        onTimeout()
//    }

    // ** DO IT THIS WAY **

    // Checks to see if the onTimeout function has changed
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect(true) {
        delay(3000L)
        updatedOnTimeout()  // Will run if the onTimeout function has changed
    }
}