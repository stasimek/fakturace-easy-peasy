package cz.stasimek.fakturaceeasypeasy.entity.interfaces;

import java.io.Serializable;

/**
 * An interface for objects with an {@code id} property.
 *
 * @param <I> the type of the entity id
 */
public interface Identifiable<I extends Serializable> {

	I getId();

	void setId(I id);
}
