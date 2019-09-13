package com.andymariscal.walmartproducts.ui

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class MultiRowTypeAdapter(private val builder: Builder) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<ViewType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        builder.delegateAdapters[viewType].onCreateViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        builder.delegateAdapters[getItemViewType(position)].onBindViewHolder(
            holder,
            items[position]
        )

    override fun getItemViewType(position: Int): Int =
        items[position].getViewType()

    class Builder {
        val delegateAdapters = SparseArray<ViewTypeDelegateAdapter>()

        fun addDelegateAdapter(viewType: Int, delegate: ViewTypeDelegateAdapter): Builder =
            this.also {
                delegateAdapters.append(viewType, delegate)
            }
    }
}

interface ViewType {
    fun getViewType(): Int
}

abstract class ViewTypeDelegateAdapter {
    abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType)
}