package com.deloitte.xperience.extension

import android.content.Context
import android.widget.Toast

fun <T> lazyN(init: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, init)

fun String.showShortToast(context: Context?) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}