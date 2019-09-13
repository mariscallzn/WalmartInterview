package com.andymariscal.walmartproducts.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andymariscal.walmartproducts.domain.ProductsListParameters
import com.andymariscal.walmartproducts.domain.ProductsListUseCase
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.result.Result
import com.andymariscal.walmartproducts.util.map
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    productsListUseCase: ProductsListUseCase
): ViewModel() {

    val productsListLiveData: LiveData<List<Product>>

    init {
        val observableProductsList = productsListUseCase.observe()
        productsListUseCase.execute(ProductsListParameters(1))
        productsListLiveData = observableProductsList.map {
             (it as? Result.Success)?.data ?: emptyList()
        }
    }
}