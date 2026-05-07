// Đoạn B sau khi refactor
// Code smell: God Class / Large Class (Vi phạm Single Responsibility Principle).
// Lớp UserService ôm đồm quá nhiều chức năng: query DB, gửi email, render UI, export file.
// Giải pháp: Extract Class, tách các chức năng thành các dịch vụ riêng biệt.

class User {
    // Dummy class
    private int id;
    public int getId() { return id; }
}

class UserService {
    public User findById(int id) { 
        // ... code tìm user ...
        return new User(); 
    }
}

class UserEmailService {
    public void sendWelcomeEmail(User user) { 
        // ...
    }
    public void sendPasswordResetEmail(User user) { 
        // ...
    }
}

class UserProfileRenderer {
    public void renderUserProfile(User user) { 
        // ...
    }
}

class UserExportService {
    public String exportUserToCsv(User user) { 
        // ...
        return ""; 
    }
}
