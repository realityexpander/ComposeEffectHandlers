package com.example.composeeffecthandlers.launched_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collect

@Composable
fun LaunchedEffectFlowScreen(
    viewModel: LaunchedEffectViewModel
) {
    LaunchedEffect(key1 = true) {  // key1=true means its only called once
        viewModel.sharedFlow.collect { event ->
            when(event) {
                is ScreenEvents.ShowSnackbar -> {
                    viewModel.showSnackbar(event.message)
                }
                is ScreenEvents.Navigate -> {
                    viewModel.navigate(event.route)
                }
            }
        }
    }
}