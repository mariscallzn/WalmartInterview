package com.andymariscal.walmartproducts.ui.home.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andymariscal.walmartproducts.R
import com.andymariscal.walmartproducts.ui.ViewType
import com.andymariscal.walmartproducts.ui.ViewTypeDelegateAdapter
import com.andymariscal.walmartproducts.util.inflate
import kotlinx.android.synthetic.main.item_feedback.view.*

class FeedbackDelegate(private val feedbackHandler: FeedbackHandler) : ViewTypeDelegateAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(parent)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, viewType: ViewType) {
        (viewHolder as ViewHolder).bind(viewType)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_feedback)) {

        fun bind(viewType: ViewType) {
            itemView.tellUsBtn.setOnClickListener {
                feedbackHandler.onActionClicked()
            }
        }
    }
}

interface FeedbackHandler {
    fun onActionClicked()
}