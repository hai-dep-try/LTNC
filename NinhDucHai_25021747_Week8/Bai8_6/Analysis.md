# Bài 6: The god class

## 1. Các nhiệm vụ lớp `StudentManager` đang ôm đồm (Vi phạm SRP):
- **Quản lý thông tin sinh viên:** Chứa và xử lý các biến `studentId`, `name`, `gpa`.
- **Quản lý thông tin môn học:** Lại tiếp tục đi chứa biến của object môn học là `courseId`, `courseName`, `credits`. 
- **Quản lý chi tiết điểm số:** Ôm luôn các biến điểm `midterm`, `final`, `assignment` và các bộ công thức toán học để tự xử lý điểm trung bình (`calculateFinalGrade`), đánh giá học lực xếp loại (`getAcademicStatus`).
- **Xử lý hiển thị (UI):** Tự đảm nhận công việc format chuỗi hiển thị in ấn báo cáo bảng điểm ra màn hình qua hàm `printTranscript()`.

*Kết luận: Điều này biến class trên thành một "God class" (Lớp thượng đế - làm mọi thứ). Bất kể có module nào trong 4 module trên thay đổi, lập trình viên đều phải mở file này lên để sửa, điều đó làm cho code ngày càng hỗn loạn.*

## 2. Giải pháp chia nhóm chức năng (Extract Class)
Chia lớp cũ thành các bộ phận tập trung vào một nhiệm vụ duy nhất (Single Responsibility):
- **Lớp `Student`:** Chỉ chứa `studentId`, `name`, `gpa`.
- **Lớp `Course`:** Chỉ chứa `courseId`, `courseName`, `credits`.
- **Lớp `Grade`:** Để chứa điểm và các hàm đánh giá điểm (`calculateFinalGrade()`, `getAcademicStatus()`).
- Lớp **`Transcript`:** Gộp 3 đối tượng trên lại với nhau để thực thi hàm tổng `printTranscript()` lấy dữ liệu từ cả 3.

## 3. Đề xuất khi thêm hệ thống `TeachingAssistant`
Hệ thống lúc này yêu cầu thêm Trợ giảng (`TeachingAssistant`) có đặc tính giống `Student` ở chỗ cùng có `id` và `name`, nhưng lại không cần `gpa` (GPA là điểm trung bình).
- **Giải pháp xử lý:** Áp dụng kỹ thuật **Extract Superclass** (Tách tạo lớp cha).
- Tạo ra một lớp cha trừu tượng tên là `Person` chứa 2 thuộc tính chung là `id` và `name`.
- Cho `Student` kế thừa `Person`, và khai báo thêm thuộc tính đặc thù `gpa`.
- Cho `TeachingAssistant` kế thừa `Person` mà không cần mang theo `gpa` thừa thãi.
*(Trong code mình đã chủ động áp dụng luôn vào code cho bạn xem cụ thể nhé)*
