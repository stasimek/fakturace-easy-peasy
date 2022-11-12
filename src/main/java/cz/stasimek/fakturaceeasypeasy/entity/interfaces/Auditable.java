package cz.stasimek.fakturaceeasypeasy.entity.interfaces;

import java.time.ZonedDateTime;

public interface Auditable {

	ZonedDateTime getCreatedAt();

	void setCreatedAt(ZonedDateTime timestamp);

	ZonedDateTime getUpdatedAt();

	void setUpdatedAt(ZonedDateTime timestamp);

}
