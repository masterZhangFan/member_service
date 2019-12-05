package cn.gaozheng.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomUtil {

    private static volatile RandomUtil randomUtil = null;

    public static RandomUtil getInstance() {
        if (randomUtil == null) {
            synchronized (RandomUtil.class) {
                randomUtil = new RandomUtil();
            }
        }

        return randomUtil;
    }

    private static final String SYMBOL_STR = "0123456789";
    private static final Random SECURE_RANDOM = new SecureRandom();

    public String flexibleRandom(int length) {
        char[] chars = new char[length];
        for (int index = 0; index < chars.length; ++index) {
            chars[index] = SYMBOL_STR.charAt(SECURE_RANDOM.nextInt(SYMBOL_STR.length()));
        }
        return new String(chars);
    }

}
