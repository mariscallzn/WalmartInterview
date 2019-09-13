package com.andymariscal.walmartproducts.ui.products

import androidx.lifecycle.*
import com.andymariscal.walmartproducts.domain.ProductsListParameters
import com.andymariscal.walmartproducts.domain.ProductsListUseCase
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.result.Event
import com.andymariscal.walmartproducts.result.Result
import com.andymariscal.walmartproducts.util.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProductsListViewModel @Inject constructor(
    private val productsListUseCase: ProductsListUseCase
) : ViewModel() {

    companion object {
        const val INITIAL_PAGE = 1
    }

    val loadProductsList: LiveData<Event<List<Product>>>

    private var _lastItemReached = MutableLiveData<Any>()
    val lastItemReached: LiveData<Any>
        get() = _lastItemReached

    private var lastItem = false
    private var currentPage = INITIAL_PAGE

    init {
        val observableProductsList = productsListUseCase.observe()
        productsListUseCase.execute(ProductsListParameters(INITIAL_PAGE))
        currentPage++

        loadProductsList = observableProductsList.map {
            when (it) {
                is Result.Success -> {
                    it.data.also { list ->
                        lastItem = list.isEmpty()
                        if (lastItem) _lastItemReached.postValue(lastItem)
                        Timber.e("Andres $lastItem")
                    }
                    Event(it.data)
                }
                is Result.Error -> {
                    Timber.e(it.exception, "Andres")
                    Event(emptyList())
                }
                else -> Event(emptyList())
            }
        }
    }

    fun loadNextPage() {
        if (!lastItem) {
            productsListUseCase.execute(ProductsListParameters(currentPage))
            Timber.e("Andres $currentPage")
        }
    }
}