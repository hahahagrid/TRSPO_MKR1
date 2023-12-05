import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);

            int N = 2000; // Розмір матриці N x M
            int M = 1500; // Розмір матриці M x L
            int L = 1000; // Розмір матриці M x L

            int[][] matrixA = generateRandomMatrix(N, M);
            int[][] matrixB = generateRandomMatrix(M, L);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeInt(N);
            outputStream.writeInt(M);
            outputStream.writeInt(L);
            outputStream.writeObject(matrixA);
            outputStream.writeObject(matrixB);

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            int[][] resultMatrix = (int[][]) inputStream.readObject();

            // Вивід результату
            System.out.println("Result Matrix:");
            printMatrix(resultMatrix);

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
