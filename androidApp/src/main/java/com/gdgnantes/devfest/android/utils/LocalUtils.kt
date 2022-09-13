package com.gdgnantes.devfest.android.utils

import com.gdgnantes.devfest.model.ContentLanguage
import java.util.*

fun Locale.toContentLanguage(): ContentLanguage {
    return when (language) {
        "fr" -> ContentLanguage.FRENCH
        "en" -> ContentLanguage.ENGLISH
        else -> ContentLanguage.ENGLISH
    }
}