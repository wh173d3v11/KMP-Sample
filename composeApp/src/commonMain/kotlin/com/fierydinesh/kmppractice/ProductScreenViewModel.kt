package com.fierydinesh.kmppractice

import androidx.lifecycle.viewModelScope
import com.fierydinesh.kmppractice.model.ProductX
import com.fierydinesh.kmppractice.repo.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductScreenViewModel(private val productRepository: ProductRepository = ProductRepository()) :
    androidx.lifecycle.ViewModel() {
    private val _products = MutableStateFlow(listOf<ProductX>())
    val products = _products.asStateFlow()

    var selectedProduct: ProductX? = null

    init {
        viewModelScope.launch {
            productRepository.getProducts().collect { products ->
                _products.update { it + products }
            }
        }
    }

}