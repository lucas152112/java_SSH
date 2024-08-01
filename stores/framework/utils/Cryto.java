package com.oim.stores.framework.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密解密公共类
 * 
 * @author oim
 */
public class Cryto {
	
	static Logger logger = Logger.getLogger(Cryto.class);
	private static String algorithm_DESede = "DESede";
	private static String default_charset = "UTF-8";

	/**
	 * 获得一个密钥
	 * @return 密钥
	 */
	public static String generateKey() {
		byte abyte0[] = generateKeyByteArr();
		return byteArrayToHexString(abyte0).toUpperCase();
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串
	 * @param buf byte数组
	 * @return 字符串
	 */
	public static String byteArrayToHexString(byte buf[]) {
		try {
			int iLen = buf.length;
			StringBuffer sb = new StringBuffer(iLen * 2);
			for (int i = 0; i < iLen; i++) {
				int intTmp;
				for (intTmp = buf[i]; intTmp < 0; intTmp += 256)
					;
				if (intTmp < 16)
					sb.append("0");
				sb.append(Integer.toString(intTmp, 16));
			}

			return sb.toString().toUpperCase();
		} catch (Exception e) {
			logger.info("将byte数组转换为表示16进制值的字符串时出错!@" + e.getMessage());
		}
		return null;
	}

	/**
	 * 生成密钥
	 * @return
	 */
	private static byte[] generateKeyByteArr() {
		SecretKey secretkey;
		SecureRandom securerandom = new SecureRandom();
		KeyGenerator keygenerator;
		try {
			keygenerator = KeyGenerator.getInstance(algorithm_DESede);
			keygenerator.init(securerandom);
			secretkey = keygenerator.generateKey();
			return secretkey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			logger.info("生成密钥KEY异常:" + e.getMessage());
			return null;
		}

	}

	/**
	 * 生成签名串
	 * 
	 * @param source
	 *            需要签名源串
	 * @param key
	 *            密钥值
	 * @return 输出为字符的签名串
	 */
	public static String generateAuthenticator(String source, String key) {
		return generateAuthenticator(source, key, default_charset);
	}

	/**
	 * 生成签名串
	 * 
	 * @param source
	 * @param key
	 * @param charset
	 * @return
	 */
	public static String generateAuthenticator(String source, String key, String charset) {
		String s2 = null;
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
			messagedigest.update(source.getBytes(charset));
			byte abyte0[] = messagedigest.digest();
			// logger.info(ISOUtil.hexdump(abyte0));
			byte abyte1[] = Hex.decode(key);
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS7Padding", "BC");
			cipher.init(1, new SecretKeySpec(abyte1, algorithm_DESede), new IvParameterSpec(Hex.decode("0102030405060708")));
			byte abyte2[] = cipher.doFinal(abyte0);
			// logger.info(ISOUtil.hexdump(abyte2));
			s2 = base64Encode(abyte2);
		} catch (Exception exception) {
			logger.info("生成Authenticator认证信息时出错!@" + exception.getMessage());
		}
		return s2;
	}

	public static byte[] SHA1(String source, String charset) {
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
			messagedigest.update(source.getBytes(charset));
			byte abyte0[] = messagedigest.digest();
			return abyte0;
		} catch (Exception exception) {
			logger.info("SHA-1出错!@" + exception.getMessage());
			return null;
		}
	}

	/**
	 * 校验签名
	 * 
	 * @param key
	 *            密钥值
	 * @param source
	 *            签名源串
	 * @param si
	 *            签名值
	 * @return
	 */
	public static boolean validateAuthenticator(String key, String source, String si) {
		String s3 = generateAuthenticator(source, key);
		return si.equals(s3);
	}

	/**
	 * 3DES加密以BASE64方式输出
	 * 
	 * @param source
	 * @param key
	 * @return
	 */
	public static String encryptBase643DES(String source, String key) {
		return encryptBase643DES(source, key, default_charset);
	}

	public static String encryptBase643DES(String source, String key, String charset) {
		try {
			byte abyte1[];
			byte abyte0[] = Hex.decode(key);
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS7Padding", "BC");
			cipher.init(1, new SecretKeySpec(abyte0, algorithm_DESede), new IvParameterSpec(Hex.decode("0102030405060708")));
			abyte1 = cipher.doFinal(source.getBytes(charset));
			return base64Encode(abyte1);
		} catch (Exception e) {
			logger.info("对字符串进行3DES加密时出错!@" + e.getMessage());
			return null;
		}
	}

	public static String encrypt3DES(String source, String key) {
		try {
			byte abyte1[];
			byte abyte0[] = Hex.decode(key);
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("DESede/cbc/PKCS7Padding", "BC");
			cipher.init(1, new SecretKeySpec(abyte0, algorithm_DESede), new IvParameterSpec(Hex.decode("0102030405060708")));
			abyte1 = cipher.doFinal(source.getBytes("gbk"));
			return byteArrayToHexString(abyte1);
		} catch (Exception e) {
			logger.info("对字符串进行3DES加密时出错!@" + e.getMessage());
			return null;
		}
	}

	public static String encrypt3DES(byte[] source, String key) {
		try {
			byte abyte1[];
			byte abyte0[] = Hex.decode(key);
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("DESede/cbc/PKCS7Padding", "BC");
			cipher.init(1, new SecretKeySpec(abyte0, algorithm_DESede), new IvParameterSpec(Hex.decode("0102030405060708")));
			abyte1 = cipher.doFinal(source);
			return byteArrayToHexString(abyte1);
		} catch (Exception e) {
			logger.info("对字符串进行3DES加密时出错!@" + e.getMessage());
			return null;
		}
	}

	/**
	 * 3DES加密以BASE64方式输出的解密
	 * 
	 * @author oim
	 * @param source
	 * @param key
	 * @return
	 */
	public static String decryptBase643DES(String source, String key) {
		return decryptBase643DES(source, key, default_charset);
	}

	public static String decryptBase643DES(String source, String key, String charset) {
		try {
			byte abyte1[];
			byte abyte0[] = Hex.decode(key);
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS7Padding", "BC");
			cipher.init(2, new SecretKeySpec(abyte0, algorithm_DESede), new IvParameterSpec(Hex.decode("0102030405060708")));

			byte[] base64ed = base64Decode(source);
			if (base64ed == null)
				return null;

			abyte1 = cipher.doFinal(base64ed);
			return new String(abyte1, charset);
		} catch (Throwable e) {
			logger.info("对字符串进行3DES解密时出错!@");
		}

		return null;
	}

	public static String decrypt3DES(String source, String key) {
		try {
			byte abyte1[];
			byte abyte0[] = Hex.decode(key);
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS7Padding", "BC");
			cipher.init(2, new SecretKeySpec(abyte0, algorithm_DESede), new IvParameterSpec(Hex.decode("0102030405060708")));

			// byte[] base64ed = base64Decode(source);
			byte[] base64ed = Hex.decode(source);
			if (base64ed == null)
				return null;

			abyte1 = cipher.doFinal(base64ed);
			return new String(abyte1, "gbk");
		} catch (Throwable e) {
			logger.info("对字符串进行3DES解密时出错!@");
		}

		return null;
	}

	public static byte[] base64Decode(String str) {
		try {
			byte a[] = (new BASE64Decoder()).decodeBuffer(str);

			return a;
		} catch (Exception e) {
			logger.info("使用增强型BASE64编码格式对字符串进行解码时出错!@" + e.getMessage());
		}
		return null;
	}

	public static String base64Encode(byte b[]) {
		try {
			return (new BASE64Encoder()).encode(b);
		} catch (Exception e) {
			System.out.println("使用增强型BASE64编码格式对字节数组进行编码时出错!@" + e.getMessage());
		}
		return null;
	}

	/**
	 * 采用MD5对字符进行加密后返回(HEX格式)
	 * 
	 * @param str
	 *            源字符串
	 * @return String 返回加密后的字符串
	 * 
	 * 
	 */
	public static String cryptMD5ToHEX(String str) {
		return cryptMD5ToHEX(str, default_charset);
	}

	/**
	 * 采用MD5对字符进行加密后返回(HEX格式)
	 * 
	 * @param str
	 *            源字符串
	 * @return String 返回加密后的字符串
	 * 
	 * 
	 */
	public static String cryptMD5ToHEX(String str, String charset) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return byteArrayToHexString(md.digest(str.getBytes(charset)));
		} catch (NoSuchAlgorithmException e) {
			logger.info("" + e.getMessage());
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		String key = "DF2FBAF816F2F1326EF180D070A87A8638F1C26EC1269D2F";
		System.out.println("key:"+key);
		String source = "<OperationCode>1</OperationCode><RequestAmount>2</RequestAmount><RechargeItem>" +
				"<CardPin>592021102028123672</CardPin><SubId>0000001</SubId><RequestAmount>1</RequestAmount>" +
				"<SubRechargeItem><Sequence>1</Sequence><DestinationId>18905942050</DestinationId>" +
				"<DestinationAttr>2</DestinationAttr><ObjType>3</ObjType><RechargeUnit>0</RechargeUnit>" +
				"<RechargeAmount>1000</RechargeAmount></SubRechargeItem></RechargeItem><RechargeItem>" +
				"<CardPin>590221107393193740</CardPin><SubId>0000002</SubId><RequestAmount>1</RequestAmount>" +
				"<SubRechargeItem><Sequence>1</Sequence><DestinationId>059582027800</DestinationId>" +
				"<DestinationAttr>1</DestinationAttr><ObjType>3</ObjType><RechargeUnit>0</RechargeUnit>" +
				"<RechargeAmount>1000</RechargeAmount></SubRechargeItem></RechargeItem>";
		String authSource = "12345$100001002$1234567890123456$20120618181818";
		String auth = Cryto.cryptMD5ToHEX(Cryto.generateAuthenticator(authSource, key));
		System.out.println("认证码:"+auth);
		
		String jiami = Cryto.encryptBase643DES(source, key);
		System.out.println("加密后:"+jiami);
		String jiemi = Cryto.decryptBase643DES("DfKSswyd49uzEu5LWAdqgv4cpOVoWVxx4klQHqE9q2vOKjkyh8qAZQ5VLDHJ01XaXjl8/5TSiCABkNZjVW0qRRmLh/meoBjSmDi98sRx39BehGLE4ASilbpjwKg4hfsCnbjPg1KEMWs0kYxulmUVry6PtcderQBgsBp92fHAJELD2TdfgHFTTC46ZGzQvtO39bET9WgamWet6dH2U2/d4SUdN5TdvDHqEITljNx504dYipqGfe2Kx68fqevc2jjck3wFNenZxy0IFdDdCovEINcVBAiHUAQIHF8aqsVHsSqyh/b2/O3Z1+Lys0XgslZB6OTYWgFPgVcgk0LrhWDmsyQKnwF2p6PyFa1bbYyukm83tmCLD4MQFNVvpLHTDisgAlvB4CchQbe5zA60GjY6A9xi0PNy+fVuTKgmnDx1MCdYGG7LwxCuL/JKWcjjkV1XhznRBVcARp1vFuTxIk2sydhEJPzEMrsPJvV4VrvI+CzStnQIwLeU5EzA2RlIE5FQDlbMIfB4vl8WIMr+1RpsSEsxeJS/wNuONMnu4LvV6DGgIQCYPYzHZVccHcuaKj9Rcv9yu+1MhclEWSQ3WyHYbABJWXaXCVlk/4j5YYLugUgtv0kn7GxN/RPs/dHWPX2fv+kFW9AYWv9WSosTEskNymKsFnuDSmbbEDOJQ314TrpayW7hwGunBwRE+wctKze/889dAu1WBKBQgF/DeiOwUjBICC9OnTLoQtBa1N3kbuxNXRMvL4c49hRvMdaQMW/fhoJQRaXtpuRHXPnqhHoR5A==", key);
		System.out.println("解密后:"+jiemi);
		
	}
}
