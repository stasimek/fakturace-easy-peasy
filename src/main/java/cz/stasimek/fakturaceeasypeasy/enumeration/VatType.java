package cz.stasimek.fakturaceeasypeasy.enumeration;

import lombok.Getter;

@Getter
public enum VatType {
	NOT_VAT_PAYER("Neplátce DPH"),
	VAT_PAYER("Plátce DPH"),
	IDENTIFIED_PERSON("Identifikovaná osoba");

	private final String nameCs;

	VatType(String nameCs) {
		this.nameCs = nameCs;
	}
}
