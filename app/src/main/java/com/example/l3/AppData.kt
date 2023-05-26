package com.example.l3

import java.io.Serializable

data class AppData(
    val appName: String?,
    val appDeveloper: String?,
    val appType: String?,
    val appCategory: String?,
    val appCostType: String?,
    val appCost: String?,
    val appReleaseDate: String?,
    val appOpenSource: Boolean
) : Serializable {
    override fun toString(): String {
        return """
            App Name: $appName
            App Developer: $appDeveloper
            App Type: $appType
            App Category: $appCategory
            App Cost Type: $appCostType
            App Cost: $appCost
            App Release Date: $appReleaseDate
            Is App Open Source: $appOpenSource
        """.trimIndent()
    }
}

