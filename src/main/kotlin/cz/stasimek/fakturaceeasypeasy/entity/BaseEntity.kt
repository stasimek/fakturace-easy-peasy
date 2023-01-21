package cz.stasimek.fakturaceeasypeasy.entity

import cz.stasimek.fakturaceeasypeasy.entity.interfaces.Auditable
import cz.stasimek.fakturaceeasypeasy.entity.interfaces.Identifiable
import cz.stasimek.fakturaceeasypeasy.entity.interfaces.SoftDeletable
import lombok.EqualsAndHashCode
import org.hibernate.annotations.Filter
import org.hibernate.annotations.FilterDef
import org.hibernate.annotations.ParamDef
import org.hibernate.annotations.Where
import java.io.Serializable
import java.time.ZonedDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.PastOrPresent

@MappedSuperclass
// @SQLDelete doesn't work here. Must be in each entity.
// @SQLDelete(sql = "UPDATE #{#entityName} SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = [ParamDef(name = "deleted", type = "boolean")])
@Filter(name = "deletedFilter", condition = "deleted = :deleted")
@EqualsAndHashCode(of = ["id"])
abstract class BaseEntity : Identifiable<UUID?>, Auditable, SoftDeletable, Serializable {

    @Id
    @Column(columnDefinition = "char(16) for bit data")
    @GeneratedValue
    open override var id: UUID? = null

    @Column(nullable = false)
    @PastOrPresent
    open override var createdAt: ZonedDateTime? = null

    @Column(nullable = false)
    @PastOrPresent
    open override var updatedAt: ZonedDateTime? = null

    @Column
    open override var deleted = false

    open override fun toString(): String {
        return String.format("%s (#%s)", javaClass.simpleName, id)
    }

    @PrePersist
    private fun prePersist() {
        updatedAt = ZonedDateTime.now()
        createdAt = updatedAt
    }

    @PreUpdate
    private fun preUpdate() {
        updatedAt = ZonedDateTime.now()
    }
}