# Bài 7: The Discount Inspector

## Đặc tả hàm `calculateDiscount(double price, String memberType)`

| Điều kiện | Hành vi |
|-----------|---------|
| `price < 0` | Ném `IllegalArgumentException` |
| `memberType = "GUEST"` | Không chiết khấu (0%) |
| `memberType = "MEMBER"`, `price < 100` | Chiết khấu 5% |
| `memberType = "MEMBER"`, `price >= 100` | Chiết khấu 10% |
| `memberType = "VIP"`, `price < 100` | Chiết khấu 15% |
| `memberType = "VIP"`, `price >= 100` | Chiết khấu 20% |
| `memberType` khác | Ném `IllegalArgumentException` |

---

## 1. Equivalence Partitioning (EP) cho tham số `price`

| # | Lớp tương đương | Miền giá trị | Hợp lệ? |
|---|-----------------|--------------|----------|
| EP1 | Giá âm (invalid) | price < 0 | Không |
| EP2 | Giá thấp (valid) | 0 ≤ price < 100 | Có |
| EP3 | Giá cao (valid) | price ≥ 100 | Có |

### Bảng test case theo EP

| Mã TC | Mô tả | price | memberType | Kết quả mong đợi |
|-------|--------|-------|------------|-------------------|
| TC01 | Giá âm, GUEST | -5 | GUEST | IllegalArgumentException |
| TC02 | Giá âm, MEMBER | -10 | MEMBER | IllegalArgumentException |
| TC03 | Giá âm, VIP | -1 | VIP | IllegalArgumentException |
| TC04 | Giá thấp, GUEST | 50 | GUEST | 0.0 |
| TC05 | Giá thấp, MEMBER | 50 | MEMBER | 2.5 (5%) |
| TC06 | Giá thấp, VIP | 50 | VIP | 7.5 (15%) |
| TC07 | Giá cao, GUEST | 150 | GUEST | 0.0 |
| TC08 | Giá cao, MEMBER | 150 | MEMBER | 15.0 (10%) |
| TC09 | Giá cao, VIP | 150 | VIP | 30.0 (20%) |
| TC10 | memberType không hợp lệ | 50 | UNKNOWN | IllegalArgumentException |

---

## 2. Boundary Value Analysis (BVA)

Áp dụng BVA cho tham số `price` tại hai ranh giới: `price = 0` và `price = 100`.

### Ranh giới `price = 0`

| Giá trị biên | price | Ý nghĩa |
|--------------|-------|---------|
| min- | -0.01 | Ngay dưới 0 → Invalid |
| min | 0 | Ranh giới dưới → Valid |
| min+ | 0.01 | Ngay trên 0 → Valid |

### Ranh giới `price = 100`

| Giá trị biên | price | Ý nghĩa |
|--------------|-------|---------|
| max- | 99.99 | Ngay dưới 100 → Chiết khấu thấp |
| max | 100 | Ranh giới trên → Chiết khấu cao |
| max+ | 100.01 | Ngay trên 100 → Chiết khấu cao |

### Bảng test case BVA (lấy VIP làm ví dụ minh họa)

| Mã TC | price | memberType | Kết quả mong đợi |
|-------|-------|------------|-------------------|
| BVA01 | -0.01 | VIP | IllegalArgumentException |
| BVA02 | 0 | VIP | 0.0 (15% × 0) |
| BVA03 | 0.01 | VIP | 0.0015 (15%) |
| BVA04 | 99.99 | VIP | 14.9985 (15%) |
| BVA05 | 100 | VIP | 20.0 (20%) |
| BVA06 | 100.01 | VIP | 20.002 (20%) |

---

## 3. 2-way Combinatorial Testing cho cặp (price, memberType)

### Các giá trị đại diện

| Tham số | Giá trị đại diện |
|---------|-------------------|
| **price** | -5 (invalid), 50 (0 ≤ p < 100), 150 (p ≥ 100) |
| **memberType** | "GUEST", "MEMBER", "VIP", "UNKNOWN" (invalid) |

### Bộ test 2-way combinatorial

Bộ test tối thiểu đảm bảo mọi cặp giá trị (price, memberType) đều xuất hiện ít nhất trong 1 test case:

| Mã TC | price | memberType | Kết quả mong đợi |
|-------|-------|------------|-------------------|
| COMB01 | -5 | GUEST | IllegalArgumentException |
| COMB02 | -5 | MEMBER | IllegalArgumentException |
| COMB03 | -5 | VIP | IllegalArgumentException |
| COMB04 | -5 | UNKNOWN | IllegalArgumentException |
| COMB05 | 50 | GUEST | 0.0 |
| COMB06 | 50 | MEMBER | 2.5 |
| COMB07 | 50 | VIP | 7.5 |
| COMB08 | 50 | UNKNOWN | IllegalArgumentException |
| COMB09 | 150 | GUEST | 0.0 |
| COMB10 | 150 | MEMBER | 15.0 |
| COMB11 | 150 | VIP | 30.0 |
| COMB12 | 150 | UNKNOWN | IllegalArgumentException |

**Tổng: 12 test case** đảm bảo phủ hết tất cả các cặp 2-way giữa `price` và `memberType`.
