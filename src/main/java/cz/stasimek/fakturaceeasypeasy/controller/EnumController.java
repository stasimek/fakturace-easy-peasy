package cz.stasimek.fakturaceeasypeasy.controller;

import cz.stasimek.fakturaceeasypeasy.enumeration.Country;
import cz.stasimek.fakturaceeasypeasy.enumeration.Currency;
import cz.stasimek.fakturaceeasypeasy.enumeration.CzNace;
import cz.stasimek.fakturaceeasypeasy.enumeration.SubjectLegalForm;
import cz.stasimek.fakturaceeasypeasy.enumeration.SubjectType;
import cz.stasimek.fakturaceeasypeasy.enumeration.TaxOffice;
import cz.stasimek.fakturaceeasypeasy.enumeration.VatPeriod;
import cz.stasimek.fakturaceeasypeasy.enumeration.VatType;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnumController {

	@GetMapping("/enum/country")
	public Map<String, String> country() {
		for (Map.Entry<String, String> entry : Country.valuesMap().entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
		}

		return Country.valuesMap();
	}

	@GetMapping("/enum/currency")
	public Map<String, String> currency() {
		return Currency.valuesMap();
	}

	@GetMapping("/enum/cz-nace")
	public Map<String, String> czNace() {
		return CzNace.valuesMap();
	}

	@GetMapping("/enum/subject-legal-form")
	public Map<String, String> subjectLegalForm() {
		return SubjectLegalForm.valuesMap();
	}

	@GetMapping("/enum/subject-type")
	public Map<String, String> subjectType() {
		return SubjectType.valuesMap();
	}

	@GetMapping("/enum/tax-office")
	public Map<String, String> taxOffice() {
		return TaxOffice.valuesMap();
	}

	@GetMapping("/enum/vat-period")
	public Map<String, String> vatPeriod() {
		return VatPeriod.valuesMap();
	}

	@GetMapping("/enum/vat-type")
	public Map<String, String> vatType() {
		return VatType.valuesMap();
	}

}
