package cz.stasimek.fakturaceeasypeasy.enumeration;

import lombok.Getter;

@Getter
public enum Currency {
	CZK("Kč"),
	EUR("EUR"),
	USD("USD");

	private final String nameCs;

	Currency(String nameCs) {
		this.nameCs = nameCs;
	}
}
