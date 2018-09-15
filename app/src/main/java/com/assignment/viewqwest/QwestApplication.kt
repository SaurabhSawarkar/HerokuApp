package com.assignment.viewqwest

import android.app.Application
import com.assignment.viewqwest.util.AppConstants
import com.deloitte.xperience.network.manager.APIManager

class QwestApplication : Application() {

    companion object {
        lateinit var instance: QwestApplication
    }

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        instance = this
        APIManager.setBaseUrl(AppConstants.BASE_URL)
    }
}