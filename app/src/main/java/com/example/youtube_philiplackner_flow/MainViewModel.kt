package com.example.youtube_philiplackner_flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    val countdownFlow = flow<Int> {
        val startValue = 10
        var currentValue = startValue

        emit(startValue)

        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }

    }.flowOn(dispatchers.main)

//    private val _stateFlow = MutableStateFlow(0)
//    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun squareNumber(number: Int) {
        viewModelScope.launch(dispatchers.main)  {
            _sharedFlow.emit(number * number)
        }
    }

//    fun incrementCounter() {
//        _stateFlow.value += 1
//    }

    init {

        squareNumber(3)

        viewModelScope.launch(dispatchers.main)  {
            sharedFlow.collect {
                println("First Flow : Received number is $it")
            }
        }

        viewModelScope.launch(dispatchers.main) {
            sharedFlow.collect {
                println("Second Flow : Received number is $it")
            }
        }


    }

//    private fun collectFlow() {
//        val flow = flow {
//            delay(250L)
//            emit("Appetizer")
//            delay(1000L)
//            emit("Main dish")
//            delay(100L)
//            emit("Dessert")
//        }

//        viewModelScope.launch(dispatchers.main)  {
//            flow.onEach {
//                println("Flow : $it is delivered")
//            }
//                .conflate()
//                .collect {
//                    println("Flow : Now eating $it")
//                    delay(1500L)
//                    println("Flow : Finished eating $it")
//                }
//        }


//        viewModelScope.launch {
//            val reduceResult = countdownFlow
//                .filter { time ->
//                    time % 2 == 0
//                }
//                .map { time ->
//                    time * time
//                }
//                .onEach {time ->
//                    println(time)
//                }
//                .fold(100) { accumulator, value ->
//                    accumulator + value
//                }
//                .reduce { accumulator, value ->
//                    accumulator + value
//                }
//                .count {
//                    it % 2 == 0
//                }
//
//            println("The result is $reduceResult")
//        }
//    }

}