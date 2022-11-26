package cz.stasimek.fakturaceeasypeasy.entity;

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
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
public class Invoice extends BaseEntity {

	@NotNull
	@ManyToOne(optional = false)
	private User user;

	// Číslo faktury
	@Size(max = 20)
	@Column(length = 20)
	private String number;

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private InvoiceType type;

	@NotNull
	@ManyToOne(optional = false)
	private Subject subject;

	// Datum vystavení
	@Column(nullable = false)
	private LocalDate issueDate;

	// Datum znanitelného plnění
	@Column
	private LocalDate taxableSupplyDate;

	// Datum splatnosti
	@Column(nullable = false)
	private LocalDate dueDate;

	// Datum přijetí - if InvoiceType = RECEIVED
	@Column(nullable = false)
	private LocalDate receiveDate;

	// Variabilní symbol
	@Column
	private Long variableSymbol;

	@Column(length = 3)
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@NotNull
	@Column(nullable = false)
	private BigDecimal exchangeRate;

	// Číslo objednávky
	@Size(max = 20)
	@Column(length = 20)
	private String orderNumber;

	@Size(max = 255)
	@Column(length = 255)
	private String description;

	@OneToMany(mappedBy = "invoice")
	private List<InvoiceItem> items;
}
