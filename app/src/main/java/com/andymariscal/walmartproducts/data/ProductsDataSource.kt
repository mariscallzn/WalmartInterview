package com.andymariscal.walmartproducts.data

import com.andymariscal.walmartproducts.model.ProductsList
import com.andymariscal.walmartproducts.network.WalmartApi
import com.andymariscal.walmartproducts.util.NetworkUtils
import io.reactivex.Observable
import javax.inject.Inject

interface ProductsDataSource {
    fun getObservableProducts(pageNumber: Int, pageSize: Int): Observable<ProductsList>
}

class ApiProductsDataSource @Inject constructor(
    private val api: WalmartApi,
    private val networkUtils: NetworkUtils
) : ProductsDataSource {
    override fun getObservableProducts(pageNumber: Int, pageSize: Int): Observable<ProductsList> {
        if(!networkUtils.hasNetworkConnection()) {
            return Observable.create { emitter ->
                emitter.onError(Exception("Network not connected"))
            }
        }
        return api.getProductsList(pageNumber = pageNumber, pageSize = pageSize)
    }

}