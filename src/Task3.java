import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n <= 1) {
                System.out.println("Composite");
            } else if (isPrime(n, n / 2)) {
                System.out.println("Prime");
            } else {
                System.out.println("Composite");
            }
        }
    }
    static boolean isPrime(int n, int i) {
        if (i==1) {
            return true;
        }
        if (n%i == 0) {
            return false;
        }
        return isPrime(n, i-1);
    }
}