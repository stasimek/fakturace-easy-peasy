package cz.stasimek.fakturaceeasypeasy.enumeration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
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

	public static Map<String, String> valuesMap() {
		return Arrays.stream(Currency.values())
				.map((Currency c) -> new String[]{c.name(), c.getNameCs()})
				.collect(Collectors.toMap(p -> p[0], p -> p[1], (x, y) -> y, LinkedHashMap::new));
	}
}
