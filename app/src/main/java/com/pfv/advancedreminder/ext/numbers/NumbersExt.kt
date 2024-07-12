package com.pfv.advancedreminder.ext.numbers

fun <T> T.ifNegativeSetZero(): T where T : Number, T : Comparable<T> {
    return if (this.toDouble() < 0) {
        when (this) {
            is Int -> 0 as T
            is Long -> 0L as T
            is Float -> 0.0f as T
            is Double -> 0.0 as T
            is Short -> 0.toShort() as T
            is Byte -> 0.toByte() as T
            else -> this
        }
    } else {
        this
    }
}
