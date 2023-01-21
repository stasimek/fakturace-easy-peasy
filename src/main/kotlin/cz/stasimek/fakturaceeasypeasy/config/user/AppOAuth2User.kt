package cz.stasimek.fakturaceeasypeasy.config.user

import cz.stasimek.fakturaceeasypeasy.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class AppOAuth2User(
    private val oAuth2User: OAuth2User,
    override val user: User
) : OAuth2User, AppUser {

    override fun <A : Any?> getAttribute(name: String): A? {
        return oAuth2User.getAttribute(name)
    }

    override fun getAttributes(): Map<String, Any> {
        return oAuth2User.attributes
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return oAuth2User.authorities
    }

    override fun getName(): String {
        return oAuth2User.name
    }
}