package com.assignment.viewqwest.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.assignment.viewqwest.QwestApplication
import com.assignment.viewqwest.R
import com.assignment.viewqwest.databinding.UserGridItemBinding

class ChildListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private val itemsList: ArrayList<String>?

    constructor(itemList: ArrayList<String>?) : super() {
        itemsList = itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val childBinding = DataBindingUtil.inflate(inflater, R.layout.user_grid_item,
                null, false) as UserGridItemBinding
        return ChildListAdapter.Holder(childBinding)
    }

    override fun getItemCount(): Int {
        return if (null == itemsList) 0 else itemsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChildListAdapter.Holder).bindViewHolder(itemsList?.get(position)!!)
    }

    class Holder : RecyclerView.ViewHolder {

        private val childBiding: UserGridItemBinding
        private var isCombine = true
        private val layoutManager: GridLayoutManager

        init {
            layoutManager = GridLayoutManager(QwestApplication.instance, 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    isCombine = !isCombine
                    return if (isCombine) 1 else 2
                }
            }
        }

        constructor(binder: UserGridItemBinding) : super(binder.root) {
            childBiding = binder
        }

        fun bindViewHolder(imageUrl: String) {
            val viewModel = childBiding.imageUrl
            if (null == viewModel) {
                childBiding.imageUrl = String()
            }
            childBiding.imageUrl = imageUrl
        }
    }
}