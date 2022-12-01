package cz.stasimek.fakturaceeasypeasy.enumeration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public enum SubjectLegalForm {
	INDIVIDUAL("Fyzická osoba"),
	LEGAL_ENTITY("Právnická osoba");

	private final String nameCs;

	SubjectLegalForm(String nameCs) {
		this.nameCs = nameCs;
	}

	public static Map<String, String> valuesMap() {
		return Arrays.stream(SubjectLegalForm.values())
				.map((SubjectLegalForm s) -> new String[]{s.name(), s.getNameCs()})
				.collect(Collectors.toMap(p -> p[0], p -> p[1], (x, y) -> y, LinkedHashMap::new));
	}
}
