package com.assignment.viewqwest.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.deloitte.studiostore.base.BaseFragment

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity(), BaseFragment.Callback {

    protected abstract fun getLayoutId(): Int
    protected lateinit var dataBindingInstance: DB

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        dataBindingInstance.setLifecycleOwner(this)
    }

    open fun performDataBinding() {
        dataBindingInstance = DataBindingUtil.setContentView(this, getLayoutId())
        dataBindingInstance.executePendingBindings()
    }

}