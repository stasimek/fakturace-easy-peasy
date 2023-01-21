package cz.stasimek.fakturaceeasypeasy.enumeration

enum class Currency(public val nameCs: String) {
    CZK("Kč"),
    EUR("EUR"),
    USD("USD");

    companion object {
        fun valuesMap(): Map<String, String> {
            return values()
                .map { it.name to it.nameCs }
                .toMap(LinkedHashMap())
        }
    }
}