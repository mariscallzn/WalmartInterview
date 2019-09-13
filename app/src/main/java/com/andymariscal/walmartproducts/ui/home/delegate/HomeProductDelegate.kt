package com.andymariscal.walmartproducts.ui.home.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.walmartproducts.BuildConfig
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.ui.ViewType
import com.andymariscal.walmartproducts.ui.ViewTypeDelegateAdapter
import com.andymariscal.walmartproducts.util.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home_product.view.*

class HomeProductDelegate(private val handlerHome: HomeProductHandler) : ViewTypeDelegateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType as Product)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_home_product)) {

        fun bind(homeProduct: Product) {
            Glide.with(itemView.context)
                .load(BuildConfig.URL_BASE + homeProduct.productImage)
                .into(itemView.thumbnail)
            itemView.productNameTv.text = homeProduct.productName
            itemView.productPriceTv.text = homeProduct.price
            itemView.ratingBar.rating = homeProduct.reviewRating
            itemView.setOnClickListener {
                handlerHome.onHomeProductClicked(arrayListOf(homeProduct))
            }
        }
    }
}

interface HomeProductHandler {
    fun onHomeProductClicked(products: List<Product>)
}