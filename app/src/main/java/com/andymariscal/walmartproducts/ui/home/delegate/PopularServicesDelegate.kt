package com.andymariscal.walmartproducts.ui.home.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.ui.ViewType
import com.andymariscal.walmartproducts.ui.ViewTypeDelegateAdapter
import com.andymariscal.walmartproducts.util.inflate
import kotlinx.android.synthetic.main.item_popular_services.view.*

class PopularServicesDelegate(private val popularServicesHandler: PopularServicesHandler) :
    ViewTypeDelegateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_popular_services)) {

        fun bind(viewType: ViewType) {
            itemView.profileIv.setOnClickListener {
                popularServicesHandler.onActionClick(PopularService.PROFILE)
            }
            itemView.sourceCodeIv.setOnClickListener {
                popularServicesHandler.onActionClick(PopularService.SOURCE_CODE)
            }
            itemView.websiteIv.setOnClickListener {
                popularServicesHandler.onActionClick(PopularService.WEBSITE)
            }
            itemView.shoppingIv.setOnClickListener {
                popularServicesHandler.onActionClick(PopularService.SHOPPING)
            }
        }
    }
}

interface PopularServicesHandler {
    fun onActionClick(popularService: PopularService)
}

enum class PopularService {
    PROFILE,
    SOURCE_CODE,
    WEBSITE,
    SHOPPING
}