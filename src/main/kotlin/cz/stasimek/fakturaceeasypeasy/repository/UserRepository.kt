package cz.stasimek.fakturaceeasypeasy.repository

import cz.stasimek.fakturaceeasypeasy.entity.User
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {

    fun findByProviderAndLogin(provider: Provider, login: String): User?

    fun findByProviderAndEmail(provider: Provider, email: String): User?
}