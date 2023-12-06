public class Main {
    private static final int NUMBER_OF_CLIENTS = 3; // Кількість клієнтів для запуску

    public static void main(String[] args) {
        /*

        Запуск сервера - цей код писався під час тестування, але був закоментований для наглядності роботи сервера
                                                                    (він запускається окремо, а клієнти в необмеженій кількость - окремо)

        new Thread(() -> Server.main(null)).start();

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
