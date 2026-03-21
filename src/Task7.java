import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            printReverse(sc, n);
        }
    }

    static void printReverse(Scanner sc, int n) {
        if (n > 0) {
            int val = sc.nextInt();
            printReverse(sc, n - 1);
            System.out.print(val + " ");
        }
    }
}