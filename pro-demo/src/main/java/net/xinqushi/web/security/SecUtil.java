package net.xinqushi.web.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

@Component
public class SecUtil {

	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
	        (byte) 0x10, (byte) 0x12, };

	private static String key;
	
	@SuppressWarnings("unused")
	private static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
	}

	private static String base64Encode(byte[] bytes) {
		return new String(Base64.encode(bytes));
	}

	private static final char[] PASSWORD = "N*j&js:2T11Iqwn<Q".toCharArray();

	private static String decrypt(String property) throws GeneralSecurityException, IOException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	private static byte[] base64Decode(String property) throws IOException {
		return Base64.decode(property.getBytes());
	}

	@PostConstruct
	public void convert() throws Exception {
		byte[] data = null;

		try {
			InputStream is = SecUtil.class.getResourceAsStream("/enc");
			int length = is.available();
			data = new byte[length];
			is.read(data);
		} catch (Exception e) {
		}

		String dataStr = new String(data);
		try {
			key = decrypt(dataStr);
		} catch (Exception e) {
		}

		if (key == null) {
			throw new Exception("Fail to retrieve key");
		}
	}

	public static String getKey() {
		return key;
	}

}