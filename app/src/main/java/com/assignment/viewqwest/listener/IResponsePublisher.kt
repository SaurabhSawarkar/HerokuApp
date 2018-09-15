package com.deloitte.xperience.network.util

import com.deloitte.xperience.base.BaseResponse
import io.reactivex.disposables.Disposable

/**
 * Blue print for response publisher.
 */

interface IResponsePublisher<T : BaseResponse> {


    fun onSuccess(@RequestTypes.Interface requestType: Int, responseBean: T?)

    fun onError(@RequestTypes.Interface requestType: Int, error: Throwable?)

    fun onSubscribe(@RequestTypes.Interface requestType: Int, d: Disposable)
}
