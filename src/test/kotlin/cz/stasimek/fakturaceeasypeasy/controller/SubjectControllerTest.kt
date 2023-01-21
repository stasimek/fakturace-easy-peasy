package cz.stasimek.fakturaceeasypeasy.controller

import cz.stasimek.fakturaceeasypeasy.repository.SubjectRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.url = jdbc:h2:mem:test;INIT=create schema if not exists APP",
        "spring.datasource.driver-class-name = org.h2.Driver",
        "spring.datasource.username = sa",
        "spring.datasource.password = password",
        "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect",
        "spring.security.user.name = user",
        "spring.security.user.password = 69420",
        "spring.jpa.hibernate.ddl-auto = update",
        "spring.flyway.enabled = false"
    ]
)
class SubjectControllerTest {
    @LocalServerPort
    private val port = 0

    @Autowired
    private val rest: TestRestTemplate? = null

    @Autowired
    private val subjectRepository: SubjectRepository? = null

    companion object {
        @BeforeAll
        fun setUpBeforeClass() {
        }
    }

    @BeforeEach
    fun setUp() {
    }

    //@Test
    fun subjects_shouldReturn4Subjects() {
        assertThat(
            rest!!.getForObject("http://localhost:$port/api/subjects", String::class.java),
            `is`("XXXXXXXXXX")
        )
    }

    //@Test
    fun getCustomers() {
    }

    //@Test
    fun getSuppliers() {
    }

    //@Test
    fun getSubject() {
    }

    //@Test
    fun createSubject() {
    }

    //@Test
    fun updateSubject() {
    }

    //@Test
    fun deleteSubject() {
    }
}