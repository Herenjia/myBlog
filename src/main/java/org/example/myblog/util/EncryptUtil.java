package org.example.myblog.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    public class Util {
        public static String encrypt(String password) throws NoSuchAlgorithmException {
            String passwordMd5 = null;
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(password.getBytes(StandardCharsets.UTF_8));
                passwordMd5 = toHex(bytes);
            } catch (NoSuchAlgorithmException e) {
                throw e;
            }
            return passwordMd5;
        }

        public static String toHex(byte[] bytes) {
            final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
            StringBuilder ret = new StringBuilder(bytes.length * 2);
            for (byte aByte : bytes) {
                ret.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
                ret.append(HEX_DIGITS[aByte & 0x0f]);
            }
            return ret.toString();
        }
    }
}
