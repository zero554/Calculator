package com.example.calculator

data class CalculatorState(
    var firstNumber: String = "0",
    var operation: Operation? = null,
    var secondNumber: String = "",
    var evaluation: String = "0"
)
