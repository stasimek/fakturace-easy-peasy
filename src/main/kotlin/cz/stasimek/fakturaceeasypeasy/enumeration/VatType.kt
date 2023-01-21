package cz.stasimek.fakturaceeasypeasy.enumeration

enum class VatType(private val nameCs: String) {
    NOT_VAT_PAYER("Neplátce DPH"),
    VAT_PAYER("Plátce DPH"),
    IDENTIFIED_PERSON("Identifikovaná osoba");

    companion object {
        fun valuesMap(): Map<String, String> {
            return values()
                .map { it.name to it.nameCs }
                .toMap(LinkedHashMap())
        }
    }
}