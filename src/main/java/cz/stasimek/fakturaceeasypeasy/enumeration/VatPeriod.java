package cz.stasimek.fakturaceeasypeasy.enumeration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public enum VatPeriod {
	M("Měsíčně"), // Monthly
	Q("Čtvrtletně"); // Quarterly

	private final String nameCs;

	VatPeriod(String nameCs) {
		this.nameCs = nameCs;
	}

	public static Map<String, String> valuesMap() {
		return Arrays.stream(VatPeriod.values())
				.map((VatPeriod v) -> new String[]{v.name(), v.getNameCs()})
				.collect(Collectors.toMap(p -> p[0], p -> p[1], (x, y) -> y, LinkedHashMap::new));
	}
}
