package com.deloitte.xperience.network.currentOpportunity

import com.assignment.viewqwest.network.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface APIEndPoints {

    interface IGetUsersEndPoint {
        @GET("api/users")
        fun getUsers(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<UserResponse>
    }

}