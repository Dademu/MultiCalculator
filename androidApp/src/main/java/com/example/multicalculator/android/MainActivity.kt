package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multicalculator.Greeting
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
                    GreetingView(Greeting().greet())
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
fun CalcView() {

}

@Composable
fun CalcRow() {

}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(
        text = display.value,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(5.dp),
        style = TextStyle(fontSize = 20.sp)
    )
}

@Composable
fun CalcNumeric(display: MutableState<String>) {
    Button(
        onClick = {
            display.value = "0"
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}
@Composable
fun CalcOperation(display: MutableState<String>) {
    Button(
        onClick = {
            display.value = "0"
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}

@Composable
fun CalcEqualsButton(display: MutableState<String>) {
    Button(
        onClick = {
            display.value = "0"
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=")
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

