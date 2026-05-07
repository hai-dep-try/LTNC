// Hệ thống cũ (không được sửa)
class OldPlayer {
    void playFile(String name) {
        System.out.println("OldPlayer đang phát: " + name);
    }
}

// Interface của hệ thống mới
interface Player {
    void play(String name);
}

// Bộ chuyển đổi (Adapter)
class PlayerAdapter implements Player {
    private OldPlayer oldPlayer;

    public PlayerAdapter(OldPlayer oldPlayer) {
        this.oldPlayer = oldPlayer;
    }

    @Override
    public void play(String name) {
        oldPlayer.playFile(name); // Ủy quyền gọi hàm cũ
    }
}