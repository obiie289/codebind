package com.example.w11.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.w11.model.Product

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            // 높이를 고정하지 않고 내용물에 맞게 늘어나도록 설정
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        // 기존 Row(가로)를 Column(세로)으로 변경!
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally // 가운데 정렬
        ) {
            // 1. 이미지 (위쪽)
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(120.dp) // 이미지 높이 고정
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 2. 텍스트 정보 (아래쪽)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                // 제목
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium, // 글자 크기 약간 줄임
                    fontWeight = FontWeight.Medium,
                    maxLines = 2, // 최대 2줄까지만 표시
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 가격
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}