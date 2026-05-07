# Bài 5: The delivery calculator

## 1. Phân tích rủi ro khi có thêm loại đơn hàng mới
- **Vấn đề gì sẽ xảy ra?** Khi có đơn hàng mới (vd Bulky), bắt buộc phải chui vào lớp `Order` và sửa mã nguồn ở 2 chỗ: thêm khối `else if` vào chuỗi xét của hàm `getDeliveryFee()`, và thêm `if` vào hàm `getLabel()`.
- **Tại sao việc thay đổi này là không tốt?** Nếu cứ tiếp tục như thế, đoạn lệnh `if/else if` càng ngày sẽ càng dài dằng dặc. Đội thợ fix bug có thể bất cẩn nối sai thuật toán của các loại cũ, tạo ra lỗi phần mềm trong các class vốn dĩ đã chạy ổn định.
- **Nguyên lý vi phạm:** Open/Closed Principle (OCP - Đóng với việc thay đổi, Mở cho phần mở rộng). Một class khi đã viết và chạy chuẩn rồi thì KHÔNG nên bị sửa lại mỗi khi thêm tính năng mới.

## 2. So sánh công sức khi thêm loại đơn hàng mới (`BulkyOrder`)

### Kịch bản thiết kế cũ (Switch-case / Mệnh đề điều kiện)
Bạn cần cập nhật và lập trình thay đổi hàm thư viện cốt lõi `Order`. Các code test của `Order` cũ bắt buộc phải chạy test lại toàn bộ từ đầu đến cuối.

### Kịch bản đa hình (Polymorphism)
Để thêm loại `BulkyOrder`, hoàn toàn KHÔNG cần phải đụng vào class `Order` (điều này có ý nghĩa to lớn, loại bỏ hoàn toàn viễn cảnh làm sai lệch/rò rỉ code khi maintain).
Hành động cần làm duy nhất là bung ra **một file class hoàn toàn mới** (`BulkyOrder.java`), cho class này tự cài đè mã cước phí (getDeliveryFee) và Nhãn. Hệ thống `Main` ngay lập tức sẽ tiếp nhận đơn hàng này không chút do dự!
