package cz.stasimek.fakturaceeasypeasy.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AppStringUtilsTest {

	@Test
	public void camelCaseToWords_givenCamelCase_returns2Words() {
		assertThat(AppStringUtils.camelCaseToWords("CamelCase"), is("camel case"));
	}

	@Test
	public void camelCaseToWords_givenNull_returnsNull() {
		assertThat(AppStringUtils.camelCaseToWords(null), is(nullValue()));
	}

	@Test
	public void camelCaseToWords_givenEmptyString_returnsEmptyString() {
		assertThat(AppStringUtils.camelCaseToWords(""), is(""));
	}

	@Test
	public void camelCaseToWords_givenCamel_returnsWord() {
		assertThat(AppStringUtils.camelCaseToWords("Camel"), is("camel"));
	}

	@Test
	public void camelCaseToWords_givenA_returnsA() {
		assertThat(AppStringUtils.camelCaseToWords("A"), is("a"));
	}

	@Test
	public void camelCaseToWords_givenTheCamelCase_returns3Words() {
		assertThat(AppStringUtils.camelCaseToWords("theCamelCase"), is("the camel case"));
	}

}
