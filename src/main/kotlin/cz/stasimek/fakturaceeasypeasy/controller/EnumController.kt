package cz.stasimek.fakturaceeasypeasy.controller

import cz.stasimek.fakturaceeasypeasy.enumeration.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class EnumController {

    @GetMapping("/enum/country")
    fun country(): Map<String, String> {
        return Country.valuesMap()
    }

    @GetMapping("/enum/currency")
    fun currency(): Map<String, String> {
        return Currency.valuesMap()
    }

    @GetMapping("/enum/cz-nace")
    fun czNace(): Map<String, String> {
        return CzNace.valuesMap()
    }

    @GetMapping("/enum/subject-legal-form")
    fun subjectLegalForm(): Map<String, String> {
        return SubjectLegalForm.valuesMap()
    }

    @GetMapping("/enum/subject-type")
    fun subjectType(): Map<String, String> {
        return SubjectType.valuesMap()
    }

    @GetMapping("/enum/tax-office")
    fun taxOffice(): Map<String, String> {
        return TaxOffice.valuesMap()
    }

    @GetMapping("/enum/vat-period")
    fun vatPeriod(): Map<String, String> {
        return VatPeriod.valuesMap()
    }

    @GetMapping("/enum/vat-type")
    fun vatType(): Map<String, String> {
        return VatType.valuesMap()
    }
}