# Bài 3: Refactor theo từng "small steps"

## 1. Phân tích vấn đề của `fuelLevel` và `batteryPercent`
- **Vấn đề (Code Smell):** *Refused Bequest* (Từ chối di sản thừa kế) / Rò rỉ cấu trúc. Lớp cha `Vehicle` chứa các thuộc tính không dùng chung cho tất cả các lớp con. Cụ thể, `ElectricCar` (xe điện) sẽ nhận thừa kế biến `fuelLevel` (vô nghĩa với xe điện) và ngược lại `MotorBike` với `Car` (xe chạy xăng) phải kế thừa `batteryPercent` (vô nghĩa với xe xăng).
- **Giải pháp:** Áp dụng kỹ thuật *Push Down Field* (Đẩy thuộc tính xuống lớp con) đồng thời sử dụng *Extract Superclass* (Tách lớp cha). Ta sẽ chuyển `fuelLevel` xuống một lớp trừu tượng trung gian mới là `FuelVehicle` (dành cho xe xăng, lớp này kế thừa `Vehicle`), qua đó để `MotorBike` và `Car` kế thừa `FuelVehicle`. Đồng thời ta chuyển biến `batteryPercent` trực tiếp vào lớp `ElectricCar`.

## 2. Vấn đề của phương thức `getInfo()` trong các lớp con
- **Vấn đề (Code Smell):** *Duplicated Code* (Lặp code). Cả 3 lớp con đều phải tự khai báo lại đoạn code nối chuỗi `"[" + plate + "] - " + brand`. Nếu sau này chuẩn format biển số thay đổi, ta sẽ phải sửa ở cả 3 class khác nhau.
- **Giải pháp:** Áp dụng kỹ thuật *Pull Up Method* kết hợp mô hình *Template Method*. Rút phần logic format chung `[" + plate + "] - " + brand` lên trên class cha là `Vehicle`. Ta tạo một hàm trừu tượng (ví dụ `getVehicleType()`) trên `Vehicle` để các lớp con tự cung cấp loại xe ("Motorbike", "Car", "Electric Car").
