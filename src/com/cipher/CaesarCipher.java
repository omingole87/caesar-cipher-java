package com.cipher;

import java.util.ArrayList;
import java.util.List;

/**
 * Core engine for Caesar Cipher encryption, decryption, and cryptanalysis.
 */
public class CaesarCipher {

    // Standard English letter frequencies (A to Z) for auto-cracking / cryptanalysis
    private static final double[] ENGLISH_FREQUENCIES = {
        0.08167, 0.01492, 0.02782, 0.04253, 0.12702, // A, B, C, D, E
        0.02228, 0.02015, 0.06094, 0.06966, 0.00153, // F, G, H, I, J
        0.00772, 0.04025, 0.02406, 0.06749, 0.07507, // K, L, M, N, O
        0.01929, 0.00095, 0.05987, 0.06327, 0.09056, // P, Q, R, S, T
        0.02758, 0.00978, 0.02360, 0.00150, 0.01974, // U, V, W, X, Y
        0.00074                                      // Z
    };

    /**
     * Encrypts plaintext using a given shift value.
     * Preserves casing and keeps non-alphabetic characters (spaces, numbers, symbols) unchanged.
     *
     * @param plainText The message to encrypt
     * @param shift     The shift key (positive, negative, or > 26)
     * @return The encrypted ciphertext
     */
    public static String encrypt(String plainText, int shift) {
        if (plainText == null) {
            return null;
        }

        // Normalize shift to 0..25 range
        int normalizedShift = ((shift % 26) + 26) % 26;
        StringBuilder result = new StringBuilder(plainText.length());

        for (char ch : plainText.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char shifted = (char) ('A' + (ch - 'A' + normalizedShift) % 26);
                result.append(shifted);
            } else if (Character.isLowerCase(ch)) {
                char shifted = (char) ('a' + (ch - 'a' + normalizedShift) % 26);
                result.append(shifted);
            } else {
                // Keep spaces, punctuation, digits intact
                result.append(ch);
            }
        }

        return result.toString();
    }

    /**
     * Decrypts ciphertext using the known shift key.
     *
     * @param cipherText The message to decrypt
     * @param shift      The original shift key used to encrypt
     * @return The decrypted plaintext
     */
    public static String decrypt(String cipherText, int shift) {
        // Decryption is simply shifting backwards by the key
        return encrypt(cipherText, -shift);
    }

    /**
     * Brute-forces all 25 possible shift keys and returns each result.
     *
     * @param cipherText The message to inspect
     * @return List of formatted strings showing [Key X]: <Candidate Plaintext>
     */
    public static List<String> bruteForce(String cipherText) {
        List<String> results = new ArrayList<>();
        for (int shift = 1; shift < 26; shift++) {
            String candidate = decrypt(cipherText, shift);
            results.add(String.format("Shift %2d: %s", shift, candidate));
        }
        return results;
    }

    /**
     * Analyzes ciphertext using English letter frequency analysis to automatically
     * predict the most probable shift key and cracked plaintext.
     *
     * @param cipherText The encrypted message
     * @return A CrackResult containing the predicted key, score, and cracked text
     */
    public static CrackResult autoCrack(String cipherText) {
        int bestShift = 1;
        double bestScore = Double.NEGATIVE_INFINITY;
        String bestText = "";

        for (int shift = 1; shift < 26; shift++) {
            String candidate = decrypt(cipherText, shift);
            double score = scoreEnglishText(candidate);
            if (score > bestScore) {
                bestScore = score;
                bestShift = shift;
                bestText = candidate;
            }
        }

        return new CrackResult(bestShift, bestText, bestScore);
    }

    /**
     * Calculates a likelihood score for candidate text based on expected English frequencies.
     */
    private static double scoreEnglishText(String text) {
        double score = 0.0;
        int letterCount = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char lower = Character.toLowerCase(ch);
                int index = lower - 'a';
                score += ENGLISH_FREQUENCIES[index];
                letterCount++;
            }
        }

        return letterCount == 0 ? 0.0 : score;
    }

    /**
     * Record representing the result of an auto-crack operation.
     */
    public record CrackResult(int predictedKey, String crackedText, double score) {}
}
