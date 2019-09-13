package com.andymariscal.walmartproducts.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.databinding.FragmentProductsBinding
import com.andymariscal.walmartproducts.model.LOADING_FOOTER_TYPE
import com.andymariscal.walmartproducts.model.PRODUCT_TYPE
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.result.Event
import com.andymariscal.walmartproducts.result.EventObserver
import com.andymariscal.walmartproducts.ui.MultiRowTypeAdapter
import com.andymariscal.walmartproducts.ui.products.delegate.LoadingDelegate
import com.andymariscal.walmartproducts.ui.products.delegate.ProductHandler
import com.andymariscal.walmartproducts.ui.products.delegate.ProductsListDelegate
import com.andymariscal.walmartproducts.util.viewModelProviders
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class ProductsFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    private lateinit var productViewModel: ProductsListViewModel
    private lateinit var binding: FragmentProductsBinding
    private lateinit var productsListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productViewModel = viewModelProviders(providerFactory)
        productsListAdapter = ProductListAdapter(
            MultiRowTypeAdapter.Builder()
                .addDelegateAdapter(
                    PRODUCT_TYPE,
                    ProductsListDelegate(
                        productHandler
                    )
                )
                .addDelegateAdapter(LOADING_FOOTER_TYPE, LoadingDelegate())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = productViewModel
        }

        binding.productsRecycler.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            paginationScrollListener.linearLayoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(paginationScrollListener)
            adapter = productsListAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel.loadProductsList.observe(
            viewLifecycleOwner, EventObserver { products ->
                productsListAdapter.addAll(products)
            })

        productViewModel.lastItemReached.observe(viewLifecycleOwner, Observer<Any> {
            productsListAdapter.removeLodingView()
        })
    }

    private val productHandler = object :
        ProductHandler {
        override fun onProductClicked(position: Int) {
            view?.let {
                val bundle = bundleOf(
                    "position" to position,
                    "products" to productsListAdapter.items
                )
                Navigation.findNavController(it)
                    .navigate(R.id.action_productsFragment_to_productDetailFragment, bundle)
            }
        }
    }

    private val paginationScrollListener = object :
        PaginationScrollListener() {
        override fun loadMoreItems() {
            Timber.e("Andres LoadMoreItems")
            productViewModel.loadNextPage()
        }
    }
}