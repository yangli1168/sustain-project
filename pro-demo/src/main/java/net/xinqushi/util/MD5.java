package net.xinqushi.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class MD5 {
	public static String getMd5(String password, String randomKey) throws NoSuchAlgorithmException {
		String md5 = getMd5(password);
		return getMd5(md5 + randomKey);
	}

	public static String getMd5(String source) throws NoSuchAlgorithmException {
		MessageDigest s = MessageDigest.getInstance("MD5");
		try {
			s.update(source.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		byte[] bytes = s.digest();
		return StringBytes.toHexString(bytes);
	}

	public static String getSaltPwd() throws NoSuchAlgorithmException {
		String randomCode = new RandomValidateCode().getRandomCode();
		// System.out.println("randomCode = " + randomCode);

		String md5 = getMd5(randomCode);
		// System.out.println("转换md5码: " + md5);

		String hashed = BCrypt.hashpw(md5, BCrypt.gensalt(12));
		// System.out.println("after gensalt: " + hashed);

		return hashed;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		getSaltPwd();

	}
}
