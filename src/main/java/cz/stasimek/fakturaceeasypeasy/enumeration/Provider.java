package cz.stasimek.fakturaceeasypeasy.enumeration;

import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException;

public enum Provider {
	GITHUB,
	GOOGLE;

	public static Provider valueOfCaseInsensitive(String search) {
		for (Provider provider : values()) {
			if (provider.name().compareToIgnoreCase(search) == 0) {
				return provider;
			}
		}
		throw new ApplicationException(
				String.format("Provider %s not supported.", search)
		);
	}
}
