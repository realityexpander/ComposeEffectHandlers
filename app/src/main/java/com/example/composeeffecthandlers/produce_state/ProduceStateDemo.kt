package com.example.composeeffecthandlers.produce_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

// ProduceState is a composable that produces a state.
// Similar to a flow, it can be used to represent a value that changes over time.

@Composable
fun ProduceStateDemo(countUpTo: Int): State<Int> {

    // similar to launchedEffect, we can use suspend funcs, but "compose" way to do it
    return produceState(initialValue = 0) {
        for (i in 0 until countUpTo) {
            delay(1000)
            value++
        }
    }
    
    // Very similar to:
    return flow<Int> {
        for (i in 0 until countUpTo) {
            delay(1000)
            emit(i)
        }
    }.collectAsState(initial = 0)
}