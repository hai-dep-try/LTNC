#!/bin/bash

chcp 65001 > /dev/null 2>&1

ROOT_DIR=$(pwd)
SRC_DIR="$ROOT_DIR/src"
BUILD_DIR="$ROOT_DIR/build"
LIB_DIR="$ROOT_DIR/lib"
JUNIT_JAR="$LIB_DIR/junit-platform-console-standalone-1.10.2.jar"

mkdir -p "$BUILD_DIR" "$LIB_DIR"

# Download JUnit standalone JAR if not present
if [ ! -f "$JUNIT_JAR" ]; then
    echo "Downloading JUnit 5 standalone JAR..."
    curl -L -o "$JUNIT_JAR" \
        "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar"
fi

# ========================================================
# BEFORE: Chạy test với code LỖI (before/)
# ========================================================
echo "==========================================="
echo "  PHASE 1: Test với code BUGGY (before/)"
echo "==========================================="
echo ""

# Copy before source + test + Main
cp "$SRC_DIR/before/GradeClassifier.java" "$SRC_DIR/GradeClassifier.java"

javac -encoding UTF-8 -cp "$JUNIT_JAR" -d "$BUILD_DIR" \
    "$SRC_DIR/GradeClassifier.java" \
    "$SRC_DIR/GradeClassifierTest.java" \
    "$SRC_DIR/Main.java"

echo "--- Main demo (buggy) ---"
java -Dfile.encoding=UTF-8 -cp "$BUILD_DIR" Main
echo ""

echo "--- JUnit Tests (expect some FAIL) ---"
java -Dfile.encoding=UTF-8 -jar "$JUNIT_JAR" \
    --class-path "$BUILD_DIR" \
    --scan-class-path \
    --details verbose
echo ""

# ========================================================
# AFTER: Chạy test với code ĐÃ SỬA (after/)
# ========================================================
echo "==========================================="
echo "  PHASE 2: Test với code FIXED (after/)"
echo "==========================================="
echo ""

# Copy fixed source
cp "$SRC_DIR/after/GradeClassifier.java" "$SRC_DIR/GradeClassifier.java"

javac -encoding UTF-8 -cp "$JUNIT_JAR" -d "$BUILD_DIR" \
    "$SRC_DIR/GradeClassifier.java" \
    "$SRC_DIR/GradeClassifierTest.java" \
    "$SRC_DIR/Main.java"

echo "--- Main demo (fixed) ---"
java -Dfile.encoding=UTF-8 -cp "$BUILD_DIR" Main
echo ""

echo "--- JUnit Tests (expect all PASS) ---"
java -Dfile.encoding=UTF-8 -jar "$JUNIT_JAR" \
    --class-path "$BUILD_DIR" \
    --scan-class-path \
    --details verbose

# Cleanup temp copy
rm -f "$SRC_DIR/GradeClassifier.java"
