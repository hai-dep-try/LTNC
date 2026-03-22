#!/bin/bash

# 1. Xác định thư mục hiện tại (thư mục chứa file run.sh)
ROOT_DIR=$(pwd)

# 2. Định nghĩa đường dẫn tới thư mục chứa code và thư mục chứa file đã biên dịch
SRC_DIR="$ROOT_DIR/src"
BUILD_DIR="$ROOT_DIR/build"

# 3. Tạo thư mục build nếu nó chưa tồn tại
mkdir -p "$BUILD_DIR"

# 4. Biên dịch tất cả các file .java trong thư mục src và bỏ vào thư mục build
javac -d "$BUILD_DIR" "$SRC_DIR"/*.java

# 5. Chạy chương trình từ lớp có tên là Main
java -cp "$BUILD_DIR" Main