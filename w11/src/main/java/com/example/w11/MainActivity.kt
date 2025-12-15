package com.example.w11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.w11.ui.ProductListScreen
import com.example.w11.ui.theme.CandyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CandyTheme {
                ProductListScreen()
            }
        }
    }
}