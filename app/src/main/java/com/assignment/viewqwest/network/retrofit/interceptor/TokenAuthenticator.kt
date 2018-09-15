package com.deloitte.xperience.network.interceptor


import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Used to refresh the access token.
 */

class TokenAuthenticator : Authenticator {

    //Handle logic to refresh token here
    override fun authenticate(route: Route?, response: Response?): Request? {
        return response?.request()
    }
}
