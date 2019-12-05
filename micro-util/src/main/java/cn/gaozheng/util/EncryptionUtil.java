package cn.gaozheng.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EncryptionUtil {

    private static final String MD5 = "MD5";
    private static final String SHA256 = "SHA-256";

    /**
     * 生成MD5摘要
     * @param str 被计算的字符
     * @return 摘要字符
     * @throws NoSuchAlgorithmException 未发现该算法实现
     */
    private static byte[] encrypt(byte[] str, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(str);
        return messageDigest.digest();
    }

    private static String toString(byte[] src) {
        return new BigInteger(1, src).toString(16);
    }

    public static String encryptMD5(String src, Charset charset) {
        return encryptMD5(src.getBytes(charset));
    }

    public static String encryptMD5(String src) {
        return encryptMD5(src, StandardCharsets.UTF_8);
    }

    public static String encryptMD5(byte[] src) {
        try {
            return toString(encrypt(src, MD5));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encryptSHA256(String src) {
        return toString(encryptSHA256(src.getBytes(StandardCharsets.UTF_8)));
    }

    public static byte[] encryptSHA256(byte[] src) {
        try {
            return encrypt(src, SHA256);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
