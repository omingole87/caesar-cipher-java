@echo off
echo ============================================================
echo   Building Caesar Cipher Suite (Java)
echo ============================================================

if not exist bin mkdir bin

echo [*] Compiling Java classes...
javac -d bin src\com\cipher\*.java
if %errorlevel% neq 0 (
    echo [X] Compilation failed.
    exit /b %errorlevel%
)

echo [*] Running automated tests...
java -cp bin com.cipher.CipherDemoTest
if %errorlevel% neq 0 (
    echo [X] Tests failed.
    exit /b %errorlevel%
)

echo [*] Packaging executable JAR...
jar --create --file caesar-cipher.jar --main-class com.cipher.Main -C bin .

echo ============================================================
echo [V] Build succeeded! Run with: java -jar caesar-cipher.jar
echo ============================================================
pause
