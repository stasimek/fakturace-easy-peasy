package cz.stasimek.fakturaceeasypeasy.service.interfaces

import cz.stasimek.fakturaceeasypeasy.entity.BaseEntity
import java.util.*

interface AppService<E : BaseEntity> {
    fun findById(id: UUID): E
}