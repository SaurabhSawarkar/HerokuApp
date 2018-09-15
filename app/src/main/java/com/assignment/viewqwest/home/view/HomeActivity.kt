package com.assignment.viewqwest.home.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import com.assignment.viewqwest.BR
import com.assignment.viewqwest.R
import com.assignment.viewqwest.databinding.ActivityHomeBinding
import com.assignment.viewqwest.home.HomeViewModel
import com.assignment.viewqwest.adapter.UserListAdapter
import com.assignment.viewqwest.network.model.Users
import com.deloitte.studiostore.base.ToolbarBaseActivity
import com.deloitte.xperience.extension.showShortToast


class HomeActivity : ToolbarBaseActivity<HomeViewModel, ActivityHomeBinding>() {

    private var isLoadMore: Boolean = false
    private lateinit var adapter: UserListAdapter
    private val offset = 10
    private var limit = 10

    override fun getViewModelClassName(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.homeVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initVariables() {
        init()
        setToolbar()
        initObserver()
        getUsersList()
    }

    private fun setToolbar() {
        setupToolbar("Home", false)
    }

    private fun getUsersList() {
        viewModelInstance.getUserList(offset, limit)
    }

    private fun handleError(t: Throwable?) {
        "ERROR IN FETCHING API RESPONSE. Try again".showShortToast(this)
    }

    private fun initObserver() {
        viewModelInstance.getUserList()?.observe(this, Observer { list -> updateUserList(list) })
        viewModelInstance.getNetworkError().observe(this, Observer { error -> handleError(error) })
        viewModelInstance.getLoadMore().observe(this, Observer { isMore -> if (isMore != null) isLoadMore = isMore })
    }

    private fun updateUserList(userList: List<Users>?) {
        adapter.updateList(userList)
    }

    private fun init() {
        adapter = UserListAdapter()
        dataBindingInstance.recyclerView.layoutManager = LinearLayoutManager(this)
        dataBindingInstance.recyclerView.adapter = adapter
    }
}
