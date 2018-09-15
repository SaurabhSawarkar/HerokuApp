package com.assignment.viewqwest.network.model

import com.deloitte.xperience.base.BaseResponse

data class Data(

        var users: ArrayList<Users>? = null,

        var has_more: Boolean = false
) : BaseResponse()