package cz.stasimek.fakturaceeasypeasy.enumeration

enum class SubjectLegalForm(private val nameCs: String) {
    INDIVIDUAL("Fyzická osoba"),
    LEGAL_ENTITY("Právnická osoba");

    companion object {
        fun valuesMap(): Map<String, String> {
            return values()
                .map { it.name to it.nameCs }
                .toMap(LinkedHashMap())
        }
    }
}