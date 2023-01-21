package cz.stasimek.fakturaceeasypeasy.util

import cz.stasimek.fakturaceeasypeasy.util.AppStringUtils.camelCaseToWords
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test

class AppStringUtilsTest {

    @Test
    fun camelCaseToWords_givenCamelCase_returns2Words() {
        assertThat(camelCaseToWords("CamelCase"), `is`("camel case"))
    }

    @Test
    fun camelCaseToWords_givenNull_returnsNull() {
        assertThat(camelCaseToWords(null), `is`(nullValue()))
    }

    @Test
    fun camelCaseToWords_givenEmptyString_returnsEmptyString() {
        assertThat(camelCaseToWords(""), `is`(""))
    }

    @Test
    fun camelCaseToWords_givenCamel_returnsWord() {
        assertThat(camelCaseToWords("Camel"), `is`("camel"))
    }

    @Test
    fun camelCaseToWords_givenA_returnsA() {
        assertThat(camelCaseToWords("A"), `is`("a"))
    }

    @Test
    fun camelCaseToWords_givenTheCamelCase_returns3Words() {
        assertThat(camelCaseToWords("theCamelCase"), `is`("the camel case"))
    }
}