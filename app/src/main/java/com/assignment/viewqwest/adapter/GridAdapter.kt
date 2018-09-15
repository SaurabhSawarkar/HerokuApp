package com.deloitte.studiostore.adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.assignment.viewqwest.R
import com.assignment.viewqwest.databinding.UserGridItemBinding

class GridAdapter : BaseAdapter {

    private val itemsList: ArrayList<String>?

    constructor(itemList: ArrayList<String>?) : super() {
        itemsList = itemList
    }

    override fun getCount(): Int {
        return if (itemsList == null) 0 else itemsList.size
    }

    override fun getItem(position: Int): Any {
        return itemsList?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        var binding: UserGridItemBinding? = null
        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.user_grid_item,
                    null, false) as UserGridItemBinding
        }
        binding?.imageUrl = itemsList?.get(position)
        return if (null == binding) convertView!! else binding.root
    }
}