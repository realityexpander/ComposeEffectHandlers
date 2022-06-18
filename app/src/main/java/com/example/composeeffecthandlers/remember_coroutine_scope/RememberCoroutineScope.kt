package com.example.composeeffecthandlers.remember_coroutine_scope

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScope() {
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch { // *IMPORTANT* only use the scope for callback functions, used for independent coroutines
                       // Better to use the viewModel scope for this
        delay(1000L)
            println("Hello from coroutine scope")
    } }) {

    }
}