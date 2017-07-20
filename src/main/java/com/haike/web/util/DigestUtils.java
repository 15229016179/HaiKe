package com.haike.web.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class DigestUtils {

	public static String sha1(String content) {
		return digest(content, "SHA1");
	}

	public static String sha256(String content) {
		return digest(content, "SHA-256");
	}

	public static String digest(String content, String algorithm) {
		return digest(content, algorithm, 1);
	}

	public static String digest(String content, String algorithm, int iterations) {
		byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
		return DatatypeConverter.printHexBinary(digest(bytes, algorithm, iterations));
	}

	public static byte[] digest(byte[] content, String algorithm, int iterations) {
		if (iterations < 1) {
			throw new IllegalArgumentException("iterations must be greater than 0");
		}

		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] value = content;
			for (int i = 0; i < iterations; i++) {
				value = md.digest(value);
			}
			return value;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}