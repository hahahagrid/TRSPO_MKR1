import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    private static final int SERVER_PORT = 9999;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                executorService.execute(new ClientHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                int N = inputStream.readInt();
                int M = inputStream.readInt();
                int L = inputStream.readInt();
                int[][] matrixA = (int[][]) inputStream.readObject();
                int[][] matrixB = (int[][]) inputStream.readObject();

                int[][] resultMatrix = multiplyMatrices(matrixA, matrixB, N, M, L);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(resultMatrix);

                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB, int N, int M, int L) {
            int[][] result = new int[N][L];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < L; j++) {
                    for (int k = 0; k < M; k++) {
                        result[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }

            return result;
        }
    }
}
