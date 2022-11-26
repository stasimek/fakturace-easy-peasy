package cz.stasimek.fakturaceeasypeasy.entity;

import cz.stasimek.fakturaceeasypeasy.enumeration.Country;
import cz.stasimek.fakturaceeasypeasy.enumeration.Currency;
import cz.stasimek.fakturaceeasypeasy.enumeration.SubjectLegalForm;
import cz.stasimek.fakturaceeasypeasy.enumeration.SubjectType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Subject extends BaseEntity {

	@NotNull
	@ManyToOne(optional = false)
	private User user;

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private SubjectType type;

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private SubjectLegalForm legalForm;

	@Size(max = 255)
	@Column(length = 255)
	private String companyName;

	@Size(max = 100)
	@Column(length = 100)
	private String street;

	@Size(max = 100)
	@Column(length = 100)
	private String city;

	// PSČ
	@Size(max = 10)
	@Column(length = 10)
	private String zip;

	@Column(length = 3)
	@Enumerated(EnumType.STRING)
	private Country country;

	// IČO
	@Size(max = 20)
	@Column(length = 20)
	private String companyNumber;

	// DIČ
	@Size(max = 20)
	@Column(length = 20)
	private String vatNumber;

	// Výchozí doba splatnosti faktury pro daný subjekt
	@Column
	private Integer defaultDuePeriod;

	@Column(length = 3)
	@Enumerated(EnumType.STRING)
	private Currency defaultCurrency;
}
