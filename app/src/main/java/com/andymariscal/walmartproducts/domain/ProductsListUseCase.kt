package com.andymariscal.walmartproducts.domain

import com.andymariscal.walmartproducts.data.ProductsRepository
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.result.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class ProductsListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) : RxUseCase<ProductsListParameters, List<Product>>() {

    override fun execute(parameter: ProductsListParameters) {
        disposable = productsRepository.getProducts(parameter.pageNumber, parameter.pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ products ->
                result.postValue(Result.Success(products))
            }, {
                result.postValue(Result.Error(Exception(it)))
            }, {

            })
    }
}

data class ProductsListParameters(
    val pageNumber: Int,
    val pageSize: Int = 30
)