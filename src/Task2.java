import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            readArray(sc, arr, 0, n);
            double sum = calculateSum(arr, n - 1);
            System.out.println(sum/n);
        }
    }
    static void readArray(Scanner sc, int[] arr, int index, int n) {
        if (index < n) {
            arr[index] = sc.nextInt();
            readArray(sc, arr, index + 1, n);
        }
    }
    static double calculateSum(int[] arr, int index) {
        if (index < 0) {
            return 0;
        }
        return arr[index] + calculateSum(arr, index - 1);
    }
}