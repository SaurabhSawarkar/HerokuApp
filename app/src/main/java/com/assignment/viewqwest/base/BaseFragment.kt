package com.deloitte.studiostore.base

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<V : ViewModel, D : ViewDataBinding> : Fragment() {

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    private var rootView: View? = null
    protected lateinit var dataBindingInstance: D
    protected var viewModelInstance: V? = null
    private var activity: ViewModelBaseActivity<*, *>? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ViewModelBaseActivity<*, *>) {
            val activity = context as ViewModelBaseActivity<*, *>?
            this.activity = activity
            activity!!.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelInstance = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBindingInstance.setVariable(getBindingVariable(), viewModelInstance)
        dataBindingInstance.executePendingBindings()
        dataBindingInstance.setLifecycleOwner(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBindingInstance = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        rootView = dataBindingInstance.root
        return rootView
    }

    fun getBaseActivity(): ViewModelBaseActivity<*, *>? {
        return activity
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }
}