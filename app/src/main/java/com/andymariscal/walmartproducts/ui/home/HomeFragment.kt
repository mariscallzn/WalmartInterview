package com.andymariscal.walmartproducts.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.databinding.FragmentHomeBinding
import com.andymariscal.walmartproducts.model.*
import com.andymariscal.walmartproducts.ui.MainActivity
import com.andymariscal.walmartproducts.ui.MultiRowTypeAdapter
import com.andymariscal.walmartproducts.ui.ViewType
import com.andymariscal.walmartproducts.ui.home.delegate.*
import com.andymariscal.walmartproducts.util.viewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = viewModelProviders(viewModelFactory)
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        adapter = HomeAdapter(
            MultiRowTypeAdapter.Builder()
                .addDelegateAdapter(
                    PRODUCT_TYPE,
                    HomeProductsListDelegate(
                        productHandler
                    )
                )
                .addDelegateAdapter(
                    TWO_DAY_DELIVERY_TYPE,
                    TwoDayDeliveryDelegate(
                        twoDayDelivareHandler
                    )
                )
                .addDelegateAdapter(
                    POPULAR_SERVICES_TYPE,
                    PopularServicesDelegate(popularServicesHandler)
                )
                .addDelegateAdapter(FEEDBACK_TYPE, FeedbackDelegate(feedbackHandler))
        )

        binding.homeRecycler.adapter = adapter
        binding.homeRecycler.addItemDecoration(DividerItemDecoration(context, VERTICAL))

        homeViewModel.productsListLiveData.observe(
            viewLifecycleOwner,
            Observer<List<Product>> { list ->
                adapter.addAtIndex(0, HomeProductsList(list))
                binding.homeRecycler.smoothScrollToPosition(0)
            })

        adapter.add(object : ViewType {
            override fun getViewType(): Int = TWO_DAY_DELIVERY_TYPE
        })

        adapter.add(object : ViewType {
            override fun getViewType(): Int = POPULAR_SERVICES_TYPE
        })

        adapter.add(object : ViewType {
            override fun getViewType(): Int = FEEDBACK_TYPE
        })


        return binding.root
    }

    private val productHandler = object :
        HomeProductHandler {
        override fun onHomeProductClicked(products: List<Product>) {
            view?.let {

                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_productDetailFragment, bundleOf(
                        "products" to products
                    ))
            }
        }
    }

    private val twoDayDelivareHandler = object :
        StartShoppingHandler {
        override fun onStartShoppingClicked() {
            view?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_productsFragment)
            }
        }
    }

    private val popularServicesHandler = object : PopularServicesHandler {
        override fun onActionClick(popularService: PopularService) {
            view?.let {
                when (popularService) {
                    PopularService.PROFILE -> {
                        (activity as? MainActivity)?.openDrawer()
                    }
                    PopularService.SOURCE_CODE -> {
                        Navigation.findNavController(it)
                            .navigate(R.id.action_homeFragment_to_webViewActivity)
                    }
                    PopularService.WEBSITE -> {
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walmart.com")).apply {
                            startActivity(this)
                        }
                    }
                    PopularService.SHOPPING -> {
                        Navigation.findNavController(it)
                            .navigate(R.id.action_homeFragment_to_productsFragment)
                    }

                }
            }
        }
    }

    private val feedbackHandler = object : FeedbackHandler {
        override fun onActionClicked() {
            //TODO launch app store
            Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.andymariscal.walmartproducts")).apply {
                startActivity(this)
            }
        }
    }

}