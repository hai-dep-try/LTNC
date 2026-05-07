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

echo "=== Compiling ==="
javac -encoding UTF-8 -cp "$JUNIT_JAR" -d "$BUILD_DIR" $(find "$SRC_DIR" -name "*.java")

echo ""
echo "=== Running Main (manual demo) ==="
java -Dfile.encoding=UTF-8 -cp "$BUILD_DIR" Main

echo ""
echo "=== Running JUnit Tests ==="
java -Dfile.encoding=UTF-8 -jar "$JUNIT_JAR" \
    --class-path "$BUILD_DIR" \
    --scan-class-path \
    --details verbose
