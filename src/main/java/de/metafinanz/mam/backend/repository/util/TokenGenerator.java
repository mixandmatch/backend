package de.metafinanz.mam.backend.repository.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGenerator {
	
	public static String createToken() {
		SecureRandom random = new SecureRandom();

		String token = new BigInteger(130, random).toString(32);
		
		return token;
	}

}
