# Bài 10: The Bank Account Tester

## Đặc tả

### `deposit(double amount)`
- `amount > 0`: nạp tiền → `balance += amount`
- `amount <= 0`: ném `IllegalArgumentException("Số tiền nạp phải lớn hơn 0.")`

### `withdraw(double amount)`
- `amount > 0` và `amount <= balance`: rút thành công → `balance -= amount`, trả `true`
- `amount > 0` và `amount > balance`: không đủ tiền → trả `false`, balance không đổi
- `amount <= 0`: ném `IllegalArgumentException("Số tiền rút phải lớn hơn 0.")`

---

## 1. EP & BVA cho `deposit(double amount)`

### Equivalence Partitioning

| # | Lớp tương đương | Điều kiện | Kết quả mong đợi |
|---|-----------------|-----------|-------------------|
| EP1 | Valid | amount > 0 | balance tăng |
| EP2 | Invalid (zero) | amount == 0 | Exception |
| EP3 | Invalid (negative) | amount < 0 | Exception |

### Boundary Value Analysis (ranh giới tại `amount = 0`)

| Giá trị biên | amount | Kết quả mong đợi |
|--------------|--------|-------------------|
| min- | -0.01 | Exception |
| min | 0.0 | Exception |
| min+ | **0.01** | **Nạp thành công** ← ranh giới valid |
| large | 1,000,000 | Nạp thành công |

---

## 2. EP & BVA cho `withdraw(double amount)`

### Equivalence Partitioning

| # | Lớp tương đương | Điều kiện | Kết quả mong đợi |
|---|-----------------|-----------|-------------------|
| EP1 | Valid (đủ tiền) | 0 < amount ≤ balance | `true`, balance giảm |
| EP2 | Valid (thiếu tiền) | amount > balance | `false`, balance không đổi |
| EP3 | Invalid (zero) | amount == 0 | Exception |
| EP4 | Invalid (negative) | amount < 0 | Exception |

### Boundary Value Analysis

Với **balance = 500**, có 2 ranh giới: `amount = 0` và `amount = balance`.

#### Ranh giới `amount = 0`

| Giá trị biên | amount | Kết quả mong đợi |
|--------------|--------|-------------------|
| min- | -0.01 | Exception |
| min | 0.0 | Exception |
| min+ | **0.01** | **true** (rút thành công) |

#### Ranh giới `amount = balance (500)`

| Giá trị biên | amount | Kết quả mong đợi |
|--------------|--------|-------------------|
| max- | 499.99 | `true`, balance = 0.01 |
| max | **500.0** | **`true`**, balance = 0 |
| max+ | **500.01** | **`false`**, balance = 500 |

---

## 3. Test nhất quán chuỗi thao tác (Consistency Test)

```
Bước 1: Tạo tài khoản, balance = 0
Bước 2: deposit(500)          → balance = 500
Bước 3: withdraw(200) = true  → balance = 300
Bước 4: withdraw(400) = false → balance = 300  (không đủ tiền)
Bước 5: Kiểm tra balance == 300 ✓
```

Test này đảm bảo các thao tác liên tiếp không ảnh hưởng sai đến trạng thái, đặc biệt khi withdraw thất bại thì balance không bị thay đổi.

---

## 4. Tổng hợp test suite

| # | Test method | Loại | Mô tả |
|---|-------------|------|--------|
| 1 | `testDeposit_validAmount` | EP | amount=200, balance tăng |
| 2 | `testDeposit_zero` | EP | amount=0 → Exception |
| 3 | `testDeposit_negative` | EP | amount=-100 → Exception |
| 4 | `testDeposit_BVA_justBelowZero` | BVA | amount=-0.01 → Exception |
| 5 | `testDeposit_BVA_exactlyZero` | BVA | amount=0.0 → Exception |
| 6 | `testDeposit_BVA_justAboveZero` | BVA | amount=0.01 → success |
| 7 | `testDeposit_BVA_largeAmount` | BVA | amount=1M → success |
| 8 | `testWithdraw_validAmount` | EP | amount=200 → true |
| 9 | `testWithdraw_insufficientFunds` | EP | amount=600 → false |
| 10 | `testWithdraw_zero` | EP | amount=0 → Exception |
| 11 | `testWithdraw_negative` | EP | amount=-50 → Exception |
| 12 | `testWithdraw_BVA_justBelowZero` | BVA | amount=-0.01 → Exception |
| 13 | `testWithdraw_BVA_exactlyZero` | BVA | amount=0.0 → Exception |
| 14 | `testWithdraw_BVA_justAboveZero` | BVA | amount=0.01 → true |
| 15 | `testWithdraw_BVA_justBelowBalance` | BVA | amount=499.99 → true |
| 16 | `testWithdraw_BVA_exactlyBalance` | BVA | amount=500.0 → true |
| 17 | `testWithdraw_BVA_justAboveBalance` | BVA | amount=500.01 → false |
| 18 | `testConsistency_sequentialOperations` | Flow | 0→+500→-200→-400→300 |
