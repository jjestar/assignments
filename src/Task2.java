import java.util.Scanner;
public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int[] array = new int[n];
            fillArray(array, 0, scanner);
            int sum = findSum(array, n);
            double average = (double) sum / n;
            System.out.println(average);
        }
    }
    public static int findSum(int[] arr, int n) {
        if (n <= 0) {
            return 0;
        }
        return arr[n - 1] + findSum(arr, n - 1);
    }
    public static void fillArray(int[] arr, int index, Scanner sc) {
        if (index == arr.length) {
            return;
        }
        arr[index] = sc.nextInt();
        fillArray(arr, index + 1, sc);
    }
}