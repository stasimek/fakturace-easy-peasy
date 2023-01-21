package cz.stasimek.fakturaceeasypeasy.repository

import cz.stasimek.fakturaceeasypeasy.entity.*
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface SubjectRepository : CrudRepository<Subject, UUID> {

    fun findByUser(@Param("user") user: User): Iterable<Subject>

    @Query(
        "SELECT s"
                + " FROM Subject s"
                + " WHERE "
                + "   s.user = :user"
                + "   AND type IN ('CUSTOMER', 'BOTH')"
                + "   AND deleted = false"
    )
    fun findAllCustomersByUser(@Param("user") user: User): Iterable<Subject>

    @Query(
        "SELECT s"
                + " FROM Subject s"
                + " WHERE "
                + "   s.user = :user"
                + "   AND type IN ('SUPPLIER', 'BOTH')"
                + "   AND deleted = false"
    )
    fun findAllSuppliersByUser(@Param("user") user: User): Iterable<Subject>
}