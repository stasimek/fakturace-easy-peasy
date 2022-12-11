package cz.stasimek.fakturaceeasypeasy.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class InvoiceNumberGeneratorTest {

	@Test
	public void generate_givenInvoiceNumber20210001AndCurrentYear2021_returns20210002() {
		assertThat(
				InvoiceNumberGenerator.generate("20210001", 2021),
				is("20210002")
		);
	}

	@Test
	public void generate_givenInvoiceNumber2021Dash0001AndCurrentYear2021_returns2021Dash0002() {
		assertThat(
				InvoiceNumberGenerator.generate("2021-0001", 2021),
				is("2021-0002")
		);
	}

	@Test
	public void generate_givenInvoiceNumber20210001AndCurrentYear2022_returns20220001() {
		assertThat(
				InvoiceNumberGenerator.generate("20210001", 2022),
				is("20220001")
		);
	}

	@Test
	public void generate_givenInvoiceNumber2021Dash0001AndCurrentYear2022_returns2022Dash0001() {
		assertThat(
				InvoiceNumberGenerator.generate("2021-0001", 2022),
				is("2022-0001")
		);
	}

	@Test
	public void generate_givenNullInvoiceNumberAndCurrentYear2025_returns20250001() {
		assertThat(
				InvoiceNumberGenerator.generate(null, 2025),
				is("20250001")
		);
	}

}
