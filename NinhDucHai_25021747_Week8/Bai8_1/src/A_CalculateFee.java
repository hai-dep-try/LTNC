// Đoạn A sau khi refactor
// Code smell: Poor Naming (t, h, r, m, f không có ý nghĩa rõ ràng) & Magic Number (0.9).
// Giải pháp: Đổi tên biến cho rõ nghĩa (Rename Variable), và đặt hằng số cho giá trị 0.9 (Replace Magic Number with Symbolic Constant).

public class A_CalculateFee {
    public static final double MEMBER_DISCOUNT_RATE = 0.9;

    public double calculateFee(String type, int hours, double rate, boolean isMember) {
        double fee = hours * rate;
        if (isMember) {
            fee = fee * MEMBER_DISCOUNT_RATE;
        }
        return fee;
    }
}
