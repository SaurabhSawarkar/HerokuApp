package com.assignment.viewqwest.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.assignment.viewqwest.network.model.UserResponse
import com.assignment.viewqwest.network.model.Users
import com.deloitte.xperience.base.BaseResponse
import com.deloitte.xperience.extension.lazyN
import com.deloitte.xperience.network.manager.APIManager
import com.deloitte.xperience.network.util.IResponsePublisher
import com.deloitte.xperience.network.util.RequestTypes
import io.reactivex.disposables.Disposable

class HomeViewModel : ViewModel() {

    private val userList by lazyN { MutableLiveData<List<Users>>() }
    private val isLoadMore by lazyN { MutableLiveData<Boolean>() }
    private val networkError by lazyN { MutableLiveData<Throwable>() }
    private val userSubscribe by lazyN { MutableLiveData<Disposable>() }

    fun getUserList(offset: Int, limit: Int) {
        APIManager.getInstance().getUsers(RequestTypes.GET_USERS, offset, limit, responsePublisher)
    }

    private val responsePublisher = object : IResponsePublisher<BaseResponse> {
        override fun onSuccess(requestType: Int, responseBean: BaseResponse?) {
            when (requestType) {
                RequestTypes.GET_USERS -> {
                    val response = responseBean as UserResponse?
                    userList.value = response?.data?.users
                    isLoadMore.value = response?.data?.has_more
                }

            }
        }

        override fun onError(requestType: Int, error: Throwable?) {
            networkError.value = error
        }

        override fun onSubscribe(requestType: Int, d: Disposable) {
            when (requestType) {
                RequestTypes.GET_USERS -> {
                    userSubscribe.value = d
                }
            }
        }
    }

    fun getUserList(): LiveData<List<Users>>? {
        return userList
    }

    fun getLoadMore(): LiveData<Boolean> {
        return isLoadMore
    }

    fun getNetworkError(): LiveData<Throwable> {
        return networkError
    }

    fun getUserSubscribe(): LiveData<Disposable> {
        return userSubscribe
    }
}