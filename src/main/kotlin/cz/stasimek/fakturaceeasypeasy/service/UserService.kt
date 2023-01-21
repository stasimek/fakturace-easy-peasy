package cz.stasimek.fakturaceeasypeasy.service

import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider
import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException
import cz.stasimek.fakturaceeasypeasy.repository.UserRepository
import cz.stasimek.fakturaceeasypeasy.service.interfaces.AppService
import org.hibernate.Session
import org.springframework.beans.BeanUtils
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager

@Service
class UserService(
    private val em: EntityManager,
    private val userRepository: UserRepository
) : AppService<User> {

    fun createUserIfNotExist(provider: Provider, oAuth2User: OAuth2User): User {
        var login = oAuth2User.getAttribute<String>("login")
        val email = oAuth2User.getAttribute<String>("email")
        val name = oAuth2User.getAttribute<String>("name")
        if (login == null) {
            login = email
        }
        if (login == null) {
            throw ApplicationException("Cannot create user, login is missing.")
        }
        var user = find(provider, login, email)
        if (user == null) {
            user = User()
            user.provider = provider
            user.login = login
            user.email = email
            user.name = name
            user = create(user)
        }
        return user
    }

    fun create(user: User): User {
        return userRepository.save(user)
    }

    fun update(user: User): User {
        val originalUser = findById(user.id!!)
        // Ensure that these values haven't been changed (login, provider, createdAt, deleted).
        val propertiesToIgnore = arrayOf("login", "provider", "createdAt", "deleted")
        BeanUtils.copyProperties(user, originalUser, *propertiesToIgnore)
        return userRepository.save(originalUser)
    }

    fun delete(id: UUID) {
        userRepository.deleteById(id)
    }

    override fun findById(id: UUID): User {
        return userRepository.findById(id).orElseThrow()
    }

    fun find(provider: Provider, login: String, email: String?): User? {
        var user: User? = userRepository.findByProviderAndLogin(provider, login)
        if (user == null && email != null) {
            user = userRepository.findByProviderAndEmail(provider, email)
        }
        return user
    }

    fun findAll(deleted: Boolean): Iterable<User> {
        val session = em.unwrap(Session::class.java)
        val filter = session.enableFilter("deletedFilter")
        filter.setParameter("deleted", deleted)
        val users = userRepository.findAll()
        session.disableFilter("deletedFilter")
        return users
    }
}