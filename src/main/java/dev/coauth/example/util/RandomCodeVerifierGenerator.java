package dev.coauth.example.util;

import java.security.SecureRandom;

public class RandomCodeVerifierGenerator {
    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomAlphanumeric() {
        int length=15;
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_CHARACTERS.length());
            char randomChar = ALPHANUMERIC_CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
