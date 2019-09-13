package com.andymariscal.walmartproducts.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.andymariscal.walmartproducts.databinding.FragmentProductDetailBinding
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.util.viewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProductDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var productDetailViewModel: ProductDetailViewModel

    private lateinit var binding: FragmentProductDetailBinding

    private lateinit var adapter: ProductDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailViewModel = viewModelProviders(viewModelFactory)

        adapter = ProductDetailAdapter()
        binding = FragmentProductDetailBinding.inflate(inflater, container, false).apply {
            viewModel = productDetailViewModel
            PagerSnapHelper().attachToRecyclerView(productDetailRecycler)
            productDetailRecycler.layoutManager =
                LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
            productDetailRecycler.adapter = adapter
        }

        arguments?.let {
            it.getParcelableArrayList<Product>("products")?.let { products ->
                adapter.addAll(products)
            }
            binding.productDetailRecycler.layoutManager?.scrollToPosition(it.getInt("position"))
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }
}