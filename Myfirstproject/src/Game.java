import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    JFrame window;
    Container con;

    // Khai báo 2 "tấm màn hình" (Panel)
    JPanel titleScreenPanel;
    JPanel gameScreenPanel;

    JButton startButton;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        // 1. Tạo Khung cửa sổ cơ bản
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();

        // --- MÀN HÌNH 1: TITLE SCREEN ---
        titleScreenPanel = new JPanel();
        titleScreenPanel.setBounds(100, 100, 600, 150);
        titleScreenPanel.setBackground(Color.blue); // Màu xanh để dễ nhận diện
        JLabel titleName = new JLabel("GAME NHẬP VAI");
        titleName.setForeground(Color.white);
        titleScreenPanel.add(titleName);

        // Tạo nút Start
        startButton = new JButton("BẮT ĐẦU");
        startButton.setBounds(300, 400, 200, 50);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createGameScreen(); // <--- KHI BẤM NÚT THÌ GỌI HÀM CHUYỂN CẢNH
            }
        });

        // --- MÀN HÌNH 2: GAME SCREEN (Ban đầu chưa hiện) ---
        gameScreenPanel = new JPanel();
        gameScreenPanel.setBounds(50, 50, 700, 500);
        gameScreenPanel.setBackground(Color.red); // Màu đỏ để thấy khác biệt
        JLabel gameText = new JLabel("Bạn đang đứng trước một hang động tối tăm...");
        gameText.setForeground(Color.white);
        gameScreenPanel.add(gameText);

        // QUAN TRỌNG: Mới vào game thì ẩn màn hình chơi đi
        gameScreenPanel.setVisible(false);

        // Dán tất cả lên Container
        con.add(titleScreenPanel);
        con.add(startButton);
        con.add(gameScreenPanel);

        window.setVisible(true);
    }

    // --- LOGIC CHUYỂN CẢNH ---
    public void createGameScreen() {
        // 1. Ẩn màn hình cũ (Title & Nút Start)
        titleScreenPanel.setVisible(false);
        startButton.setVisible(false);

        // 2. Hiện màn hình mới (Game Screen)
        gameScreenPanel.setVisible(true);
    }
}