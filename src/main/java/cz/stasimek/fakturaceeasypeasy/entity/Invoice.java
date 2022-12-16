package cz.stasimek.fakturaceeasypeasy.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.stasimek.fakturaceeasypeasy.entity.interfaces.HasUser;
import cz.stasimek.fakturaceeasypeasy.enumeration.Currency;
import cz.stasimek.fakturaceeasypeasy.enumeration.InvoiceType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.validation.annotation.Validated;

@Entity
@SQLDelete(sql = "UPDATE APP.invoice SET deleted = true WHERE id = ?")
@Getter
@Setter
@Validated
public class Invoice extends BaseEntity implements HasUser {

	@NotNull
	@ManyToOne(optional = false)
	private User user;

	// Číslo faktury
	@NotNull
	@Size(max = 20)
	@Column(length = 20, nullable = false)
	private String number;

	@NotNull
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private InvoiceType type;

	@NotNull
	@ManyToOne(optional = false)
	private Subject subject;

	// Datum vystavení
	@NotNull
	@Column(nullable = false)
	private LocalDate issueDate;

	// Datum znanitelného plnění
	@Column
	private LocalDate taxableSupplyDate;

	// Datum splatnosti
	@NotNull
	@Column(nullable = false)
	private LocalDate dueDate;

	// Datum přijetí - if InvoiceType = RECEIVED
	@Column
	private LocalDate receiveDate;

	// Variabilní symbol
	@Column
	private Long variableSymbol;

	@Column(length = 3)
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@Column
	private BigDecimal exchangeRate;

	// Číslo objednávky
	@Size(max = 20)
	@Column(length = 20)
	private String orderNumber;

	@Size(max = 255)
	@Column(length = 255)
	private String description;

	@JsonManagedReference
	@OneToMany(mappedBy = "invoice")
	@Where(clause = "deleted = false")
	private List<InvoiceItem> items;
}
