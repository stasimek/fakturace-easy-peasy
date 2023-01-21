package cz.stasimek.fakturaceeasypeasy.config.user

import cz.stasimek.fakturaceeasypeasy.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.oidc.OidcIdToken
import org.springframework.security.oauth2.core.oidc.OidcUserInfo
import org.springframework.security.oauth2.core.oidc.user.OidcUser

class AppOidcUser(
    private val oidcUser: OidcUser,
    override val user: User
) : OidcUser, AppUser {

    override fun <A : Any?> getAttribute(name: String): A? {
        return oidcUser.getAttribute(name)
    }

    override fun getAttributes(): Map<String, Any> {
        return oidcUser.attributes
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return oidcUser.authorities
    }

    override fun getName(): String {
        return oidcUser.name
    }

    override fun getClaims(): Map<String, Any> {
        return oidcUser.claims
    }

    override fun getUserInfo(): OidcUserInfo {
        return oidcUser.userInfo
    }

    override fun getIdToken(): OidcIdToken {
        return oidcUser.idToken
    }
}