# ⚡ Adjugé! — Lộ Trình Phát Triển Tiếp Theo

Dựa trên phân tích toàn bộ mã nguồn hiện tại (37 class, 7 package), dưới đây là đánh giá hiện trạng và đề xuất phát triển cho ba lĩnh vực: **Bảo mật**, **Kiểm thử**, và **Kết nối mạng/Server**.

---

## 📊 Đánh giá hiện trạng

### Những gì đã làm tốt
- Kiến trúc MVC + DAO phân lớp rõ ràng
- Áp dụng đa dạng Design Pattern (Observer, Singleton, Factory, Strategy)
- Thread safety với `synchronized` trong `BidService` và `CopyOnWriteArrayList` cho observers
- Exception handling có hệ thống với custom exceptions

### Các vấn đề cần khắc phục

> [!CAUTION]
> **Mật khẩu đang được lưu dạng plain-text!**
> Trong [AuthService.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/service/AuthService.java#L39), mật khẩu được so sánh trực tiếp bằng `user.getPassword().equals(password)`. Đây là lỗ hổng bảo mật nghiêm trọng nhất của dự án hiện tại.

> [!WARNING]
> **Không có kiểm thử tự động (Unit Test).**
> Toàn bộ dự án hiện tại không có bất kỳ test nào. Mọi kiểm tra đều phải thực hiện thủ công qua giao diện.

> [!WARNING]
> **Ứng dụng chỉ chạy cục bộ (offline).**
> Dữ liệu lưu trong SQLite local, không có cơ chế đồng bộ giữa các máy. Không thể để hai người dùng thực sự đấu giá với nhau.

---

## 🔒 Phase 1: Bảo Mật (Security)

### 1.1 Mã hoá mật khẩu (Password Hashing)

**Vấn đề:** Mật khẩu đang được lưu và so sánh dạng plain-text trong cả `AuthService` lẫn bảng `users` trong SQLite.

**Giải pháp:** Sử dụng thuật toán **BCrypt** hoặc **PBKDF2** (có sẵn trong JDK) để hash mật khẩu trước khi lưu.

**File cần sửa:**

#### [NEW] `src/com/adjuge/util/PasswordUtil.java`
- Tạo class tiện ích với 2 phương thức:
  - `hashPassword(String plainText) → String` — trả về chuỗi hash
  - `verifyPassword(String plainText, String hashedPassword) → boolean` — so sánh an toàn
- Sử dụng `PBKDF2WithHmacSHA256` (có sẵn trong `javax.crypto`), không cần thêm thư viện ngoài

#### [MODIFY] `src/com/adjuge/service/AuthService.java`
- Hàm `register()`: gọi `PasswordUtil.hashPassword(password)` trước khi tạo user
- Hàm `login()`: thay `user.getPassword().equals(password)` bằng `PasswordUtil.verifyPassword(password, user.getPassword())`

#### [MODIFY] `src/com/adjuge/pattern/DataStore.java`
- Cập nhật `seedData()` để hash mật khẩu của các tài khoản demo

### 1.2 Validation đầu vào (Input Sanitization)

**Vấn đề:** Các trường nhập liệu (email, tên, mô tả sản phẩm) chưa được validate kỹ.

**Giải pháp:**

#### [NEW] `src/com/adjuge/util/ValidationUtil.java`
- `isValidEmail(String email) → boolean` — kiểm tra định dạng email bằng regex
- `sanitize(String input) → String` — loại bỏ ký tự đặc biệt nguy hiểm (chống SQL injection dù đã dùng PreparedStatement)
- `isValidPrice(String input) → boolean` — kiểm tra giá hợp lệ (số dương, không quá lớn)

#### [MODIFY] `AuthService.java`
- Thêm validation email format trước khi đăng ký
- Thêm validation tên (không trống, không chứa ký tự đặc biệt)

### 1.3 Quản lý phiên đăng nhập (Session Security)

**Vấn đề:** `currentUser` hiện chỉ là một biến tham chiếu đơn giản, không có cơ chế timeout hay token.

**Giải pháp (nâng cao):**

#### [NEW] `src/com/adjuge/service/SessionManager.java`
- Tạo session token khi đăng nhập
- Auto-logout sau thời gian không hoạt động (ví dụ: 30 phút)
- Ghi log các lần đăng nhập/đăng xuất

---

## 🧪 Phase 2: Kiểm Thử (Testing)

### 2.1 Thiết lập Framework kiểm thử

**Công cụ:** JUnit 5 (Jupiter) — framework kiểm thử chuẩn của Java.

**Cấu trúc thư mục đề xuất:**
```
Adjuge/
├── src/          (mã nguồn chính - đã có)
├── test/         (mã kiểm thử - MỚI)
│   └── com/adjuge/
│       ├── service/
│       │   ├── AuthServiceTest.java
│       │   ├── BidServiceTest.java
│       │   └── AuctionServiceTest.java
│       ├── model/
│       │   ├── AuctionTest.java
│       │   └── UserTest.java
│       └── dao/
│           └── DatabaseManagerTest.java
└── lib/
    ├── sqlite-jdbc.jar   (đã có)
    └── junit-platform-console-standalone.jar  (MỚI - tải về)
```

### 2.2 Các Test Case ưu tiên

#### `test/com/adjuge/service/AuthServiceTest.java`
Áp dụng kỹ thuật **Equivalence Partitioning (EP)** và **Boundary Value Analysis (BVA)**:

| Test Case | Mô tả | Kết quả mong đợi |
|---|---|---|
| `testLoginSuccess` | Email + password đúng | Trả về User, không throw |
| `testLoginWrongPassword` | Email đúng, password sai | Throw `AuthenticationException` |
| `testLoginUnknownEmail` | Email không tồn tại | Throw `AuthenticationException` |
| `testRegisterSuccess` | Thông tin hợp lệ | Tạo user mới, tự động login |
| `testRegisterPasswordTooShort` | Password 5 ký tự (BVA: biên dưới) | Throw exception |
| `testRegisterPasswordExactMin` | Password 6 ký tự (BVA: biên) | Thành công |
| `testRegisterPasswordMismatch` | Password ≠ confirm | Throw exception |
| `testRegisterDuplicateEmail` | Email đã tồn tại | Throw exception |

#### `test/com/adjuge/service/BidServiceTest.java`

| Test Case | Mô tả | Kết quả mong đợi |
|---|---|---|
| `testPlaceBidSuccess` | Bid cao hơn giá hiện tại | Tạo `BidTransaction`, cập nhật auction |
| `testBidTooLow` | Bid ≤ giá hiện tại | Throw `InvalidBidException` |
| `testBidOnOwnAuction` | Seller bid lên auction của chính mình | Throw `InvalidBidException` |
| `testBidOnExpiredAuction` | Auction đã hết hạn | Throw `AuctionClosedException` |
| `testBidExceedsMaximum` | Bid > $10,000,000 | Throw `InvalidBidException` |
| `testConcurrentBids` | 2 thread cùng bid một lúc | Chỉ 1 bid thắng, không race condition |

#### `test/com/adjuge/model/AuctionTest.java`

| Test Case | Mô tả | Kết quả mong đợi |
|---|---|---|
| `testObserverNotified` | Thêm bid → observer được gọi | `onNewBid()` được gọi đúng 1 lần |
| `testStateChangeNotification` | Đổi state → observer nhận thông báo | `onStateChange()` với đúng old/new state |
| `testIsExpired` | Auction có endTime trong quá khứ | `isExpired()` trả về `true` |
| `testBidCountAccuracy` | Thêm 3 bid | `getBidCount()` trả về `3` |

### 2.3 Cách chạy Test (không cần Maven/Gradle)

Tải file `junit-platform-console-standalone-1.10.x.jar` vào thư mục `lib/`, sau đó chạy:
```batch
javac --module-path javafx\javafx-sdk-24\lib --add-modules javafx.controls,javafx.fxml ^
      -cp "lib\*;out" -d out\test test\com\adjuge\service\*.java

java --module-path javafx\javafx-sdk-24\lib --add-modules javafx.controls,javafx.fxml ^
     -jar lib\junit-platform-console-standalone.jar ^
     -cp "out;out\test;lib\sqlite-jdbc.jar" --scan-class-path
```

---

## 🌐 Phase 3: Kết Nối Mạng / Server (Networking)

Đây là bước nâng cấp lớn nhất — biến Adjugé! từ ứng dụng offline thành ứng dụng **Client-Server** thực sự.

### 3.1 Phương án A: Java Socket (Đơn giản, phù hợp bài tập lớn OOP)

Sử dụng `java.net.Socket` / `java.net.ServerSocket` có sẵn trong JDK, không cần thư viện ngoài.

**Kiến trúc:**
```
┌──────────────┐       TCP Socket       ┌──────────────┐
│  JavaFX App  │  ◄─────────────────►  │  Java Server  │
│  (Client)    │   JSON messages        │  (Console)    │
│              │                        │  + SQLite DB  │
└──────────────┘                        └──────────────┘
          ▲                                    ▲
          │                                    │
     Nhiều client                    Một server duy nhất
     cùng kết nối                    quản lý toàn bộ data
```

**Cấu trúc package mới:**

```
src/com/adjuge/
├── model/        (giữ nguyên)
├── server/       (MỚI)
│   ├── AdjugeServer.java          — Main server, accept connections
│   ├── ClientHandler.java         — Xử lý mỗi client trên thread riêng
│   └── Protocol.java              — Định nghĩa message format (JSON)
├── network/      (MỚI)
│   ├── NetworkClient.java         — Client-side socket connection
│   └── MessageListener.java       — Interface nhận message từ server
├── service/      (SỬA)
│   ├── AuthService.java           — Gửi login/register qua network
│   ├── BidService.java            — Gửi bid qua network
│   └── ...
└── controller/   (SỬA NHẸ)
    └── BiddingController.java     — Kết nối NetworkClient thay vì DataStore trực tiếp
```

**Luồng hoạt động:**
1. Server khởi chạy, lắng nghe trên cổng (ví dụ: `5555`)
2. Client JavaFX kết nối tới `localhost:5555` (hoặc IP mạng LAN)
3. Khi người dùng đặt giá, client gửi JSON: `{"action":"BID","auctionId":"AUC-001","amount":15000}`
4. Server xử lý (validate, lưu DB), rồi broadcast kết quả tới **tất cả** client đang online
5. Mỗi client nhận được thông báo và cập nhật giao diện qua Observer Pattern (đã có sẵn!)

### 3.2 Phương án B: REST API với HTTP Server (Nâng cao hơn)

Sử dụng `com.sun.net.httpserver.HttpServer` (có sẵn trong JDK) hoặc thư viện nhẹ như **Javalin** / **Spark**.

**Ưu điểm so với Socket:**
- Có thể mở rộng thêm Web frontend (trình duyệt)
- Dễ debug hơn (dùng Postman/curl kiểm tra API)
- Stateless, dễ scale

**Nhược điểm:**
- Real-time cập nhật cần thêm WebSocket hoặc polling
- Phức tạp hơn cho bài tập OOP

### 3.3 Đề xuất cho bạn

> [!IMPORTANT]
> **Với bối cảnh bài tập lớn OOP, mình khuyến nghị Phương án A (Java Socket)** vì:
> - Thể hiện rõ kiến thức về **multi-threading** (mỗi client = 1 thread)
> - Showcase được **Observer Pattern** trong thực tế (broadcast bid updates)
> - Không cần thêm thư viện bên ngoài (dùng thuần JDK)
> - Demo "2 máy đấu giá với nhau" rất ấn tượng khi bảo vệ bài

---

## 📋 Tóm Tắt Thứ Tự Ưu Tiên

| Ưu tiên | Phase | Công việc | Độ khó | Thời gian ước tính |
|:---:|:---:|---|:---:|:---:|
| 🔴 1 | Security | Hash mật khẩu (PBKDF2) | ⭐⭐ | 2-3 giờ |
| 🔴 2 | Security | Input validation | ⭐ | 1-2 giờ |
| 🟡 3 | Testing | Setup JUnit 5 + Auth tests | ⭐⭐ | 3-4 giờ |
| 🟡 4 | Testing | Bid tests + Auction tests | ⭐⭐ | 3-4 giờ |
| 🟢 5 | Network | Server + ClientHandler | ⭐⭐⭐ | 5-8 giờ |
| 🟢 6 | Network | Tích hợp client JavaFX | ⭐⭐⭐ | 4-6 giờ |
| ⚪ 7 | Security | Session management | ⭐⭐ | 2-3 giờ |

> [!TIP]
> Nếu thời gian hạn chế, hãy ưu tiên **Phase 1.1 (hash password)** và **Phase 2.2 (unit tests cho AuthService + BidService)**. Đây là hai điểm mà giảng viên thường đánh giá cao nhất trong bài tập OOP.

---

## ❓ Câu hỏi cần bạn quyết định

1. **Deadline bài tập lớn là khi nào?** — Để mình điều chỉnh phạm vi phù hợp.
2. **Bạn muốn mình bắt tay làm Phase nào trước?** — Mình có thể viết code trực tiếp cho bạn.
3. **Phần Networking:** Bạn muốn demo trên **cùng 1 máy** (2 cửa sổ) hay **2 máy qua mạng LAN**?
