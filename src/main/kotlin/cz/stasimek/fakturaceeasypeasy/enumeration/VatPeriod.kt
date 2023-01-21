package cz.stasimek.fakturaceeasypeasy.enumeration

enum class VatPeriod(private val nameCs: String) {
    M("Měsíčně"), // Monthly
    Q("Čtvrtletně"); // Quarterly

    companion object {
        fun valuesMap(): Map<String, String> {
            return values()
                .map { it.name to it.nameCs }
                .toMap(LinkedHashMap())
        }
    }
}