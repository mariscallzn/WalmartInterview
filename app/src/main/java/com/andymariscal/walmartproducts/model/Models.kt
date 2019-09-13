package com.andymariscal.walmartproducts.model

import android.os.Parcel
import android.os.Parcelable
import com.andymariscal.walmartproducts.ui.ViewType
import kotlinx.android.parcel.Parcelize

/* Payload
{
  "products":[
    {
      "productId":"003e3e6a-3f84-43ac-8ef3-a5ae2db0f80e",
      "productName":"Ellerton TV Console",
      "shortDescription":"[HTML TEXT]",
      "longDescription":"[HTML TEXT]",
      "price":"$949.00",
      "productImage":"/images/image2.jpeg",
      "reviewRating":2,
      "reviewCount":1,
      "inStock":true
    }
  ],
  "totalProducts":224,
  "pageNumber":1,
  "pageSize":1,
  "statusCode":200
}
* */


const val PRODUCT_TYPE = 0
const val TWO_DAY_DELIVERY_TYPE = 1
const val POPULAR_SERVICES_TYPE = 2
const val FEEDBACK_TYPE = 3
const val LOADING_FOOTER_TYPE = 4


//region Product
data class Product(
    val productId: String,
    val productName: String,
    val shortDescription: String,
    val longDescription: String,
    val price: String,
    val productImage: String,
    val reviewRating: Float,
    val reviewCount: Int,
    val inStock: Boolean
) : Parcelable, ViewType {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readInt() == 1
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(productId)
        parcel?.writeString(productName)
        parcel?.writeString(shortDescription)
        parcel?.writeString(longDescription)
        parcel?.writeString(price)
        parcel?.writeString(productImage)
        parcel?.writeFloat(reviewRating)
        parcel?.writeInt(reviewCount)
        parcel?.writeInt(if(inStock) 1 else 0)
    }

    override fun describeContents(): Int = 0

    override fun getViewType(): Int = PRODUCT_TYPE

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Product> {
            override fun createFromParcel(parcel: Parcel) = Product(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Product>(size)
        }
    }
}

data class HomeProductsList(val products: List<Product>): ViewType {
    override fun getViewType(): Int = PRODUCT_TYPE
}
//endregion

//region ProductsList
data class ProductsList(
    val products: List<Product>,
    val totalProducts: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val statusCode: Int
)
//endregion