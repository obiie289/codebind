package com.example.w11.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.w11.data.RetrofitInstance
import com.example.w11.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductUiState {
    object Loading : ProductUiState
    data class Success(val products: List<Product>) : ProductUiState
    data class Error(val message: String) : ProductUiState
}
// 여기 까지 ProductUiState 내용

class ProductViewModel : ViewModel() {
    // 위에서 정의한 ProductUiState를 여기서 사용
    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {

                val response = RetrofitInstance.api.getProducts()
                allProducts = response
                _uiState.value = ProductUiState.Success(response)
            } catch (e: Exception) {
                // 에러 발생 시
                _uiState.value = ProductUiState.Error("에러 발생: ${e.localizedMessage}")
            }
        }
    }
    fun updateCategory(category : String) {

        if(_uiState.value !is ProductUiState.Success) return

        if(category == "All") {
            _uiState.value = ProductUiState.Success(allProducts)
        }
        else {
            val filteredList = allProducts.filter {
                it.category == category.lowercase()
            }
            _uiState.value = ProductUiState.Success(filteredList)
        }
    }
}