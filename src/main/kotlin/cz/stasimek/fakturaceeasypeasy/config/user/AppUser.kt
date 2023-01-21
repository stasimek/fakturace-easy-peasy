package cz.stasimek.fakturaceeasypeasy.config.user

import cz.stasimek.fakturaceeasypeasy.entity.User
import org.springframework.security.oauth2.core.user.OAuth2User

interface AppUser : OAuth2User {
    val user: User
}