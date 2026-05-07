import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CustomerSupportSystem {
    public static void main(String[] args) {
        Queue<Customer> customerQueue = new LinkedList<>();
        Stack<Message> messageHistory = new Stack<>();

        System.out.println("KHÁCH HÀNG GỬI YÊU CẦU");

        Customer khA = new Customer("C01", "Khách A");
        customerQueue.offer(khA);
        System.out.println("Đã thêm vào hàng đợi: " + khA.name);

        Customer khB = new Customer("C02", "Khách B");
        customerQueue.offer(khB);
        System.out.println("Đã thêm vào hàng đợi: " + khB.name);

        System.out.println("\nNHÂN VIÊN XỬ LÝ KHÁCH A");
        if (!customerQueue.isEmpty()) {
            Customer current = customerQueue.poll();
            System.out.println("Đang hỗ trợ: " + current.name);
            messageHistory.clear();

            messageHistory.push(new Message("M1", "Dạ Tiki xin chào ạ."));
            System.out.println("Đã gõ: " + messageHistory.peek().content);

            messageHistory.push(new Message("M2", "Sản phẩm này hiện đang hết hàng."));
            System.out.println("Đã gõ: " + messageHistory.peek().content);

            messageHistory.push(new Message("M3", "Anh/chị thông cảm nhaaaaa!"));
            System.out.println("Đã gõ: " + messageHistory.peek().content);

            System.out.println("-> [View Last]: Câu vừa gõ là: '" + messageHistory.peek().content + "'");

            Message deletedMsg = messageHistory.pop();
            System.out.println("-> [Undo]: Đã xóa câu: '" + deletedMsg.content + "'");
        }

        System.out.println("\nNHÂN VIÊN XỬ LÝ KHÁCH B");
        if (!customerQueue.isEmpty()) {
            Customer current = customerQueue.poll();
            System.out.println("Đang hỗ trợ: " + current.name);
            messageHistory.clear();

            messageHistory.push(new Message("M4", "Shopee chào Khách B, đơn của bạn đang giao nhé."));
            System.out.println("Đã gõ: " + messageHistory.peek().content);
        }

        System.out.println("\nNHÂN VIÊN BẤM XỬ LÝ TIẾP");
        if (customerQueue.isEmpty()) {
            System.out.println("Thông báo: Không còn khách đợi.");
        } else {
            customerQueue.poll();
        }
    }
}
