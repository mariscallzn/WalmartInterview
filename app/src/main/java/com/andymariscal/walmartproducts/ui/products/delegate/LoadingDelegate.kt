package com.andymariscal.walmartproducts.ui.products.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.ui.ViewType
import com.andymariscal.walmartproducts.ui.ViewTypeDelegateAdapter
import com.andymariscal.walmartproducts.util.inflate

class LoadingDelegate : ViewTypeDelegateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        /* no-op */
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_loading_footer))
}