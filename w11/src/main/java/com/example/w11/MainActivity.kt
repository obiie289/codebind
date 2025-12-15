package com.example.w11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.w11.ui.ProductListScreen
import com.example.w11.ui.theme.CandyTheme // 테마 이름은 프로젝트마다 다를 수 있습니다 (빨간줄 뜨면 알트+엔터)

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