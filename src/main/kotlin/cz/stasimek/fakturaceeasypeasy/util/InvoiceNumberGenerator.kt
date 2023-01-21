package cz.stasimek.fakturaceeasypeasy.util

object InvoiceNumberGenerator {

    fun generate(lastInvoiceNumber: String?, currentYear: Int): String {
        // First invoice at all
        if (lastInvoiceNumber == null) {
            return "${currentYear}0001"
        }

        // First invoice this year
        if (!lastInvoiceNumber.startsWith(currentYear.toString())) {
            if (lastInvoiceNumber.contains("-")) {
                return "$currentYear-0001"
            } else {
                return "${currentYear}0001"
            }
        }

        // Non-first invoice this year
        val numberParts = lastInvoiceNumber.split("-")
        if (numberParts.size == 1) {
            return (lastInvoiceNumber.toLong() + 1).toString()
        } else {
            return "$currentYear-" + String.format("%04d", numberParts[1].toLong() + 1)
        }
    }
}