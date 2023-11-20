package com.example.youtube_philiplackner_flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.youtube_philiplackner_flow.ui.theme.Youtube_PhilipLackner_FlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Youtube_PhilipLackner_FlowTheme {
                val viewModel = viewModel<MainViewModel>()
//                val count = viewModel.stateFlow.collectAsState(initial = 0)
                LaunchedEffect(key1 = true) {

                    viewModel.sharedFlow.collect { number ->
                        viewModel.squareNumber(number)
                    }
                }

//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
//                    Button(
//                        onClick = { viewModel.incrementCounter() }
//                    ) {
//                        Text(
//                            text = "Counter : ${count.value}"
//                        )
//                    }
//                }
            }
        }
    }
}

