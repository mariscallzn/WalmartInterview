package com.andymariscal.walmartproducts.ui.products

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(var linearLayoutManager: LinearLayoutManager? = null) :
    RecyclerView.OnScrollListener() {

    abstract fun loadMoreItems()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        linearLayoutManager?.let {
            val visibleItemsCount = it.childCount
            val totalItemCount = it.itemCount
            val firstVisibleItemPosition = it.findFirstVisibleItemPosition()

            if ((visibleItemsCount + firstVisibleItemPosition)
                >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }
}