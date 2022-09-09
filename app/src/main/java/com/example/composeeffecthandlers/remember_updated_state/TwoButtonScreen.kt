package com.example.composeeffecthandlers.remember_updated_state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

// RememberUpdatedState gets the latest value of a state variable even after the composition has started.

// https://proandroiddev.com/jetpack-compose-side-effects-iii-rememberupdatedstate-c8df7b90a01d

@Composable
fun TwoButtonScreen() {
    var buttonColour by remember {
        mutableStateOf("Unknown")
    }
    Column {
        Button(
            onClick = {
                buttonColour = "Red"
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red
            )
        ) {
            Text("Red Button")
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                buttonColour = "Black"
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text("Black Button")
        }

        // This will *NOT* update latest value when the button colour changes
        //Timer(buttonColor = buttonColour)

        // This *WILL* update latest value when the button colour changes by using rememberUpdatedState
        Timer2(buttonColour = buttonColour)
    }
}

@Composable
fun Timer(
    buttonColor: String
) {
    val timerDuration = 5000L

    // Keeps the buttonColor that was passed in when the composable was first called (does not update to latest)
    println("Composing timer with colour : $buttonColor")

    LaunchedEffect(key1 = Unit, block = {
        startTimer(timerDuration) {
            println("Timer ended")
            println("Last pressed button color was $buttonColor")
        }
    })
}


suspend fun startTimer(time: Long, onTimerEnd: () -> Unit) {
    delay(timeMillis = time)
    onTimerEnd()
}

@Composable
fun Timer2(
    buttonColour: String
) {
    val timerDuration = 5000L
    println("Composing timer with colour : $buttonColour")

    // Will always get the latest value of buttonColour via rememberUpdatedState, even after
    //   LaunchedEffect has started (and not cancelled due to a new value as its not possible with Unit)
    val buttonColorUpdated by rememberUpdatedState(newValue = buttonColour)

    LaunchedEffect(key1 = Unit, block = {
        startTimer(timerDuration) {
            println("Timer ended")
            println("[1] Last pressed button color is $buttonColour")
            println("[2] Last pressed button color is $buttonColorUpdated")
        }
    })
}