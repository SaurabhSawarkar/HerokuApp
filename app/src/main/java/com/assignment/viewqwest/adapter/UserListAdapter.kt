package com.assignment.viewqwest.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.assignment.viewqwest.QwestApplication
import com.assignment.viewqwest.R
import com.assignment.viewqwest.databinding.UserListViewItemBinding
import com.assignment.viewqwest.network.model.Users


class UserListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userList: List<Users>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val childBinding = DataBindingUtil.inflate(inflater, R.layout.user_list_view_item,
                null, false) as UserListViewItemBinding
        return Holder(childBinding)
    }

    override fun getItemCount(): Int {
        return if (null == userList) 0 else userList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Holder).bindViewHolder(userList?.get(position)!!)
    }

    fun updateList(list: List<Users>?) {
        if (null == userList) {
            userList = list
            notifyDataSetChanged()
        }
    }

    class Holder : RecyclerView.ViewHolder {

        private val childBiding: UserListViewItemBinding
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

        constructor(binder: UserListViewItemBinding) : super(binder.root) {
            childBiding = binder
        }

        fun bindViewHolder(user: Users) {
            val viewModel = childBiding.user
            if (null == viewModel) {
                childBiding.user = Users()
            }
            childBiding.user?.name = user.name
            childBiding.user?.image = user.image

            val gridAdapter = ChildListAdapter(user.items)
            childBiding.childRecyclerView.layoutManager = LinearLayoutManager(QwestApplication.instance)
            childBiding.childRecyclerView.adapter = gridAdapter
        }
    }
}