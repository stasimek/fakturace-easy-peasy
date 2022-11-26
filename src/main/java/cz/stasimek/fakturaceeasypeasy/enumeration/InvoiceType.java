package cz.stasimek.fakturaceeasypeasy.enumeration;

import lombok.Getter;

@Getter
public enum InvoiceType {
	ISSUED, // Vydaná faktura
	RECEIVED; // Přijatá faktura
}
