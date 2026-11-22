
package com.studentmanagement;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordUtil {

    private static final int ITERATIONS = 600000;
    private static final int KEY_LENGTH = 256;
    private static final SecureRandom RANDOM =
            new SecureRandom();

    private PasswordUtil() {}

    public static String hash(String password) {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);

        byte[] derived = derive(
                password.toCharArray(), salt, ITERATIONS);

        return "pbkdf2_sha256$" + ITERATIONS + "$"
                + Base64.getEncoder().encodeToString(salt)
                + "$"
                + Base64.getEncoder().encodeToString(derived);
    }

    public static boolean verify(
            String password, String stored) {
        if (password == null || stored == null) {
            return false;
        }

        try {
            String[] parts = stored.split("\\$", -1);
            if (parts.length != 4
                    || !parts[0].equals("pbkdf2_sha256")) {
                return false;
            }

            int iterations = Integer.parseInt(parts[1]);
            if (iterations < 100000 || iterations > 2000000) {
                return false;
            }

            byte[] salt = Base64.getDecoder().decode(parts[2]);
            byte[] expected =
                    Base64.getDecoder().decode(parts[3]);

            if (salt.length != 16 || expected.length != 32) {
                return false;
            }

            byte[] actual = derive(
                    password.toCharArray(), salt, iterations);

            return MessageDigest.isEqual(actual, expected);
        } catch (Exception e) {
            return false;
        }
    }

    private static byte[] derive(
            char[] password, byte[] salt, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(
                password, salt, iterations, KEY_LENGTH);
        try {
            return SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256")
                    .generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Password hashing failed", e);
        } finally {
            spec.clearPassword();
        }
    }
}
