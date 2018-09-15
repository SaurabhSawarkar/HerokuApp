package com.deloitte.xperience.base

import retrofit2.Response

open class BaseResponse {
    var baseMessage: String? = null
    var baseStatus: String? = null
    var baseResponse: Response<*>? = null
    var baseCode: Int = 0
}
