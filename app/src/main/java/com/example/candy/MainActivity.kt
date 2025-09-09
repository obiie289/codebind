package com.example.candy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.candy.ui.theme.CandyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CandyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "World",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello World",
        modifier = modifier
            .border(2.dp, Color.Green)
            .padding(8.dp)


    )
}

@Composable
fun MainScreen(){
    Scaffold (modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent,contentColor = Color.Unspecified){ innerPadding ->
        Text(
            text = "Hello Compose",
            modifier = Modifier
                .padding(innerPadding)
                .border(width = 12.dp, Color.Red)
                .padding(12.dp)
                .background(Color.Green)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CandyTheme {
        Greeting("Android")
    }
}