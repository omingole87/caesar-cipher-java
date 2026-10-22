<div align="center">

# 🕵️ Caesar Cipher Suite in Java

**A modern, robust, and interactive classical cryptography suite with cryptanalysis & auto-cracking.**

[![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

</div>

---

## 📖 About The Project

The **Caesar Cipher Suite** is an implementation of classical substitution cryptography built using modern Java. It provides encryption, decryption, brute-force search across all possible shift keys, and an **automated frequency-analysis cracking engine** (Detective Mode) that decodes secret messages without needing the key.

### 🎯 Key Highlights:
- **🔤 Case & Character Preservation:** Preserves uppercase and lowercase casing, spaces, digits, punctuation, and emojis.
- **🔄 Robust Modular Arithmetic:** Seamlessly handles positive, negative, and large shifts ($> 26$) using mathematical normalization:
  $$\text{normalizedShift} = ((\text{shift} \pmod{26}) + 26) \pmod{26}$$
- **🕵️ Detective Auto-Crack:** Implements statistical English letter frequency analysis to automatically calculate the most probable plaintext and key.
- **⚡ Zero External Dependencies:** Pure Java Standard Edition.
- **🧪 Fully Tested:** Comprehensive test suite covering boundary wraparounds, negative shifts, and frequency prediction.

---

## 🧠 How the Caesar Cipher Works

Each alphabet character is mapped to an index ($A=0, B=1, \dots, Z=25$):

```
Plaintext Alphabet:   A  B  C  D  E  F ... X  Y  Z
Shift by 3 (Key = 3): D  E  F  G  H  I ... A  B  C
```

### Mathematical Formulas:
- **Encryption:**
  $$E_n(x) = (x + n) \pmod{26}$$
- **Decryption:**
  $$D_n(x) = (x - n) \pmod{26}$$

```
Example:
  Input:       "Attack at Dawn! 🕵️" (Shift = 3)
  Output:      "Dwwdfn dw Gdzq! 🕵️"
```

---

## 📂 Project Structure

```
caesar-cipher-java/
├── .github/
│   └── workflows/
│       └── ci.yml             # GitHub Actions CI build & test pipeline
├── src/
│   └── com/cipher/
│       ├── CaesarCipher.java   # Core cipher engine & frequency analysis
│       ├── Main.java           # Interactive CLI menu
│       └── CipherDemoTest.java # Automated verification suite
├── .gitignore                  # Git ignore rules for Java
├── build.bat                   # 1-Click Windows build script
├── LICENSE                     # MIT License
└── README.md                   # Project documentation
```

---

## 🚀 Quick Start

### Prerequisites
- **JDK 17+** (JDK 21 or later recommended)
- Verify installation: `java -version` and `javac -version`

### 1. Clone the Repository
```bash
git clone https://github.com/omingole87/caesar-cipher-java.git
cd caesar-cipher-java
```

### 2. Build the Project

#### On Windows:
```cmd
build.bat
```

#### Or via Command Line:
```bash
# Compile classes into bin/
javac -d bin src/com/cipher/*.java

# Package executable JAR
jar --create --file caesar-cipher.jar --main-class com.cipher.Main -C bin .
```

### 3. Run the Interactive Suite

```bash
java -jar caesar-cipher.jar
```

Or run directly from bytecode:
```bash
java -cp bin com.cipher.Main
```

---

## 💻 Interactive Menu Preview

```text
============================================================
       🕵️  CAESAR CIPHER ENCRYPTION & ANALYSIS SUITE  🕵️     
============================================================
  Learn how classical cryptography works with Java!
============================================================

Menu Options:
  [1] Encrypt a message (Lock with a key)
  [2] Decrypt a message (Unlock with a known key)
  [3] Auto-crack unknown cipher (Detective mode: Frequency Analysis)
  [4] Brute-force all 25 shift variants
  [5] Exit
```

---

## 🧪 Running Automated Tests

To run the verification test suite:

```bash
java -cp bin com.cipher.CipherDemoTest
```

**Verification Results:**
```text
Running Caesar Cipher automated test verification...

✔ Test 1 Passed: Classic uppercase shift of 3 -> KHOOR ZRUOG
✔ Test 2 Passed: Mixed casing & punctuation preserved -> Khoor, Zruog! 123
✔ Test 3 Passed: Large shift key wrap (>26) -> DEF
✔ Test 4 Passed: Negative shift handling -> ABC
✔ Test 5 Passed: Boundary wraparound (XYZ -> ABC) -> ABCabc
✔ Test 6 Passed: Detective Frequency Auto-Crack accurately cracked key 7!

------------------------------------------------------------
Results: 6 / 6 tests passed successfully!
------------------------------------------------------------
```

---

## 🧩 Programmatic Java API

You can easily integrate `CaesarCipher` into your own Java projects:

```java
import com.cipher.CaesarCipher;

public class Example {
    public static void main(String[] args) {
        // 1. Encrypt with any key (positive, negative, or large)
        String secret = CaesarCipher.encrypt("Top Secret Message!", 5);
        System.out.println(secret); // "Ytu Xjhwjy Rjxxflj!"

        // 2. Decrypt with the key
        String plain = CaesarCipher.decrypt(secret, 5);
        System.out.println(plain);  // "Top Secret Message!"

        // 3. Auto-crack unknown encrypted text
        CaesarCipher.CrackResult crack = CaesarCipher.autoCrack(secret);
        System.out.println("Predicted Key: " + crack.predictedKey());
        System.out.println("Cracked Text:  " + crack.crackedText());
    }
}
```

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'feat: Add AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the **MIT License**. See [`LICENSE`](LICENSE) for more information.

---

<div align="center">
Crafted with ❤️ by <a href="https://github.com/omingole87">Om Ingole</a>
</div>
