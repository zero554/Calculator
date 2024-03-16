package com.example.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.Purple80

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    events: (CalculatorEvent) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            with(state) {
                Text(
                    text = firstNumber + (operation?.symbol ?: "") + secondNumber,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 80.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    maxLines = 2
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    symbol = "AC",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = { events(CalculatorEvent.Clear) }
                )
                CalculatorButton(
                    symbol = "Del",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Delete) }
                )

                val (color, textColor) = getColorPair(state = state, operation = Operation.Divide)

                CalculatorButton(
                    symbol = "/",
                    color = color,
                    textColor = textColor,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.CalculatorOperation(Operation.Divide)) }
                )
            }

            // 789 x
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    symbol = "7",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )
                CalculatorButton(
                    symbol = "8",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )
                CalculatorButton(
                    symbol = "9",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )

                val (color, textColor) = getColorPair(state = state, operation = Operation.Multiply)

                CalculatorButton(
                    symbol = "x",
                    color = color,
                    textColor = textColor,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.CalculatorOperation(Operation.Multiply)) }
                )
            }

            // 456 -
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    symbol = "4",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )
                CalculatorButton(
                    symbol = "5",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )
                CalculatorButton(
                    symbol = "6",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )

                val (color, textColor) = getColorPair(state = state, operation = Operation.Subtract)

                CalculatorButton(
                    symbol = "-",
                    color = color,
                    textColor = textColor,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.CalculatorOperation(Operation.Subtract)) }
                )
            }

            // 123 +
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    symbol = "1",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )
                CalculatorButton(
                    symbol = "2",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )
                CalculatorButton(
                    symbol = "3",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = { events(CalculatorEvent.Number(it)) }
                )

                val (color, textColor) = getColorPair(state = state, operation = Operation.Add)

                CalculatorButton(
                    symbol = "+",
                    color = color,
                    textColor = textColor,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        events(CalculatorEvent.CalculatorOperation(Operation.Add))
                    }
                )
            }

            // 0 . =
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CalculatorButton(
                    symbol = "0",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                ) { events(CalculatorEvent.Number(it)) }
                CalculatorButton(
                    symbol = ".",
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) { events(CalculatorEvent.Decimal) }
                CalculatorButton(
                    symbol = "=",
                    color = Purple80,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) { events(CalculatorEvent.Calculate) }
            }
        }
    }
}

private fun getColorPair(state: CalculatorState, operation: Operation): Pair<Color, Color> {
    return if (state.operation == operation) {
        Pair(first = Color.White, second = Purple80)
    } else {
        Pair(first = Purple80, second = Color.White)
    }
}

@Preview
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        Calculator(CalculatorState()) {}
    }
}