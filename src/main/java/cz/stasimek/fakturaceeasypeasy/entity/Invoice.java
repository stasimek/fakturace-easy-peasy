package cz.stasimek.fakturaceeasypeasy.entity;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
public class Invoice extends BaseEntity {

	private Long number;
}
