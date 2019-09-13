package com.andymariscal.walmartproducts.data

import com.andymariscal.walmartproducts.model.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

interface ProductsRepository {
    fun getProducts(pageNumber: Int, pageSize: Int): Observable<List<Product>>
}

@Singleton
open class DefaultProductsRepository @Inject constructor(
    private val productsDataSource: ProductsDataSource
) : ProductsRepository {

//    private var inMemoryCache: MutableList<Product>? = null

    override fun getProducts(pageNumber: Int, pageSize: Int): Observable<List<Product>> {

//        inMemoryCache?.let {
//            Timber.d("Andres Returning cached data")
//            if ((pageNumber * pageSize) > it.size) return@let
//            return Observable.create { emitter ->
//                emitter.onNext(it)
//                emitter.onComplete()
//            }
//        }

        Timber.d("Fetching remote data")
        return Observable.create { emitter ->
            emitter.setDisposable(
                productsDataSource.getObservableProducts(pageNumber, pageSize)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ productsList ->
                        if (productsList.statusCode == HttpURLConnection.HTTP_OK) {
//                            inMemoryCache?.addAll(productsList.products)
//                                ?: mutableListOf<Product>().also {
//                                    it.addAll(productsList.products)
//                                    inMemoryCache = it
//                                }
                            emitter.onNext(productsList.products)
                        } else {
                            throw Throwable("Server error, status code = ${productsList.statusCode}")
                        }
                    }, {
                        emitter.onError(it)
                    }, {
                        emitter.onComplete()
                    })
            )
        }
    }
}
