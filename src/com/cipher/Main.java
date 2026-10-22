package com.cipher;

import java.util.List;
import java.util.Scanner;

/**
 * Interactive command-line interface for the Caesar Cipher suite.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Select an option (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleEncrypt();
                case "2" -> handleDecrypt();
                case "3" -> handleAutoCrack();
                case "4" -> handleBruteForce();
                case "5" -> {
                    System.out.println("\n[👋] Exiting Caesar Cipher Suite. Stay safe, agent!\n");
                    running = false;
                }
                default -> System.out.println("\n[⚠️] Invalid option. Please choose between 1 and 5.\n");
            }

            if (running) {
                System.out.println("------------------------------------------------------------");
            }
        }
    }

    private static void printBanner() {
        System.out.println("============================================================");
        System.out.println("       🕵️  CAESAR CIPHER ENCRYPTION & ANALYSIS SUITE  🕵️     ");
        System.out.println("============================================================");
        System.out.println("  Learn how classical cryptography works with Java!");
        System.out.println("============================================================\n");
    }

    private static void printMenu() {
        System.out.println("Menu Options:");
        System.out.println("  [1] Encrypt a message (Lock with a key)");
        System.out.println("  [2] Decrypt a message (Unlock with a known key)");
        System.out.println("  [3] Auto-crack unknown cipher (Detective mode: Frequency Analysis)");
        System.out.println("  [4] Brute-force all 25 shift variants");
        System.out.println("  [5] Exit");
    }

    private static void handleEncrypt() {
        System.out.println("\n--- [🔒 Encrypt Message] ---");
        System.out.print("Enter message to encrypt: ");
        String text = scanner.nextLine();

        int shift = readShiftKey();
        String encrypted = CaesarCipher.encrypt(text, shift);

        System.out.println("\n>>> Encrypted Ciphertext: ");
        System.out.println("    " + encrypted);
        System.out.println(">>> (Normalized Shift Used: " + (((shift % 26) + 26) % 26) + ")\n");
    }

    private static void handleDecrypt() {
        System.out.println("\n--- [🔓 Decrypt Message] ---");
        System.out.print("Enter ciphertext to decrypt: ");
        String text = scanner.nextLine();

        int shift = readShiftKey();
        String decrypted = CaesarCipher.decrypt(text, shift);

        System.out.println("\n>>> Decrypted Plaintext: ");
        System.out.println("    " + decrypted + "\n");
    }

    private static void handleAutoCrack() {
        System.out.println("\n--- [🕵️ Detective Auto-Crack] ---");
        System.out.print("Enter mystery ciphertext: ");
        String text = scanner.nextLine();

        CaesarCipher.CrackResult result = CaesarCipher.autoCrack(text);

        System.out.println("\n>>> Detective Analysis Result:");
        System.out.println("    Predicted Key: " + result.predictedKey());
        System.out.println("    Confidence Score: " + String.format("%.4f", result.score()));
        System.out.println("    Cracked Message:  " + result.crackedText() + "\n");
    }

    private static void handleBruteForce() {
        System.out.println("\n--- [💥 Brute-Force All 25 Shifts] ---");
        System.out.print("Enter mystery ciphertext: ");
        String text = scanner.nextLine();

        List<String> variants = CaesarCipher.bruteForce(text);
        System.out.println("\nAll Possible Decryptions:");
        for (String line : variants) {
            System.out.println("  " + line);
        }
        System.out.println();
    }

    private static int readShiftKey() {
        while (true) {
            System.out.print("Enter shift key (integer, e.g. 3, -5, or 29): ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[⚠️] Invalid number. Please enter a valid integer.");
            }
        }
    }
}
