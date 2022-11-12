package cz.stasimek.fakturaceeasypeasy.entity;

import cz.stasimek.fakturaceeasypeasy.entity.interfaces.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@MappedSuperclass
@SQLDelete(sql = "UPDATE #{#entityName} SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "deleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :deleted")
@Getter
@Setter
public abstract class BaseEntity
		implements Identifiable<UUID>, Auditable, SoftDeletable, Serializable {

	@Id
	@Column(columnDefinition = "char(16) for bit data")
	@GeneratedValue
	private UUID id;

	@PastOrPresent
	@Column(nullable = false)
	private ZonedDateTime createdAt;

	@PastOrPresent
	@Column(nullable = false)
	private ZonedDateTime updatedAt;

	@Column
	private boolean deleted = false;

	@Override
	public String toString() {
		return String.format("%s (#%s)", getClass().getSimpleName(), id);
	}

	@PrePersist
	public void prePersist() {
		createdAt = updatedAt = ZonedDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = ZonedDateTime.now();
	}

}
