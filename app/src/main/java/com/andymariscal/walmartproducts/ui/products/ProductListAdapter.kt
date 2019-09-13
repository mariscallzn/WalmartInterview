package com.andymariscal.walmartproducts.ui.products

import com.andymariscal.walmartproducts.model.LOADING_FOOTER_TYPE
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.ui.MultiRowTypeAdapter
import com.andymariscal.walmartproducts.ui.ViewType

class ProductListAdapter(builder: Builder) : MultiRowTypeAdapter(builder) {

    init {
        items.add(0, LoadingFooterViewType)
    }

    fun addAll(productsList: List<Product>) {
        items.removeAt(items.lastIndex)
        items.addAll(productsList)
        items.add(LoadingFooterViewType)
        notifyDataSetChanged()
    }

    fun removeLodingView() {
        items.removeAt(items.lastIndex)
        notifyItemRemoved(items.lastIndex)
    }

    object LoadingFooterViewType: ViewType {
        override fun getViewType(): Int = LOADING_FOOTER_TYPE
    }
}