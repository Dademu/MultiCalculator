package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //GreetingView(Greeting().greet())
                }
            }
        }
    }
}

fun main() {
    do {
        println("Simple Calculator")

        print("Enter first number: ")
        val left = readlnOrNull()?.toDoubleOrNull() ?: 0.0

        print("Enter operator (+, -, *, /): ")
        val operator = readlnOrNull()

        print("Enter second number: ")
        val right = readlnOrNull()?.toDoubleOrNull() ?: 0.0

        val result = when (operator) {
            "+" -> left + right
            "-" -> left - right
            "*" -> left * right
            "/" -> if (right != 0.0) left / right else "Error: Division by zero"
            else -> "Invalid operator"
        }

        println("Result: $result")

        print("Do you want to perform another calculation? (yes/no): ")
    }
        while (readlnOrNull()?.lowercase(Locale.getDefault()) == "yes")
}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(
        text = display.value,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(5.dp),
        style = TextStyle(fontSize = 24.sp),
    )
}

@Composable
fun CalcRow(startNum: Int, numButtons: Int, onPress: (Int) -> Unit) {
        val endNum = startNum + numButtons

        Row(modifier = Modifier.padding(0.dp)) {
            for (i in startNum until endNum) {
                CalcNumericButton(onPress)
            }
        }
    }

@Composable
fun CalcNumericButton(onPress: (Int) -> Unit) {
    Button(
        onClick = { "{$onPress.value} + {number}"},
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "$onPress")
    }
}

@Composable
fun CalcEqualsButton(onPress: () -> Unit) {
    Button(
        onClick = onPress,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}

@Composable
fun CalcOperationButton(onPress: (String) -> Unit) {
    Button(
        onClick = {onPress("")},
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "onPress")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
       // GreetingView("Hello, Android!")
    }
}

@Preview
@Composable
fun PreviewCalcView() {
    CalcView()
}

@Composable
fun CalcView() {
    val displayText = remember { mutableStateOf("0") }
    var leftNumber by rememberSaveable { mutableIntStateOf(0) }
    var rightNumber by rememberSaveable { mutableIntStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }

    if (complete && operation!= null) {
        var answer = 0
        when (operation) {
            "+" -> {answer = leftNumber + rightNumber
                    displayText.value = answer.toString()}
            "-" -> {answer = leftNumber - rightNumber
                    displayText.value = answer.toString()}
            "*" -> {answer= leftNumber * rightNumber
                    displayText.value = answer.toString()}
            "/" -> {answer = leftNumber / rightNumber
                    displayText.value = answer.toString()}
            else -> {""}
        }
        //displayText = answer.toString()
    } else if (operation.isNotEmpty() && !complete) {
        displayText.value = rightNumber.toString()
    } else {
        displayText.value = leftNumber.toString() // Set displayText to the leftNumber value
    }

    fun numberPress(btnNum: Int) {
        if (complete) {
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
        }
        if (operation.isNotBlank() && !complete) {
            rightNumber = rightNumber * 10 + btnNum
        } else if (operation.isBlank() && !complete) {
            leftNumber = leftNumber * 10 + btnNum
        }// Implement logic for number button press
    }

    fun operationPress(op: String) {
        if (!complete) {
            operation = op
        }
    }

    fun equalsPress() {
        complete = true// Implement logic for equals button press
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            CalcDisplay(displayText)
        }
        Row(modifier = Modifier.fillMaxSize()) {
            Column {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(startNum = 1,
                            numButtons = 3,
                            onPress = { number: Int ->
                            numberPress(number) // Calling the numberPress function with the number parameter
                    })
                Row {
                    CalcNumericButton(onPress = { number: Int -> numberPress(number)})
                    CalcEqualsButton(onPress = { equalsPress() })
                }
            }
            Column {
                CalcOperationButton(onPress = { op:String -> operationPress(op)})
                //CalcOperationButton(displayText, "-")
               // CalcOperationButton(displayText, "*" )
                //CalcOperationButton(displayText, "/" )
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}
}
