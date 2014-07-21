package de.metafinanz.mam.backend.repository.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenGeneratorTest {

	@Test
	public void testCreateToken() {
		assertNotNull(TokenGenerator.createToken());
	}
	
	@Test
	public void testTokenLangth() {
		
		int expected = 26;
		assertEquals("Der Token muss eine Länge von "+expected+" Zeichen haben.", expected, TokenGenerator.createToken().length());
	}

}
