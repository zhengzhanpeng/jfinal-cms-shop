package com.gz.utils;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全相关的类，如加密解密、生成消息摘要信息
 * 
 */
public class SecurityUtil {
	/**
	 * 算法名称类
	 */
	public static class AtalgorithmName {
		public static final String MD2 = "MD2";
		public static final String MD5 = "MD5";
		public static final String SHA = "SHA";
		public static final String SHA_256 = "SHA-256";
		public static final String SHA_384 = "SHA-384";
		public static final String SHA_512 = "SHA-512";
		public static final String DES = "DES";
	}

	/**
	 * 获取指定算法的消息摘要
	 * 
	 * @param algorithm
	 *            算法名称,可以参考SecurityUtil.AtalgorithmName类定义的各种算法名称
	 * @return
	 */
	public static byte[] getDigest(byte[] data, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			return md.digest(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取指定算法的消息摘要
	 * 
	 * @param algorithm
	 *            算法名称,可以参考SecurityUtil.AtalgorithmName类定义的各种算法名称
	 * @return
	 */
	public static String getDigest(String str, String algorithm) {
		byte[] digest = getDigest(str.getBytes(), algorithm);
		return bytesToHex(digest);
	}
	
	public static String getMD5(String str) {
		return getDigest(str,AtalgorithmName.MD5);
	}
	
	/**
	 * 将字节数组转成16进制的String字符串。一个字节8位对应两个16进制的字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Character.forDigit((bytes[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(bytes[i] & 15, 16));
		}
		return sb.toString();
	}
}
