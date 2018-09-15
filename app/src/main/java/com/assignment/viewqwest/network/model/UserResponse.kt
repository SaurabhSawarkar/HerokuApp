package com.assignment.viewqwest.network.model

import com.deloitte.xperience.base.BaseResponse

data class UserResponse(
        var message: String? = null,

        var status: String? = null,

        var data: Data? = null

) : BaseResponse()