# 📚 Adjugé! — Tài Liệu Ôn Tập Bảo Vệ BTL

> [!IMPORTANT]
> Mục tiêu: Cả nhóm đọc xong tài liệu này sẽ trả lời được BẤT KỲ câu hỏi nào thầy cô đặt ra về dự án.

---

## 1. Tổng Quan Dự Án (30 giây pitch)

**"Adjugé! là ứng dụng đấu giá trực tuyến viết bằng JavaFX. Người dùng có thể đăng ký, đăng nhập, duyệt sản phẩm, đặt giá, và tạo phiên đấu giá. Ứng dụng dùng kiến trúc MVC, lưu dữ liệu bằng SQLite, và áp dụng 5 Design Pattern."**

---

## 2. Cây Kế Thừa (Class Hierarchy) — CÂU HỎI HAY GẶP NHẤT

```
Entity (abstract)                    ← Lớp gốc, có id + createdAt
├── User (abstract)                  ← firstName, lastName, email, password
│   ├── Bidder                       ← canBid=true,  canSell=false
│   ├── Seller                      ← canBid=true,  canSell=true
│   └── Admin                       ← canBid=false, canManageUsers=true
└── Item (abstract)                  ← name, description, startPrice, category
    ├── Electronics                  ← brand, model, warrantyMonths
    ├── Art                          ← artist, year, medium
    ├── Vehicle                      ← year, make, model, mileage
    ├── Fashion                      ← brand, material, size
    ├── Antique                      ← era, origin, ageYears
    └── MusicalInstrument            ← brand, year, instrumentType
```

### Câu hỏi mẫu & cách trả lời

**Q: "Tại sao Entity và Item là abstract?"**
> A: Vì chúng ta không bao giờ tạo một "Entity" hay "Item" chung chung. Chúng ta chỉ tạo các đối tượng cụ thể như Seller, Electronics. Các lớp abstract bắt buộc lớp con phải override các phương thức `printInfo()`, `getCategoryLabel()`, `getItemSpecifics()` — đây chính là Đa hình.

**Q: "Đa hình thể hiện ở đâu?"**
> A: Ví dụ trong `BiddingController.buildAuctionCard()`, biến `item` khai báo kiểu `Item` nhưng thực tế có thể là Electronics, Art, Vehicle... Khi gọi `item.getCategoryLabel()`, Java tự động chọn đúng phiên bản của lớp con tại runtime. Electronics trả về "ELECTRONICS", Art trả về "ART & COLLECTIBLES".

**Q: "Tại sao Seller có canBid = true?"**
> A: Vì trong thực tế, người bán hàng trên sàn đấu giá cũng có thể đi mua (đặt giá) sản phẩm của người bán khác. Chỉ có Admin là không được phép bid.

---

## 3. Năm Design Pattern — PHẢI THUỘC

### 3.1 Singleton — `DataStore` và `DatabaseManager`

**Ở đâu:** [DataStore.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/pattern/DataStore.java), [DatabaseManager.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/dao/DatabaseManager.java)

**Cách giải thích:**
> "Singleton đảm bảo chỉ có DUY NHẤT MỘT instance của DataStore trong toàn bộ ứng dụng. Gọi `DataStore.getInstance()` ở bất kỳ đâu đều trả về cùng một đối tượng. Điều này quan trọng vì nếu có 2 DataStore khác nhau, dữ liệu sẽ bị lệch."

**Cơ chế:**
```java
private static DatabaseManager instance;          // biến static duy nhất
public static synchronized DatabaseManager getInstance() {
    if (instance == null) instance = new DatabaseManager();  // tạo 1 lần
    return instance;                                         // trả về mãi mãi
}
```

---

### 3.2 Observer — `Auction` ↔ `BiddingController`

**Ở đâu:**
- Interface: [AuctionSubject.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/pattern/AuctionSubject.java), [AuctionObserver.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/pattern/AuctionObserver.java)
- Subject: [Auction.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/model/Auction.java) (implements AuctionSubject)
- Observer: [BiddingController.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/controller/BiddingController.java) (implements AuctionObserver)

**Cách giải thích:**
> "Khi ai đó đặt giá, Auction gọi `notifyNewBid()` → duyệt qua danh sách observers → gọi `onNewBid()` trên mỗi observer. BiddingController nhận được thông báo và cập nhật giao diện. Như vậy Auction không cần biết gì về UI, nó chỉ thông báo thôi."

**Luồng chạy:**
```
User nhấn nút "Bid" 
  → BiddingController.onPlaceBid() 
    → BidService.placeBid() 
      → auction.addBid(tx) 
        → auction.notifyNewBid(tx)         ← Subject thông báo
          → controller.onNewBid(auction, bid)  ← Observer nhận
            → Platform.runLater(renderAuctionDetail())  ← Cập nhật UI
```

---

### 3.3 Factory — `ItemFactory`

**Ở đâu:** [ItemFactory.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/pattern/ItemFactory.java)

**Cách giải thích:**
> "Thay vì ở mỗi nơi trong code phải viết `new Electronics(...)` hay `new Vehicle(...)`, ta gọi `ItemFactory.createItem(Category.ELECTRONICS, ...)` và Factory tự quyết định tạo đúng lớp con. Nếu sau này thêm loại sản phẩm mới, chỉ cần sửa Factory, không cần sửa các nơi khác."

---

### 3.4 Strategy — `BidValidationStrategy`

**Ở đâu:** [BidValidationStrategy.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/pattern/BidValidationStrategy.java), triển khai trong [BidService.java](file:///f:/Dai%20cuong/007.OOP/Adjuge-20260501T095937Z-3-001/Adjuge/src/com/adjuge/service/BidService.java) (class `StandardBidValidator`)

**Cách giải thích:**
> "Strategy cho phép thay đổi thuật toán validate bid mà không sửa BidService. Hiện tại dùng StandardBidValidator (kiểm tra: không bid sản phẩm của mình, giá phải cao hơn, không vượt 10 triệu đô). Nếu cần luật khác cho VIP, chỉ cần tạo class mới implement BidValidationStrategy, không cần đụng vào BidService."

**4 luật validate hiện tại:**
1. Không được bid sản phẩm của chính mình
2. Tài khoản phải có quyền bid (`canBid() == true`)
3. Giá đặt phải cao hơn giá hiện tại
4. Giá đặt không quá $10,000,000

---

### 3.5 MVC — Model / View / Controller

| Layer | File | Vai trò |
|---|---|---|
| **Model** | `model/*.java` (16 file) | Dữ liệu: Auction, User, Item... |
| **View** | `bidding.fxml` + `bidding.css` | Giao diện: layout + style |
| **Controller** | `BiddingController.java` | Xử lý sự kiện click, gọi Service |

---

## 4. Kiến Trúc Phân Lớp — CÂU HỎI "AI LÀM GÌ?"

```
FXML (View)  →  Controller  →  Service  →  DataStore/DAO  →  SQLite
 Hiển thị      Nhận click     Logic BL     Đọc/ghi DB      Database
```

| Package | Chứa gì | Ví dụ |
|---|---|---|
| `model/` | Các class dữ liệu | User, Item, Auction, BidTransaction |
| `controller/` | Xử lý UI events | BiddingController (1 file duy nhất) |
| `service/` | Logic nghiệp vụ | AuthService (login), BidService (đặt giá), AuctionTimerService |
| `dao/` | Truy vấn database | UserDAO, ItemDAO, AuctionDAO, DatabaseManager |
| `pattern/` | Design patterns | DataStore, ItemFactory, Observer/Strategy interfaces |
| `exception/` | Lỗi tùy chỉnh | AuthenticationException, InvalidBidException |

---

## 5. Thread Safety — CÂU HỎI NÂNG CAO

**Q: "Tại sao BidService dùng synchronized?"**
> A: Vì nếu 2 người cùng lúc bid lên 1 sản phẩm, có thể xảy ra race condition: cả hai đều đọc giá hiện tại = $100, cả hai đều bid $110, một bid bị ghi đè. `synchronized(auction)` đảm bảo chỉ 1 thread xử lý bid trên cùng 1 auction tại một thời điểm. Các auction khác nhau vẫn chạy song song vì lock trên mỗi đối tượng auction riêng.

**Q: "CopyOnWriteArrayList là gì?"**
> A: Danh sách observers trong Auction dùng CopyOnWriteArrayList. Khi thêm/xóa observer, nó tạo bản sao mới. Nhờ vậy việc duyệt danh sách (notify) không bị lỗi khi đang có observer thêm/xóa cùng lúc.

---

## 6. Luồng Hoạt Động Chính — HỌC BẰNG CÁCH KỂ CHUYỆN

### 6.1 Người dùng đăng nhập
```
Nhập email + password → onLogin() → AuthService.login()
  → DataStore.getUserByEmail(email)
  → So sánh password
  → OK → Lưu currentUser, chuyển về Home
  → Sai → Throw AuthenticationException → Hiện lỗi trên form
```

### 6.2 Người dùng đặt giá (Bid)
```
Nhập số tiền → nhấn "Bid" → onPlaceBid()
  → BidService.placeBid(auction, user, amount)
    → synchronized(auction)     ← Khóa để tránh race condition
    → validator.validate(...)   ← Strategy Pattern kiểm tra luật
    → auction.addBid(tx)        ← Lưu bid + notify observers
    → DataStore.saveBid(...)    ← Ghi vào SQLite
  → Observer.onNewBid()         ← Controller nhận thông báo
  → renderAuctionDetail()       ← Vẽ lại giao diện
```

### 6.3 Auction tự đóng khi hết giờ
```
AuctionTimerService chạy mỗi 1 giây (daemon thread)
  → Duyệt tất cả auction
  → Nếu state==RUNNING && isExpired()
    → setState(FINISHED)        ← Đổi trạng thái
    → notifyStateChange()       ← Observer cập nhật UI
```

---

## 7. Câu Hỏi Thường Gặp Khi Bảo Vệ

| # | Câu hỏi | File liên quan | Gợi ý trả lời |
|---|---|---|---|
| 1 | Đa hình ở đâu? | Item.java, Electronics.java | getCategoryLabel(), getItemSpecifics() trả về khác nhau tuỳ lớp con |
| 2 | Abstract class vs Interface? | Entity/Item (abstract) vs AuctionObserver (interface) | Abstract class: có thuộc tính chung. Interface: chỉ khai báo hành vi |
| 3 | Singleton dùng làm gì? | DataStore, DatabaseManager | Đảm bảo 1 instance duy nhất, tránh dữ liệu lệch |
| 4 | Observer hoạt động sao? | Auction → BiddingController | Auction thông báo, Controller nhận và vẽ lại UI |
| 5 | Factory dùng khi nào? | ItemFactory.createItem() | Khi cần tạo đúng loại Item dựa theo Category mà không cần biết class cụ thể |
| 6 | Tại sao dùng synchronized? | BidService.placeBid() | Tránh race condition khi 2 người bid cùng lúc |
| 7 | FXML là gì? | bidding.fxml | File XML mô tả layout UI, tách riêng giao diện khỏi logic Java |
| 8 | DAO là gì? | UserDAO, ItemDAO... | Data Access Object - lớp chuyên đọc/ghi database, tách logic DB khỏi business logic |
| 9 | Tại sao chỉ có 1 Controller? | BiddingController | Kiến trúc SPA - tất cả view nằm trong 1 FXML, chuyển bằng show/hide |
| 10 | Exception handling? | exception/*.java | Tạo exception riêng cho từng loại lỗi (AuthenticationException, InvalidBidException) thay vì dùng Exception chung |

---

## 8. Kế Hoạch Ôn Tập Cho Nhóm

### Tuần 1: Đọc hiểu cơ bản
- [ ] Mỗi người đọc qua file `PROJECT_OVERVIEW.html` (đã có sẵn trong dự án)
- [ ] Đọc tài liệu này (study_guide.md)
- [ ] Mở từng file Java, đọc comment (code đã được comment rất kỹ)

### Tuần 2: Phân chia ôn theo người
- [ ] **Người 1**: Thuộc cây kế thừa Entity → User → Seller/Bidder/Admin
- [ ] **Người 2**: Thuộc cây kế thừa Entity → Item → Electronics/Art/Vehicle...
- [ ] **Người 3**: Thuộc 5 Design Pattern + giải thích luồng
- [ ] **Người 4**: Thuộc kiến trúc MVC + DAO + cách chạy app

### Tuần 3-5: Mock bảo vệ
- [ ] Ngồi hỏi nhau các câu ở bảng mục 7
- [ ] Mỗi người thử chạy app bằng `run.bat`, demo các chức năng
- [ ] Tập trình bày: mở code → chỉ vào dòng cụ thể → giải thích

> [!TIP]
> **Mẹo quan trọng nhất:** Khi thầy cô hỏi, đừng nói lý thuyết suông. Hãy **mở file code lên, chỉ vào dòng cụ thể** và nói "Đây là chỗ em dùng Observer Pattern, ở dòng 703 trong BiddingController, khi có bid mới thì hàm onNewBid() được gọi để vẽ lại giao diện."
