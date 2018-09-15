package com.deloitte.studiostore.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.assignment.viewqwest.base.BaseActivity

abstract class ViewModelBaseActivity<VM : ViewModel, DB : ViewDataBinding> : BaseActivity<DB>() {

    protected lateinit var viewModelInstance: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        dataBindingInstance.setLifecycleOwner(this)
    }

    override fun performDataBinding() {
        dataBindingInstance = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewModelInstance = ViewModelProviders.of(this).get(getViewModelClassName())
        dataBindingInstance.setVariable(getBindingVariable(), viewModelInstance)
        dataBindingInstance.executePendingBindings()
    }

    protected abstract fun getViewModelClassName(): Class<VM>
    protected abstract fun getBindingVariable(): Int
}