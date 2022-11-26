package cz.stasimek.fakturaceeasypeasy.enumeration;

import lombok.Getter;

@Getter
public enum VatPeriod {
	M("Měsíčně"), // Monthly
	Q("Čtvrtletně"); // Quarterly

	private final String nameCs;

	VatPeriod(String nameCs) {
		this.nameCs = nameCs;
	}
}
