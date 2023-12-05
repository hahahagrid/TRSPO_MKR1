public class Main {
    private static final int NUMBER_OF_CLIENTS = 5; // Кількість клієнтів для запуску

    public static void main(String[] args) {
        /*
        // Запускаємо сервер у окремому потоці
        new Thread(() -> Server.main(null)).start();

        // Затримка перед запуском клієнтів (може знадобитися для успішного запуску сервера)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                System.out.println("Starting client " + finalI);
                Client.main(null);
            }).start();
        }
    }
}
