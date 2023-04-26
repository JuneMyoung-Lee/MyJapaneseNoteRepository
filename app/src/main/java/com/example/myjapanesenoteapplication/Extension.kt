package com.example.myjapanesenoteapplication

import android.content.res.Resources

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int?.toNonNullInt(defaultValue: Int = 0) = this ?: defaultValue