import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);

            Random random = new Random();

            int N = 1001 + random.nextInt(1000);
            int M = 1001 + random.nextInt(1000);
            int L = 1001 + random.nextInt(1000);

            int[][] matrixA = generateRandomMatrix(N, M);
            int[][] matrixB = generateRandomMatrix(M, L);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeInt(N);
            outputStream.writeInt(M);
            outputStream.writeInt(L);

            outputStream.writeObject(matrixA);
            outputStream.writeObject(matrixB);

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object response = inputStream.readObject();

            if (response instanceof String) {
                System.out.println((String) response);
            } else {
                int[][] resultMatrix = (int[][]) response;

                System.out.println("Result Matrix:");
                printMatrix(resultMatrix);
            }

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int[][] generateRandomMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
