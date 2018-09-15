package com.deloitte.xperience.network.manager

import com.deloitte.xperience.base.BaseResponse
import com.deloitte.xperience.network.util.IResponsePublisher
import com.deloitte.xperience.network.util.RequestTypes

interface IAPIManager {

    fun getUsers(@RequestTypes.Interface requestType: Int, offset: Int, limit: Int,
                 publisher: IResponsePublisher<BaseResponse>)
}