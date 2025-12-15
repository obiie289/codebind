package com.example.w11.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.w11.viewmodel.ProductUiState
import com.example.w11.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is ProductUiState.Loading -> {
                CircularProgressIndicator()
            }
            is ProductUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.products) { product ->
                        ProductItem(product = product)
                    }
                }
            }
            is ProductUiState.Error -> {
                Text(text = state.message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}