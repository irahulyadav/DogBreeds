package com.cabo.dogbreeds.widget

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<T : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: T = DataBindingUtil.getBinding(itemView)!!

}
