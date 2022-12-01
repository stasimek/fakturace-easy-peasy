package cz.stasimek.fakturaceeasypeasy.enumeration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
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

	public static Map<String, String> valuesMap() {
		return Arrays.stream(VatType.values())
				.map((VatType v) -> new String[]{v.name(), v.getNameCs()})
				.collect(Collectors.toMap(p -> p[0], p -> p[1], (x, y) -> y, LinkedHashMap::new));
	}
}
