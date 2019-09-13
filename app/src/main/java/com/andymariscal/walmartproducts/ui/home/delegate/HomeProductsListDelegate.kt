package com.andymariscal.walmartproducts.ui.home.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.model.HomeProductsList
import com.andymariscal.walmartproducts.model.PRODUCT_TYPE
import com.andymariscal.walmartproducts.ui.MultiRowTypeAdapter
import com.andymariscal.walmartproducts.ui.ViewType
import com.andymariscal.walmartproducts.ui.ViewTypeDelegateAdapter
import com.andymariscal.walmartproducts.ui.home.HomeProductsListAdapter
import com.andymariscal.walmartproducts.util.inflate
import kotlinx.android.synthetic.main.item_home_products_list.view.*

class HomeProductsListDelegate(private val homeProductHandler: HomeProductHandler) : ViewTypeDelegateAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType as HomeProductsList)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_home_products_list)) {

        fun bind(homeProductsList: HomeProductsList) {
            itemView.homeProductsRecycler.apply {
                PagerSnapHelper().attachToRecyclerView(this)
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = HomeProductsListAdapter(
                    MultiRowTypeAdapter.Builder()
                        .addDelegateAdapter(
                            PRODUCT_TYPE,
                            HomeProductDelegate(
                                homeProductHandler
                            )
                        )
                )
                (adapter as HomeProductsListAdapter).addAll(homeProductsList.products)
            }
        }
    }
}