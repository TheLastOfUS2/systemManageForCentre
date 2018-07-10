package com.systemManage.common.utils;

import java.security.Key;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 * 加密解密工具
 * @author Administrator
 *
 */
public class SecurityUtil {
	private static final SecurityUtil instance = new SecurityUtil();
	private static final String KEY = "systemManage";
	
	/**
	 *方法名:encryptAES
	 *返回值:String
	 *作     用:加密算法
	 *参     数:@param content
	 *参     数:@return
	 *作     者:夏胜春
	 *日     期:2014年11月21日 下午4:16:19
	 */
	public static String encryptAES(String content){
		try{
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(KEY);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return asHex(result); // 加密
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *方法名:decryptAES
	 *返回值:String
	 *作     用:解密算法
	 *参     数:@param content
	 *参     数:@return
	 *作     者:夏胜春
	 *日     期:2014年11月21日 下午4:16:31
	 */
	public static String decryptAES(String content){
		try{
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(KEY);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(asBytes(content));
			return new String(result); // 加密
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将2进制数值转换为16进制字符串
	 * @param buf
	 * @return
	 */
	private static String asHex(byte buf[]){
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++){
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}
    
	
	/**
	 * 将16进制转换
	 * @param hexStr
	 * @return
	 */
	private static byte[] asBytes(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++){
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	/**
	 *方法名:initKeyForAES
	 *返回值:Key
	 *作     用:初始化key
	 *参     数:@param key
	 *参     数:@return
	 *参     数:@throws NoSuchAlgorithmException
	 *作     者:夏胜春
	 *日     期:2014年11月21日 下午4:16:42
	 */
	private static Key initKeyForAES(String key) throws NoSuchAlgorithmException {
		if (null == key || key.length() == 0) {
			throw new NullPointerException("key not is null");
		}
		SecretKeySpec key2 = null;
		
		//改进后代码
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
		
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			//改进后代码
			kgen.init(128, random);
			//原代码解密时有问题, javax.crypto.BadPaddingException: Given final block not properly padded
			//kgen.init(128, key.getBytes());
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			key2 = new SecretKeySpec(enCodeFormat, "AES");
		} catch (NoSuchAlgorithmException ex) {
			throw new NoSuchAlgorithmException();
		}
		return key2;

	}
	private SecurityUtil() {
	}
	public static SecurityUtil getInstance() {
		return instance;
	}

}
