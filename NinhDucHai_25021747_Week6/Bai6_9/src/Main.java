// 1. ISP: Chia nhỏ Interface thay vì nhét chung vào một interface bự
interface AudioPlayable {
    void playAudio(String file);
}

interface VideoPlayable {
    void playVideo(String file);
}

// 2. Lớp AudioPlayer chỉ implement đúng chức năng nó cần
class AudioPlayer implements AudioPlayable {
    @Override
    public void playAudio(String file) {
        System.out.println("Đang phát âm thanh: " + file);
    }
}

// 3. Lớp VideoPlayer chỉ implement đúng chức năng nó cần
class VideoPlayer implements VideoPlayable {
    @Override
    public void playVideo(String file) {
        System.out.println("Đang phát video: " + file);
    }
}

// 4. DIP: Lớp cấp cao (MediaPlayer) KHÔNG phụ thuộc vào Lớp cấp thấp (AudioPlayer/VideoPlayer)
// Nó chỉ phụ thuộc vào Abstraction (Interface).
class MediaPlayer {
    private AudioPlayable audioService;
    private VideoPlayable videoService;

    // Dependency Injection (Tiêm phụ thuộc) qua Constructor
    // Tuyệt đối không dùng từ khóa 'new' ở đây!
    public MediaPlayer(AudioPlayable audio, VideoPlayable video) {
        this.audioService = audio;
        this.videoService = video;
    }

    public void playAudio(String file) {
        audioService.playAudio(file);
    }

    public void playVideo(String file) {
        videoService.playVideo(file);
    }
}

// 5. Test trong Main
public class Main {
    public static void main(String[] args) {
        // Tạo các đối tượng cụ thể (cấp thấp)
        AudioPlayable myAudio = new AudioPlayer();
        VideoPlayable myVideo = new VideoPlayer();

        // Nhúng (Inject) các đối tượng đó vào hệ thống cấp cao
        MediaPlayer player = new MediaPlayer(myAudio, myVideo);

        player.playAudio("bai_giang_design_pattern.mp3");
        player.playVideo("huong_dan_java_nhanh.mp4");
    }
}