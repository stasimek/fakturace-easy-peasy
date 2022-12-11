package cz.stasimek.fakturaceeasypeasy.util;

import cz.stasimek.fakturaceeasypeasy.entity.Invoice;

public class InvoiceNumberGenerator {

	private InvoiceNumberGenerator() {
	}

	public static String generate(String lastInvoiceNumber, int currentYear) {
		// First invoice at all
		if (lastInvoiceNumber == null) {
			return currentYear + "0001";
		}

		// First invoice this year
		if (!lastInvoiceNumber.startsWith(currentYear + "")) {
			if (lastInvoiceNumber.contains("-")) {
				return currentYear + "-0001";
			} else {
				return currentYear + "0001";
			}
		}

		// Non-first invoice this year
		String[] numberParts = lastInvoiceNumber.split("-");
		if (numberParts.length == 1) {
			return Long.parseLong(lastInvoiceNumber) + 1 + "";
		} else {
			return currentYear + "-" + String.format("%04d", Long.parseLong(numberParts[1]) + 1);
		}
	}
}
