package com.example.composeeffecthandlers.disposable_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

// Use this to cleanup resources when the component is disposed

@Composable
fun DisposableEffectDemo() {
    val lifecycleOwner = LocalLifecycleOwner.current

    // ** DON'T DO THIS **
    // This `observer` needs to be cleaned up when the component is destroyed
//    val observer = LifecycleEventObserver { _, event ->
//        if (event == Lifecycle.Event.ON_PAUSE) {
//            println("DisposableEffectDemo: ON_PAUSE")
//        }
//    }

    // ** DO THIS **
    DisposableEffect(key1 = lifecycleOwner) {
        // This `observer` *WILL BE* cleaned up when the component is destroyed
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                println("DisposableEffectDemo: ON_PAUSE")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        // This is the cleanup code for the `observer`
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}