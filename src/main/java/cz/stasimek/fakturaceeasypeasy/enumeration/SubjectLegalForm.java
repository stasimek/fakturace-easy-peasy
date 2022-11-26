package cz.stasimek.fakturaceeasypeasy.enumeration;

import lombok.Getter;

@Getter
public enum SubjectLegalForm {
	INDIVIDUAL("Fyzická osoba"),
	LEGAL_ENTITY("Právnická osoba");

	private final String nameCs;

	SubjectLegalForm(String nameCs) {
		this.nameCs = nameCs;
	}
}
