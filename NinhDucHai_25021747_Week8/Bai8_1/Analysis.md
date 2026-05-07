# Bài 1: The smell hunter

## Đoạn A
- **Code Smell:** Poor Naming (Tên biến tối nghĩa) & Magic Number (Số ma thuật).
- **Giải thích:** Các biến `t`, `h`, `r`, `m`, `f` không biểu đạt ý nghĩa gì khiến code khó đọc. Con số `0.9` xuất hiện đột ngột không rõ ý nghĩa (là giảm giá 10%).
- **Kỹ thuật refactor:** `Rename Variable` (Đổi tên biến cho rõ nghĩa) và `Replace Magic Number with Symbolic Constant` (Khẳng định hằng số).

## Đoạn B
- **Code Smell:** God Class / Divergent Change / Vi phạm Single Responsibility Principle (SRP).
- **Giải thích:** Lớp `UserService` đảm nhận quá nhiều trách nhiệm (lấy dữ liệu User, gửi email, render giao diện, export file CSV). Nếu bất kỳ yêu cầu nào liên quan đến email, UI, hay export thay đổi, lớp này đều phải bị chỉnh sửa.
- **Kỹ thuật refactor:** `Extract Class` (Tách lớp). Tách riêng các chức năng tạo thành các lớp chuyên biệt (Ví dụ: `UserEmailService`, `UserProfileRenderer`, `UserExportService`).

## Đoạn C
- **Code Smell:** Switch Statements / Long if-else chain / Vi phạm Open-Closed Principle (OCP).
- **Giải thích:** Việc sử dụng một chuỗi `if-else` kiểm tra loại hình (`shapeType`) khiến code dễ sinh lỗi và phải thay đổi mỗi lần có một loại hình khối mới được thêm vào.
- **Kỹ thuật refactor:** `Replace Conditional with Polymorphism` (Thay thế câu lệnh điều kiện bằng Đa hình). Tạo interface `Shape` và các lớp con `Rectangle`, `Triangle`, `Circle` tự tính diện tích của mình.

## Đoạn D
- **Code Smell:** Data Clumps (Đám dữ liệu lặp lại).
- **Giải thích:** Các trường `authorName`, `authorEmail`, `authorPhone`, `authorAddress` luôn đi riêng lẻ nhưng lại thành một nhóm phụ thuộc với nhau.
- **Kỹ thuật refactor:** `Extract Class` (Tách lớp). Gộp nhóm các biến liên quan này lại thành một đối tượng duy nhất là `Author`.
