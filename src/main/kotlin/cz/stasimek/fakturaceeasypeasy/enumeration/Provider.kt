package cz.stasimek.fakturaceeasypeasy.enumeration

import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException

enum class Provider {
    GITHUB, GOOGLE;

    companion object {
        fun valueOfCaseInsensitive(search: String): Provider {
            for (provider in values()) {
                if (provider.name.compareTo(search, ignoreCase = true) == 0) {
                    return provider
                }
            }
            throw ApplicationException(String.format("Provider %s not supported.", search))
        }
    }
}