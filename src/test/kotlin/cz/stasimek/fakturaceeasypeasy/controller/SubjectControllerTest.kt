package cz.stasimek.fakturaceeasypeasy.controller

import cz.stasimek.fakturaceeasypeasy.config.user.AppOAuth2User
import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider
import cz.stasimek.fakturaceeasypeasy.repository.SubjectRepository
import cz.stasimek.fakturaceeasypeasy.service.UserService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpSession
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.util.*

@AutoConfigureMockMvc
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = [
        "spring.datasource.url = jdbc:derby:memory:local;create=true",
        "spring.datasource.driver-class-name = org.apache.derby.jdbc.EmbeddedDriver",
        "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.DerbyTenSevenDialect",
        "spring.jpa.properties.hibernate.default_schema = APP",
        "spring.jpa.hibernate.ddl-auto = validate",
        "spring.flyway.enabled = true"
    ]
)
class SubjectControllerTest {

    @Autowired
    private lateinit var rest: MockMvc

    @Autowired
    private lateinit var subjectRepository: SubjectRepository

    @Autowired
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun subjects_shouldReturnStatus200() {
        val result = rest.perform(get("/api/subjects").session(oAuth2Login()))
        val response = result.andReturn().response
        assertThat(response.status, `is`(200))
        assertThat(response.contentAsString, `is`("[]"))
    }

    private fun oAuth2Login(): MockHttpSession {
        // Create DB user "test_user" if not exists
        var user = userService.find(Provider.GOOGLE, "test_user", null);
        if (user == null) {
            user = User();
            user.provider = Provider.GOOGLE
            user.login = "test_user"
            user.email = "test_user@gmail.com"
            user.name = "Test User"
            userService.create(user)
        }

        // Create OAuth2 principal
        val attributes: MutableMap<String, Any> = HashMap()
        attributes["sub"] = "test_user"
        val authorities: List<GrantedAuthority> = Collections.singletonList(
            OAuth2UserAuthority("ROLE_USER", attributes)
        )
        val oAuth2User: AppOAuth2User = AppOAuth2User(DefaultOAuth2User(authorities, attributes, "sub"), user)
        val principal = OAuth2AuthenticationToken(oAuth2User, authorities, "whatever")

        // Create session
        val session = MockHttpSession()
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, SecurityContextImpl(principal))
        return session
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