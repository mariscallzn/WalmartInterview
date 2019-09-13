package com.andymariscal.walmartproducts.ui.home

import com.andymariscal.walmartproducts.ui.MultiRowTypeAdapter
import com.andymariscal.walmartproducts.ui.ViewType

class HomeAdapter(builder: Builder): MultiRowTypeAdapter(builder) {

    fun add(viewType: ViewType) {
        items.add(viewType)
    }

    fun addAtIndex(index: Int, viewType: ViewType) {
        items.add(index, viewType)
        notifyItemInserted(index)
    }

    fun addAll(items: List<ViewType>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}