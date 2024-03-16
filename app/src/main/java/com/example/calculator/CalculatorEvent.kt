package com.example.calculator

sealed class CalculatorEvent {

    data object Clear: CalculatorEvent()
    data object Delete: CalculatorEvent()
    data object Calculate: CalculatorEvent()
    data object Decimal: CalculatorEvent()

    data class Number(val number: String): CalculatorEvent()
    data class CalculatorOperation(val operation: Operation): CalculatorEvent()

}

sealed class Operation(val symbol: String) {
    data object Add: Operation("+")
    data object Subtract: Operation("-")
    data object Multiply: Operation("x")
    data object Divide: Operation("/")
}