import javax.security.auth.callback.ConfirmationCallback;

public class Recursion7 {
    public static void printFibonacciUpTo(int n) {
        if (n < 0) {
            return; // or print nothing
        }
        System.out.print(0 + " ");
        if (n >= 1) {
            System.out.print(1 + " ");
            printFibonacciHelper(n, 0, 1);
        }
    }

    private static void printFibonacciHelper(int n, int a, int b) {
        int next = a + b;
        if (next > n) {
            return;
        }
        System.out.print(next + " ");
        printFibonacciHelper(n, b, next);
    }

    public static void main(String[] args) {
        int n = 0;
        printFibonacciUpTo(n);
        System.out.println(); // for clean newline
    }
}
