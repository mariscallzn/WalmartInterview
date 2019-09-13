package com.andymariscal.walmartproducts.ui.home

import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.ui.MultiRowTypeAdapter

class HomeProductsListAdapter(builder: Builder) : MultiRowTypeAdapter(builder) {

    fun addAll(homeProducts: List<Product>) {
        items.addAll(homeProducts)
        notifyDataSetChanged()
    }
}