package com.joyintech.tams.svreport.pbc.common.util;

import java.io.*;
import java.security.SecureRandom;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Base64 编码和解码。
 * 
 * @author liushuai
 * @date 2019年08月29日
 */
@Component
public class Base64 {
    @Value("${application.chb.base64Key}")
    private String base64Key;

    private static String KEY;

    @PostConstruct
    public void setEndkey(){
        this.KEY = base64Key;
    }
//	private static String KEY = "HF*ts303";
//	public static void main(String[] args) {
//		String s = "HF*ts303";
//		String enc1;
//		String dec1;
//		//加密
//		enc1 = encryptData(s);
//		System.out.println(enc1);
//		//解密
//		dec1 = decryptData(enc1);
//		System.out.println(dec1);
//	}

	public Base64() {
	}

	/**
	 * 加密
	 * @param input
	 * @return
	 */
	public static String encryptData(String input) {
		try {
			SecureRandom sr = new SecureRandom();
			byte rawKeyData[] = KEY.getBytes();
			DESKeySpec dks = new DESKeySpec(rawKeyData);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);

			Cipher c = Cipher.getInstance("DES");
			c.init(Cipher.ENCRYPT_MODE, key, sr);
			byte[] cipherByte = c.doFinal(input.getBytes());
			String dec = new Base64Encoder().encode(cipherByte);
			return dec;

		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 解密
	 * @param input
	 * @return
	 */
	public static String decryptData(String input) {
		try {
			byte[] dec = new Base64Encoder().decode(input);

			SecureRandom sr = new SecureRandom();
			byte rawKeyData[] = KEY.getBytes();

			DESKeySpec dks = new DESKeySpec(rawKeyData);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);

			Cipher c = Cipher.getInstance("DES");
			c.init(Cipher.DECRYPT_MODE, key, sr);
			byte[] clearByte = c.doFinal(dec);

			return new String(clearByte);
		} catch (Exception e) {
		}
		return input;
	}

	/**
	 * 功能：编码字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(String data) {
		return new String(encode(data.getBytes()));
	}

	/**
	 * 功能：解码字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String decode(String data) {
		return new String(decode(data.toCharArray()));
	}

	/**
	 * 功能：编码byte[]
	 * 
	 * @param data
	 * @return
	 */
	public static char[] encode(byte[] data) {
		char[] out = new char[((data.length + 2) / 3) * 4];
		for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
			boolean quad = false;
			boolean trip = false;

			int val = (0xFF & (int) data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & (int) data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length) {
				val |= (0xFF & (int) data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;
	}

	/**
	 * 功能：解码
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] decode(char[] data) {

		int tempLen = data.length;
		for (int ix = 0; ix < data.length; ix++) {
			if ((data[ix] > 255) || codes[data[ix]] < 0) {
				--tempLen; // ignore non-valid chars and padding
			}
		}
		int len = (tempLen / 4) * 3;
		if ((tempLen % 4) == 3) {
			len += 2;
		}
		if ((tempLen % 4) == 2) {
			len += 1;
		}
		byte[] out = new byte[len];
		int shift = 0; // # of excess bits stored in accum
		int accum = 0; // excess bits
		int index = 0;
		for (int ix = 0; ix < data.length; ix++) {
			int value = (data[ix] > 255) ? -1 : codes[data[ix]];

			if (value >= 0) { // skip over non-code
				accum <<= 6; // bits shift up by 6 each time thru
				shift += 6; // loop, with new bits being put in
				accum |= value; // at the bottom.
				if (shift >= 8) { // whenever there are 8 or more shifted in,
					shift -= 8; // write them out (from the top, leaving any
					out[index++] = // excess at the bottom for next iteration.
					(byte) ((accum >> shift) & 0xff);
				}
			}
		}
		if (index != out.length) {
			throw new Error("Miscalculated data length (wrote " + index
					+ " instead of " + out.length + ")");
		}
		return out;
	}

	/**
	 * 功能：编码文件
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void encode(File file) throws IOException {
		if (!file.exists()) {
			return;
		}
		else {
			byte[] decoded = readBytes(file);
			char[] encoded = encode(decoded);
			writeChars(file, encoded);
		}
		file = null;
	}

	/**
	 * 功能：解码文件。
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void decode(File file) throws IOException {
		if (!file.exists()) {
			return;
		} else {
			char[] encoded = readChars(file);
			byte[] decoded = decode(encoded);
			writeBytes(file, decoded);
		}
		file = null;
	}

	private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
			.toCharArray();

	private static byte[] codes = new byte[256];
	static {
		for (int i = 0; i < 256; i++) {
			codes[i] = -1;
			// LoggerUtil.debug(i + "&" + codes[i] + " ");
		}
		for (int i = 'A'; i <= 'Z'; i++) {
			codes[i] = (byte) (i - 'A');
			// LoggerUtil.debug(i + "&" + codes[i] + " ");
		}

		for (int i = 'a'; i <= 'z'; i++) {
			codes[i] = (byte) (26 + i - 'a');
			// LoggerUtil.debug(i + "&" + codes[i] + " ");
		}
		for (int i = '0'; i <= '9'; i++) {
			codes[i] = (byte) (52 + i - '0');
			// LoggerUtil.debug(i + "&" + codes[i] + " ");
		}
		codes['+'] = 62;
		codes['/'] = 63;
	}

	private static byte[] readBytes(File file) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = null;
		InputStream fis = null;
		InputStream is = null;
		try {
			fis = new FileInputStream(file);
			is = new BufferedInputStream(fis);
			int count = 0;
			byte[] buf = new byte[16384];
			while ((count = is.read(buf)) != -1) {
				if (count > 0) {
					baos.write(buf, 0, count);
				}
			}
			b = baos.toByteArray();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (is != null)
					is.close();
				if (baos != null)
					baos.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return b;
	}

	private static char[] readChars(File file) throws IOException {
		CharArrayWriter caw = new CharArrayWriter();
		Reader fr = null;
		Reader in = null;
		try {
			fr = new FileReader(file);
			in = new BufferedReader(fr);
			int count = 0;
			char[] buf = new char[16384];
			while ((count = in.read(buf)) != -1) {
				if (count > 0) {
					caw.write(buf, 0, count);
				}
			}
		} finally {
			try {
				if (caw != null)
					caw.close();
				if (in != null)
					in.close();
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return caw.toCharArray();
	}

	private static void writeBytes(File file, byte[] data) throws IOException {
		OutputStream fos = null;
		OutputStream os = null;
		try {
			fos = new FileOutputStream(file);
			os = new BufferedOutputStream(fos);
			os.write(data);
		} finally {
			try {
				if (os != null)
					os.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private static void writeChars(File file, char[] data) throws IOException {
		Writer fos = null;
		Writer os = null;
		try {
			fos = new FileWriter(file);
			os = new BufferedWriter(fos);
			os.write(data);
		} finally {
			try {
				if (os != null)
					os.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}