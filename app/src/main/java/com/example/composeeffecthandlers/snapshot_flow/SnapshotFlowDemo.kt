package com.example.composeeffecthandlers.snapshot_flow

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

// similar to flow.collectAsState() but opposite

// A snapshotFlow converts a Compose State to a flow that can be collected by a coroutine

@Composable
fun SnapshotFlowDemo() {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = scaffoldState) {
        snapshotFlow { scaffoldState.snackbarHostState }
            .mapNotNull { it.currentSnackbarData?.message }
            .distinctUntilChanged()
            .collect { message ->
                println("A snackbar with message $message was shown")
            }
    }
}