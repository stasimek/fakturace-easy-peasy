package cz.stasimek.fakturaceeasypeasy.entity;

import cz.stasimek.fakturaceeasypeasy.enumeration.Country;
import cz.stasimek.fakturaceeasypeasy.enumeration.Currency;
import cz.stasimek.fakturaceeasypeasy.enumeration.CzNace;
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider;
import cz.stasimek.fakturaceeasypeasy.enumeration.SubjectLegalForm;
import cz.stasimek.fakturaceeasypeasy.enumeration.TaxOffice;
import cz.stasimek.fakturaceeasypeasy.enumeration.VatPeriod;
import cz.stasimek.fakturaceeasypeasy.enumeration.VatType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="`user`")
@Getter
@Setter
@Validated
public class User extends BaseEntity {

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Provider provider;

	@NotNull
	@NotBlank
	@Size(max = 255)
	@Column(length = 255, nullable = false)
	private String login;

	@Size(max = 255)
	@Column(length = 255)
	private String email;

	@Size(max = 255)
	@Column(length = 255)
	private String name;

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

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private VatType vatType;

	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private VatPeriod vatPeriod;

	@Column(length = 3)
	@Enumerated(EnumType.STRING)
	private Currency currency;

	// Finanční úřad - územní pracoviště
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private TaxOffice taxOffice;

	// E.g.: "Podnikatel zapsán v živ. rejstříku Městská část Praha 11"
	@Size(max = 255)
	@Column(length = 255)
	private String note;

	@Size(max = 20)
	@Column(length = 20)
	private String phone;

	// Hlavní činnost
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private CzNace czNace;

	@Size(max = 30)
	@Column(length = 30)
	private String bankName;

	@Size(max = 30)
	@Column(length = 30)
	private String bankAcountNumber;

	@Size(max = 34)
	@Column(length = 34)
	private String iban;

	// BIC (SWIFT)
	@Size(max = 11)
	@Column(length = 11)
	private String bic;

	// Výchozí doba splatnosti faktury
	@Column
	private Integer defaultDuePeriod = 14;
}
