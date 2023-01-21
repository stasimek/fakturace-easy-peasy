package cz.stasimek.fakturaceeasypeasy.enumeration

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class CzNaceTest {

    @Test
    fun getNameCs() {
        assertThat(
            CzNace.N_10000.nameCs,
            `is`("Rostlinná a živočišná výroba, myslivost a související činnosti")
        )
    }

    @Test
    fun getCode() {
        assertThat(
            CzNace.N_10000.code,
            `is`("10000")
        )
    }

    @Test
    fun values() {
        assertThat(
            CzNace.values()[0],
            `is`(CzNace.N_10000)
        )
    }

    @Test
    fun valuesMap() {
        val entries = CzNace.valuesMap().entries

        // Check first item
        assertThat(
            entries.first().value,
            `is`("Administrativní, kancelářské a jiné podpůrné činnosti pro podnikání")
        )
        assertThat(
            entries.first().key,
            `is`("N_820000")
        )

        // Check last item
        assertThat(
            entries.last().value,
            `is`("Životní pojištění")
        )
        assertThat(
            entries.last().key,
            `is`("N_651100")
        )
    }

    @Test
    fun valueOf() {
    }
}