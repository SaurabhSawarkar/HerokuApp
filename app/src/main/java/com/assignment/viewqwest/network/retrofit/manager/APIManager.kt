package com.deloitte.xperience.network.manager

import com.deloitte.xperience.base.BaseResponse
import com.deloitte.xperience.network.client.RetrofitClient
import com.deloitte.xperience.network.currentOpportunity.APIEndPoints
import com.deloitte.xperience.network.util.IResponsePublisher
import com.deloitte.xperience.network.util.RequestTypes
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class APIManager : IAPIManager {

    companion object {

        @Volatile
        private var instance: IAPIManager? = null
        private lateinit var baseUrl: String
        private lateinit var retrofitClient: Retrofit

        fun getInstance(): IAPIManager {
            if (null == instance) {
                synchronized(APIManager::class.java) {
                    if (null == instance) {
                        instance = APIManager()
                    }
                }
            }
            return instance!!
        }

        fun setBaseUrl(baseUrl: String) {
            this.baseUrl = baseUrl
            updateRetrofitClient()
        }

        private fun updateRetrofitClient() {
            retrofitClient = RetrofitClient.baseUrl(baseUrl).debug(true).buildSimple()
        }
    }

    override fun getUsers(@RequestTypes.Interface requestType: Int, offset: Int, limit: Int,
                          @NonNull publisher: IResponsePublisher<BaseResponse>) {
        val api = retrofitClient.create(APIEndPoints.IGetUsersEndPoint::class.java)
        val observable = api.getUsers(offset, limit)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<BaseResponse> {
                    override fun onSuccess(t: BaseResponse) {
                        publisher.onSuccess(requestType, t)
                    }

                    override fun onSubscribe(d: Disposable) {
                        publisher.onSubscribe(requestType, d)
                    }

                    override fun onError(e: Throwable) {
                        publisher.onError(requestType, e)
                    }
                })
    }
}