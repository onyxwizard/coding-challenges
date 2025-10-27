public class Pattern10 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 10: half Diamond Star Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");

        // Top including middle
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - 1; j++) System.out.print(" "); // fixed spaces
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
// Bottom
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= n - 1; j++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }

        System.out.println("-------------------------------------------------\n");
    }

    public static void main(String[] args) {
        patternDecode(3);
        patternDecode(6);
    }
}