# Bài 9: The Buggy Trap

## Đặc tả (từ JavaDoc)

| Khoảng GPA | Kết quả |
|------------|---------|
| [0.0, 5.0) | "Yếu" |
| [5.0, 6.5) | "Trung bình" |
| [6.5, 8.0) | "Khá" |
| [8.0, 10.0] | "Giỏi" |
| Ngoài [0.0, 10.0] | `IllegalArgumentException` |

---

## 1. Equivalence Partitioning (EP)

| # | Lớp tương đương | Miền giá trị | GPA đại diện | Kết quả mong đợi |
|---|-----------------|--------------|--------------|-------------------|
| EP1 | Invalid (dưới) | gpa < 0 | -1.0 | Exception |
| EP2 | Invalid (trên) | gpa > 10 | 11.0 | Exception |
| EP3 | Yếu | [0.0, 5.0) | 2.5 | "Yếu" |
| EP4 | Trung bình | [5.0, 6.5) | 5.5 | "Trung bình" |
| EP5 | Khá | [6.5, 8.0) | 7.0 | "Khá" |
| EP6 | Giỏi | [8.0, 10.0] | 9.0 | "Giỏi" |

---

## 2. Boundary Value Analysis (BVA)

### Ranh giới 0.0 (biên dưới toàn cục)

| Giá trị | GPA | Mong đợi |
|---------|-----|----------|
| min- | -0.01 | Exception |
| min | 0.0 | "Yếu" |
| min+ | 0.01 | "Yếu" |

### Ranh giới 5.0 (Yếu ↔ Trung bình)

| Giá trị | GPA | Mong đợi |
|---------|-----|----------|
| max- | 4.99 | "Yếu" |
| max | **5.0** | **"Trung bình"** ← test case quan trọng! |
| max+ | 5.01 | "Trung bình" |

### Ranh giới 6.5 (Trung bình ↔ Khá)

| Giá trị | GPA | Mong đợi |
|---------|-----|----------|
| max- | 6.49 | "Trung bình" |
| max | **6.5** | **"Khá"** ← test case quan trọng! |
| max+ | 6.51 | "Khá" |

### Ranh giới 8.0 (Khá ↔ Giỏi)

| Giá trị | GPA | Mong đợi |
|---------|-----|----------|
| max- | 7.99 | "Khá" |
| max | 8.0 | "Giỏi" |
| max+ | 8.01 | "Giỏi" |

### Ranh giới 10.0 (biên trên toàn cục)

| Giá trị | GPA | Mong đợi |
|---------|-----|----------|
| max- | 9.99 | "Giỏi" |
| max | 10.0 | "Giỏi" |
| max+ | 10.01 | Exception |

---

## 3. Kết quả chạy test với code LỖI (before)

### Test FAIL:

| Test case | GPA | Mong đợi | Thực tế | Trạng thái |
|-----------|-----|----------|---------|------------|
| `testBVA_exactly5` | 5.0 | "Trung bình" | "Yếu" | ❌ FAIL |
| `testBVA_exactly6_5` | 6.5 | "Khá" | "Trung bình" | ❌ FAIL |

Tất cả test case còn lại: ✅ PASS.

### Suy luận lỗi (không nhìn code):

Từ kết quả test, ta thấy các giá trị **tại đúng ranh giới** (`5.0` và `6.5`) bị phân loại sai — chúng bị **"kéo xuống"** nhóm thấp hơn thay vì thuộc nhóm cao hơn.

→ **Kết luận:** Lỗi nằm ở việc xử lý **biên của khoảng giá trị**. Theo đặc tả, các khoảng dùng **ngoặc vuông `[` ở đầu** (bao gồm giá trị biên), nhưng code lại **gán giá trị biên cho nhóm phía dưới** thay vì nhóm phía trên. Nói cách khác, hàm đang sử dụng phép so sánh `≤` (nhỏ hơn hoặc bằng) thay vì `<` (nhỏ hơn) tại các điểm chuyển tiếp.

---

## 4. Cách sửa

Thay đổi toán tử so sánh tại ranh giới `5.0` và `6.5`:

```diff
- if (gpa <= 5.0) return "Yếu";
- if (gpa <= 6.5) return "Trung bình";
+ if (gpa < 5.0)  return "Yếu";
+ if (gpa < 6.5)  return "Trung bình";
  if (gpa < 8.0)  return "Khá";
  return "Giỏi";
```

Sau khi sửa: **Tất cả test PASS ✅**

---

## 5. Test kiểm tra ngoại lệ bổ sung

Đã thêm 2 test case với `assertThrows` cho `gpa = -0.1` và `gpa = 10.1`, kiểm tra cả nội dung thông báo lỗi:

```java
@Test
void testException_negativePt1() {
    IllegalArgumentException ex = assertThrows(
        IllegalArgumentException.class,
        () -> GradeClassifier.classifyGrade(-0.1)
    );
    assertTrue(ex.getMessage().contains("-0.1"));
}

@Test
void testException_aboveTenPt1() {
    IllegalArgumentException ex = assertThrows(
        IllegalArgumentException.class,
        () -> GradeClassifier.classifyGrade(10.1)
    );
    assertTrue(ex.getMessage().contains("10.1"));
}
```
