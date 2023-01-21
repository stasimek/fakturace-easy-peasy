package cz.stasimek.fakturaceeasypeasy.entity.interfaces

import java.io.Serializable
import java.util.*

/**
 * An interface for objects with an `id` property.
 *
 * @param <I> the type of the entity id
</I> */
interface Identifiable<I : Serializable?> {
    var id: I
}