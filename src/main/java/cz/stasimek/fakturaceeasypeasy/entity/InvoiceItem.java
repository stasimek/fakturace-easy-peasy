package cz.stasimek.fakturaceeasypeasy.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
public class InvoiceItem extends BaseEntity {

	@NotNull
	@ManyToOne(optional = false)
	private Invoice invoice;

	@Column
	private BigDecimal quantity;

	@Size(max = 20)
	@Column(length = 20)
	private String unit;

	@NotNull
	@Size(max = 255)
	@Column(length = 255, nullable = false)
	private String description;

	@NotNull
	@Size(max = 100)
	@Column(length = 100, nullable = false)
	private String originalUnitPrice;

	@NotNull
	@Column(nullable = false)
	private BigDecimal unitPrice;

	// Sazba DPH (%)
	@NotNull
	@Column(nullable = false)
	private BigDecimal vatRate;

}
