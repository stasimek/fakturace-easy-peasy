package cz.stasimek.fakturaceeasypeasy.util

import java.util.*

object AppStringUtils {

    fun camelCaseToWords(text: String?): String? {
        if (text.isNullOrEmpty()) {
            return text
        }
        val firstLetter = text.substring(0, 1).lowercase()
        val restLetters = text.substring(1).replace("([A-Z])".toRegex(), " $1").lowercase()
        return firstLetter + restLetters
    }
}