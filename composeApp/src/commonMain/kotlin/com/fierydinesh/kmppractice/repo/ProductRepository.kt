package com.fierydinesh.kmppractice.repo

import com.fierydinesh.kmppractice.api.httpClient
import com.fierydinesh.kmppractice.model.ProductX
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class ProductRepository {

    private suspend fun getProductsList(): List<ProductX> {
        val resp = httpClient.get("https://fakestoreapi.com/products")
        return resp.body()
    }

    fun getProducts() = flow {
        emit(getProductsList())
    }

}