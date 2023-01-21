package cz.stasimek.fakturaceeasypeasy.enumeration

enum class SubjectType(private val nameCs: String) {
    CUSTOMER("Odběratel"),
    SUPPLIER("Dodavatel"),
    BOTH("Obojí");

    companion object {
        fun valuesMap(): Map<String, String> {
            return values()
                .map { it.name to it.nameCs }
                .toMap(LinkedHashMap())
        }
    }
}