package cz.stasimek.fakturaceeasypeasy.service.interfaces;

import cz.stasimek.fakturaceeasypeasy.entity.BaseEntity;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public interface AppService<E extends BaseEntity> {

	@NotNull
	E findById(UUID id);
}
