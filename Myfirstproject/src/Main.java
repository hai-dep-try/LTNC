public class Main {
    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());
        String[] messages = {"If I can stop...", "I shall not..."}; // (Lược bớt cho ngắn gọn)
        Runnable runnable = () -> {
            System.out.println("Inside: " + Thread.currentThread().getName());
            for(String message: messages) {
                System.out.println(message);
                // ... (phần sleep ở bên dưới)
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e){
                    throw new IllegalStateException(e);
                }
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}