package cz.stasimek.fakturaceeasypeasy.repository

import cz.stasimek.fakturaceeasypeasy.entity.Invoice
import cz.stasimek.fakturaceeasypeasy.entity.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface InvoiceRepository : CrudRepository<Invoice, UUID> {

    @Query(
        "SELECT i"
                + " FROM Invoice i"
                + " WHERE"
                + "   i.user = :user"
                + "   AND i.type = 'ISSUED'"
                + "   AND i.deleted = false"
                + " ORDER BY dueDate DESC"
    )
    fun findLastIssuedByUser(
        @Param("user") user: User,
        pageable: Pageable
    ): List<Invoice>

    @Query(
        "SELECT i"
                + " FROM Invoice i"
                + " WHERE "
                + "   i.user = :user"
                + "   AND i.type = 'ISSUED'"
                + "   AND year(i.issueDate) = :year"
                + "   AND deleted = false"
    )
    fun findIssuedByUserAndYear(
        @Param("user") user: User,
        @Param("year") year: Int
    ): Iterable<Invoice>

    @Query(
        "SELECT i"
                + " FROM Invoice i"
                + " WHERE "
                + "   i.user = :user"
                + "   AND i.type = 'RECEIVED'"
                + "   AND year(i.receiveDate) = :year"
                + "   AND deleted = false"
    )
    fun findReceivedByUserAndYear(
        @Param("user") user: User,
        @Param("year") year: Int
    ): Iterable<Invoice>
}