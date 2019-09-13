package com.andymariscal.walmartproducts.ui.products.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.walmartproducts.BuildConfig
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.ui.ViewType
import com.andymariscal.walmartproducts.ui.ViewTypeDelegateAdapter
import com.andymariscal.walmartproducts.util.fromHtml
import com.andymariscal.walmartproducts.util.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_products_list.view.*

class ProductsListDelegate(private val productsHandler: ProductHandler) :
    ViewTypeDelegateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType as Product)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_products_list)) {

        fun bind(product: Product) {
            Glide.with(itemView.context)
                .load(BuildConfig.URL_BASE + product.productImage)
                .into(itemView.thumbnail)
            itemView.productNameTv.text = product.productName
            itemView.productReview.fromHtml(
                itemView.context.resources.getQuantityString(
                    R.plurals.reviews,
                    product.reviewCount,
                    product.reviewCount
                )
            )
            itemView.ratingBar2.rating = product.reviewRating
            itemView.price.text = product.price

            itemView.setOnClickListener {
                productsHandler.onProductClicked(adapterPosition)
            }
        }
    }
}

interface ProductHandler {
    fun onProductClicked(position: Int)
}