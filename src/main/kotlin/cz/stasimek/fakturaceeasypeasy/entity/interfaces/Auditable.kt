package cz.stasimek.fakturaceeasypeasy.entity.interfaces

import java.time.ZonedDateTime

interface Auditable {
    var createdAt: ZonedDateTime?
    var updatedAt: ZonedDateTime?
}