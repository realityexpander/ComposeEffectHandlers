package com.example.composeeffecthandlers

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeeffecthandlers.launched_effect.LaunchedEffectFlowScreen
import com.example.composeeffecthandlers.launched_effect.LaunchedEffectViewModel
import com.example.composeeffecthandlers.remember_coroutine_scope.RememberCoroutineScope
import com.example.composeeffecthandlers.remember_updated_state.Calculation
import com.example.composeeffecthandlers.remember_updated_state.RememberUpdatedStateDemo
import com.example.composeeffecthandlers.remember_updated_state.TwoButtonScreen
import com.example.composeeffecthandlers.ui.theme.ComposeEffectHandlersTheme
import kotlinx.coroutines.delay

// from https://www.youtube.com/watch?v=gxWcfz3V2QE

class MainActivity : ComponentActivity() {

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var text by remember{ mutableStateOf("") }

            ComposeEffectHandlersTheme {

                // LaunchedEffectExample(text)

                // LaunchedEffectFlowScreen(viewModel = viewModel<LaunchedEffectViewModel>())

                // RememberCoroutineScope()

//                RememberUpdatedStateDemo( onTimeout = {
//                    Log.d("Test", "onCreate: onTimeout")
//                })


                //////////////////////////////////////
                // rememberUpdatedState demo
//                var myInput by remember {
//                    mutableStateOf(0)
//                }
//                OutlinedButton(
//                    onClick = {
//                        myInput++
//
//                    }
//                ) {
//                    Text("Increase $myInput")
//                }
//                Calculation(input = myInput)
                //////////////////////////////////////

                //////////////////////////////////////
                // another rememberUpdatedState demo
                TwoButtonScreen()

            }
        }
    }

    @Composable
    private fun LaunchedEffectExample(text: String) {
        var text1 = text
        LaunchedEffect(key1 = text1) {  // when text changes, we call this again
            delay(500L)
            text1 = "API: #${i}"
        }


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Button(onClick = { text1 += "#${i++}" }
            ) {
                // simulate network call - we dont want to do this because it will call
                // every render, convert to launched effect
                // i++

                Text(text = "Result: $text1 $i")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeEffectHandlersTheme {
        Greeting("Android")
    }
}