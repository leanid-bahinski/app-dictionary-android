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
    val appOpenSource: Boolean,
    val appPhone: String?,
    val appEmail: String?,
    val appSocial: String?,
    val logo:  String?
    ) : Serializable {
    override fun toString(): String {
        return """
            App Name: $appName
            Developer: $appDeveloper
            Type: $appType
            Category: $appCategory
            Cost Type: $appCostType
            Cost: $appCost
            Release Date: $appReleaseDate
            Is Open Source: $appOpenSource
            Phone: $appPhone
            Email: $appEmail
            Social link: $appSocial
        """.trimIndent()
    }
}

