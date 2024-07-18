package com.pfv.advancedreminder.tools

enum class BuildTypes {
    RELEASE,
    DEBUG,
    STAGING;

    val typeName: String
        get() = when (this) {
            RELEASE -> "release"
            DEBUG -> "debug"
            STAGING -> "staging"
        }
}