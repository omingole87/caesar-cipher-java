package com.cipher;

/**
 * Automated test suite to verify correctness of encryption, decryption,
 * wrapping, negative shifts, punctuation preservation, and auto-crack.
 */
public class CipherDemoTest {

    public static void main(String[] args) {
        System.out.println("Running Caesar Cipher automated test verification...\n");

        int passed = 0;
        int total = 0;

        // Test 1: Classic Julius Caesar shift of 3
        total++;
        String p1 = "HELLO WORLD";
        String c1 = CaesarCipher.encrypt(p1, 3);
        if ("KHOOR ZRUOG".equals(c1)) {
            System.out.println("✅ Test 1 Passed: Classic uppercase shift of 3 -> " + c1);
            passed++;
        } else {
            System.err.println("❌ Test 1 Failed: Expected KHOOR ZRUOG but got " + c1);
        }

        // Test 2: Case preservation & punctuation
        total++;
        String p2 = "Hello, World! 123";
        String c2 = CaesarCipher.encrypt(p2, 3);
        String d2 = CaesarCipher.decrypt(c2, 3);
        if ("Khoor, Zruog! 123".equals(c2) && p2.equals(d2)) {
            System.out.println("✅ Test 2 Passed: Mixed casing & punctuation preserved -> " + c2);
            passed++;
        } else {
            System.err.println("❌ Test 2 Failed: Encrypted: " + c2 + ", Decrypted: " + d2);
        }

        // Test 3: Large shift key (shift 29 = shift 3)
        total++;
        String c3 = CaesarCipher.encrypt("ABC", 29);
        if ("DEF".equals(c3)) {
            System.out.println("✅ Test 3 Passed: Large shift key wrap (>26) -> " + c3);
            passed++;
        } else {
            System.err.println("❌ Test 3 Failed: Expected DEF but got " + c3);
        }

        // Test 4: Negative shift (shift -3 on 'DEF' -> 'ABC')
        total++;
        String c4 = CaesarCipher.encrypt("DEF", -3);
        if ("ABC".equals(c4)) {
            System.out.println("✅ Test 4 Passed: Negative shift handling -> " + c4);
            passed++;
        } else {
            System.err.println("❌ Test 4 Failed: Expected ABC but got " + c4);
        }

        // Test 5: End-of-alphabet wraparound ('XYZ' shifted by 3 -> 'ABC')
        total++;
        String c5 = CaesarCipher.encrypt("XYZxyz", 3);
        if ("ABCabc".equals(c5)) {
            System.out.println("✅ Test 5 Passed: Boundary wraparound (XYZ -> ABC) -> " + c5);
            passed++;
        } else {
            System.err.println("❌ Test 5 Failed: Expected ABCabc but got " + c5);
        }

        // Test 6: Auto-crack using frequency analysis
        total++;
        String secretOriginal = "The quick brown fox jumps over the lazy dog and discovers secret orders";
        String secretEncrypted = CaesarCipher.encrypt(secretOriginal, 7);
        CaesarCipher.CrackResult crackResult = CaesarCipher.autoCrack(secretEncrypted);
        if (crackResult.predictedKey() == 7 && secretOriginal.equals(crackResult.crackedText())) {
            System.out.println("✅ Test 6 Passed: Detective Frequency Auto-Crack accurately cracked key 7!");
            passed++;
        } else {
            System.err.println("❌ Test 6 Failed: Predicted key " + crackResult.predictedKey() + " instead of 7");
        }

        System.out.println("\n------------------------------------------------------------");
        System.out.println("Results: " + passed + " / " + total + " tests passed successfully!");
        System.out.println("------------------------------------------------------------");
    }
}
