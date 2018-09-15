package com.deloitte.xperience.network.util

import android.support.annotation.IntDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Defines requests of each API calls.
 */

object RequestTypes {

    const val NONE = 0
    const val GET_USERS = NONE + 1

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(NONE,
            GET_USERS)

    annotation class Interface
}
