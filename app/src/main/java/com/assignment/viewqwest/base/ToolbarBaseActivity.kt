package com.deloitte.studiostore.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.widget.TextView
import com.assignment.viewqwest.R
import com.assignment.viewqwest.databinding.ActivityToolbarBaseBinding


abstract class ToolbarBaseActivity<VM : ViewModel, DB : ViewDataBinding> : ViewModelBaseActivity<VM, DB>() {

    private var toolbarTitleTv: TextView? = null
    private var drawerLayout: DrawerLayout? = null
    private lateinit var parentDataBinding: ActivityToolbarBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initVariables()
    }

    override fun performDataBinding() {
        parentDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_toolbar_base, null, false) as ActivityToolbarBaseBinding
        super.setContentView(parentDataBinding.root)
        setSupportActionBar(parentDataBinding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        dataBindingInstance = DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)
        (parentDataBinding.viewContainer).addView(dataBindingInstance.root)
        this.viewModelInstance = ViewModelProviders.of(this).get(getViewModelClassName())
        dataBindingInstance.setVariable(getBindingVariable(), viewModelInstance)
        dataBindingInstance.executePendingBindings()
    }

    private fun initViews() {
        toolbarTitleTv = parentDataBinding.tvToolbarTitle
        drawerLayout = parentDataBinding.drawerLayout
    }

    protected abstract fun initVariables()

    protected fun setToolbarTitle(titleString: String) {
        toolbarTitleTv?.text = titleString
    }

    protected fun setToolbarTitleTextColor(color: Int) {
        toolbarTitleTv?.setTextColor(color)
    }

    protected fun updateToolbarColor(colorId: Int) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, colorId)))
    }

    protected fun setupToolbar(toolbarTitle: String, isUpButton: Boolean) {
        val toolbar = parentDataBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.displayOptions = (ActionBar.DISPLAY_HOME_AS_UP
                or ActionBar.DISPLAY_SHOW_HOME or ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setDisplayHomeAsUpEnabled(isUpButton)
        supportActionBar?.setHomeButtonEnabled(true)
        toolbarTitleTv?.text = toolbarTitle
    }
}