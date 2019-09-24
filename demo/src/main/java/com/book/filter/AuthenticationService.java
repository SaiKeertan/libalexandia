package com.book.filter;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.tomcat.util.codec.binary.Base64;


public class AuthenticationService {

	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String token1 = tokenizer.nextToken();
		final String token2 = tokenizer.nextToken();

		return BillingEnumCodes.USERNAME.getCode().equals(token1) && BillingEnumCodes.PASSWORD.getCode().equals(token2);
	}

}