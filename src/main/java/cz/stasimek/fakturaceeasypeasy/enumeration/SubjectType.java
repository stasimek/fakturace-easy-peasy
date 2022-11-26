package cz.stasimek.fakturaceeasypeasy.enumeration;

import lombok.Getter;

@Getter
public enum SubjectType {
	CUSTOMER("Odběratel"),
	SUPPLIER("Dodavatel"),
	BOTH("Obojí");

	private final String nameCs;

	SubjectType(String nameCs) {
		this.nameCs = nameCs;
	}
}
