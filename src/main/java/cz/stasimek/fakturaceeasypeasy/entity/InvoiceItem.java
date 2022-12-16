package cz.stasimek.fakturaceeasypeasy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.validation.annotation.Validated;

@Entity
@SQLDelete(sql = "UPDATE APP.invoice_item SET deleted = true WHERE id = ?")
@Getter
@Setter
@Validated
public class InvoiceItem extends BaseEntity {

	@JsonBackReference
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
	@Column(nullable = false)
	private BigDecimal unitPrice;

	// Sazba DPH (%)
	@NotNull
	@Column(nullable = false)
	private BigDecimal vatRate;

}
