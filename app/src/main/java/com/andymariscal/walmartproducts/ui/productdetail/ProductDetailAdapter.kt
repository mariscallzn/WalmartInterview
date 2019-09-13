package com.andymariscal.walmartproducts.ui.productdetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.walmartproducts.BuildConfig
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.model.Product
import com.andymariscal.walmartproducts.util.fromHtml
import com.andymariscal.walmartproducts.util.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class ProductDetailAdapter : RecyclerView.Adapter<ProductDetailAdapter.ViewHolder>() {

    private val items = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addAll(products: List<Product>) {
        items.addAll(products)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_product)) {

        fun bind(product: Product) {
            Glide.with(itemView.context)
                .load(BuildConfig.URL_BASE + product.productImage)
                .into(itemView.thumbnail)

            itemView.productNameTv.text = product.productName
            itemView.ratingBar.rating = product.reviewRating
            itemView.productReview.fromHtml(
                itemView.context.resources.getQuantityString(
                    R.plurals.reviews,
                    product.reviewCount,
                    product.reviewCount
                )
            )
            itemView.shortDescriptionTv.fromHtml(product.shortDescription)
            itemView.longDescriptionTv.fromHtml(product.longDescription)
        }
    }
}