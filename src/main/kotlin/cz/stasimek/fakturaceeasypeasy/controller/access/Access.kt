package cz.stasimek.fakturaceeasypeasy.controller.access

import cz.stasimek.fakturaceeasypeasy.config.user.AppUser
import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.entity.interfaces.HasUser
import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException
import cz.stasimek.fakturaceeasypeasy.exception.BadRequestException
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService
import cz.stasimek.fakturaceeasypeasy.util.AppStringUtils
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.*

object Access {

    fun check(
        id: UUID, principal: OAuth2User, action: String, service: AppService<*>
    ) {
        val original = service.findById(id)
        val user: User
        if (original is User) {
            user = original
        } else if (original is HasUser) {
            user = (original as HasUser).user!!
        } else {
            throw ApplicationException(
                "Entity " + original.javaClass.name + " isn't instance of User or HasUser."
            )
        }
        if (user.id != getUser(principal).id) {
            val entityWords = AppStringUtils.camelCaseToWords(original.javaClass.simpleName)
            throw BadRequestException(
                if (original is User)
                    "Unable to $action user setting. You can $action only yourself."
                else
                    "Unable to $action $entityWords. You can $action only your own ${entityWords}s."
            )
        }
    }

    fun getUser(principal: OAuth2User): User {
        if (principal !is AppUser) {
            throw ApplicationException("Principal must be instance of AppUser.")
        }
        return principal.user
    }
}