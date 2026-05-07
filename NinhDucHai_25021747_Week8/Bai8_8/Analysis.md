# Bài 8: The First JUnit

## Đặc tả

### `max(int a, int b)`
Trả về số lớn hơn trong hai số. Nếu bằng nhau, trả về giá trị đó.

### `divide(int a, int b)`
Chia nguyên `a / b`. Ném `IllegalArgumentException` nếu `b == 0`.

---

## 1. Thiết kế test case cho `max(int a, int b)`

### 1.1 Equivalence Partitioning (EP)

| # | Lớp tương đương | Điều kiện | Kết quả mong đợi |
|---|-----------------|-----------|-------------------|
| EP1 | a > b | a lớn hơn b | Trả về a |
| EP2 | a == b | a bằng b | Trả về a (hoặc b) |
| EP3 | a < b | a nhỏ hơn b | Trả về b |

### 1.2 Boundary Value Analysis (BVA)

Bổ sung các giá trị biên tại `Integer.MIN_VALUE` (-2,147,483,648) và `Integer.MAX_VALUE` (2,147,483,647):

| Mã TC | a | b | Kết quả mong đợi | Lý do |
|-------|---|---|-------------------|-------|
| BVA01 | MIN_VALUE | MIN_VALUE | MIN_VALUE | Cả hai bằng nhau ở cực tiểu |
| BVA02 | MIN_VALUE | 0 | 0 | Biên dưới so với 0 |
| BVA03 | MIN_VALUE | MAX_VALUE | MAX_VALUE | Hai cực trị đối lập |
| BVA04 | MAX_VALUE | MAX_VALUE | MAX_VALUE | Cả hai bằng nhau ở cực đại |
| BVA05 | MAX_VALUE | 0 | MAX_VALUE | Biên trên so với 0 |
| BVA06 | -5 | -1 | -1 | Hai số âm (cạnh biên âm) |

---

## 2. Thiết kế test case cho `divide(int a, int b)`

### 2.1 Equivalence Partitioning (EP)

| # | Lớp tương đương | Điều kiện | Kết quả mong đợi |
|---|-----------------|-----------|-------------------|
| EP1 | b > 0 | Chia cho số dương | Kết quả bình thường (a/b) |
| EP2 | b < 0 | Chia cho số âm | Kết quả đổi dấu |
| EP3 | b == 0 | Chia cho 0 | `IllegalArgumentException` |

### 2.2 Bổ sung edge cases

| Mã TC | a | b | Kết quả mong đợi | Lý do |
|-------|---|---|-------------------|-------|
| DIV01 | 10 | 2 | 5 | Chia hết bình thường |
| DIV02 | 10 | -2 | -5 | Divisor âm |
| DIV03 | 10 | 0 | Exception | Chia cho 0 |
| DIV04 | 7 | 2 | 3 | Chia dư → truncate toward zero |
| DIV05 | 0 | 5 | 0 | Dividend = 0 |
| DIV06 | -10 | -2 | 5 | Âm / âm = dương |

---

## 3. Giải thích: Tại sao `@BeforeAll` phải là `static`?

JUnit 5 mặc định sử dụng mô hình **"per-method instance"**: mỗi khi chạy một `@Test` method, JUnit sẽ tạo ra một **instance mới** của test class. Điều này đảm bảo các test hoàn toàn **độc lập**, không chia sẻ trạng thái (state) với nhau.

```
Test class: MathUtilsTest
├── @BeforeAll   ← chạy 1 lần DUY NHẤT (chưa có instance nào!)
├── new MathUtilsTest() → chạy testMax_aGreaterThanB()
├── new MathUtilsTest() → chạy testMax_aEqualsB()
├── new MathUtilsTest() → chạy testMax_aLessThanB()
├── ...
└── @AfterAll    ← chạy 1 lần DUY NHẤT (sau tất cả)
```

Vì `@BeforeAll` chạy **trước khi bất kỳ instance nào được tạo**, nên phương thức đó **không thể là instance method** (cần `this` để gọi). Do đó, nó **bắt buộc phải là `static`** — thuộc về **class**, không thuộc về bất kỳ **object** nào.

> **Ngoại lệ:** Nếu dùng `@TestInstance(Lifecycle.PER_CLASS)`, JUnit sẽ chỉ tạo **1 instance duy nhất** cho toàn bộ test class. Lúc đó `@BeforeAll` không cần `static` nữa — nhưng đây không phải mặc định.

---

## 4. Tổng hợp test suite trong `MathUtilsTest.java`

| # | Test method | Loại | Mô tả |
|---|-------------|------|--------|
| 1 | `testMax_aGreaterThanB` | EP | a > b → a |
| 2 | `testMax_aEqualsB` | EP | a == b → a |
| 3 | `testMax_aLessThanB` | EP | a < b → b |
| 4 | `testMax_bothMinValue` | BVA | MIN vs MIN |
| 5 | `testMax_minValueVsZero` | BVA | MIN vs 0 |
| 6 | `testMax_minValueVsMaxValue` | BVA | MIN vs MAX |
| 7 | `testMax_bothMaxValue` | BVA | MAX vs MAX |
| 8 | `testMax_maxValueVsZero` | BVA | MAX vs 0 |
| 9 | `testMax_bothNegative` | BVA | -5 vs -1 |
| 10 | `testDivide_positiveB` | EP | b > 0 |
| 11 | `testDivide_negativeB` | EP | b < 0 |
| 12 | `testDivide_byZero` | EP | b == 0 → Exception |
| 13 | `testDivide_integerTruncation` | Edge | 7/2 = 3 |
| 14 | `testDivide_zeroDividend` | Edge | 0/5 = 0 |
| 15 | `testDivide_bothNegative` | Edge | -10/-2 = 5 |
