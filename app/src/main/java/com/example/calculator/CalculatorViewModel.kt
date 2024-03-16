package com.example.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel: ViewModel() {

    private var _calculatorUiState: MutableStateFlow<CalculatorState> = MutableStateFlow(
        CalculatorState()
    )
    val calculatorUiState: StateFlow<CalculatorState> = _calculatorUiState

    fun handleCalculatorEvents(calculatorEvent: CalculatorEvent) {
        when(calculatorEvent) {
            CalculatorEvent.Calculate -> calculate()
            is CalculatorEvent.CalculatorOperation -> handleCalculatorOperation(calculatorEvent.operation)
            CalculatorEvent.Clear -> clear()
            CalculatorEvent.Decimal -> handleDecimal()
            CalculatorEvent.Delete -> handleDelete()
            is CalculatorEvent.Number -> handleNumberEvent(calculatorEvent.number)
        }
    }

    private fun calculate() {
        with(_calculatorUiState.value) {
            if (operation == null) return

            val firstNumber = firstNumber.toDoubleOrNull()
            val secondNumber = secondNumber.toDoubleOrNull()

            if (firstNumber != null && secondNumber != null) {
                val evaluation = when(operation) {
                    Operation.Add -> {
                        firstNumber + secondNumber
                    }
                    Operation.Divide -> {
                        firstNumber / secondNumber
                    }
                    Operation.Multiply -> firstNumber * secondNumber
                    Operation.Subtract -> firstNumber - secondNumber
                    null -> return
                }

                _calculatorUiState.value = CalculatorState(
                    firstNumber = "$evaluation".take(7),
                    evaluation = "$evaluation".take(7),
                )
            }
        }

    }

    private fun handleDecimal() {
        with(_calculatorUiState.value) {
            if (secondNumber.isNotEmpty() && !secondNumber.contains(".")) {
                _calculatorUiState.update {
                    copy(secondNumber = "$secondNumber.")
                }
                return
            }

            if (operation != null) {
                if (secondNumber.isEmpty()) _calculatorUiState.update {
                    copy(secondNumber = "0.")
                }
                return
            }

            if (!firstNumber.contains(".")) {
                _calculatorUiState.update {
                    copy(firstNumber = "$firstNumber.")
                }
            }
        }
    }

    private fun clear() {
        _calculatorUiState.value = CalculatorState()
    }

    private fun handleDelete() {
        _calculatorUiState.update {
            with(it) {
                operation?.let {
                    if (secondNumber.isNotEmpty()) {
                        copy(secondNumber = secondNumber.dropLast(1))
                    } else copy(operation = null)
                } ?: copy(firstNumber = firstNumber.dropLast(1))
            }
        }
    }

    private fun handleCalculatorOperation(operation: Operation) {
        _calculatorUiState.update {
            if (it.firstNumber.isNotEmpty()) {
                it.copy(operation = operation)
            } else {it}
        }
    }

    private fun handleNumberEvent(number: String) {
        _calculatorUiState.update {
            with(it) {
                operation?.let { _ ->
                    if (secondNumber.length > MAX_NUMBER_DIGITS) return
                    copy(secondNumber = secondNumber + number)
                } ?: run {
                    if (firstNumber.length > MAX_NUMBER_DIGITS) return

                    when (firstNumber) {
                        evaluation -> copy(firstNumber = number)
                        else -> copy(firstNumber = firstNumber + number)
                    }
                }
            }
        }
    }

    companion object {
        const val MAX_NUMBER_DIGITS = 7
    }
}