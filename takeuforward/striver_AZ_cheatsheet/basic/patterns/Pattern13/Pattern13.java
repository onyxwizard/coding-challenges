public class Pattern13 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 13: Increasing Number Triangle Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");
        int start=1;
        for(int i=1;i<=n;i++){
            for(int j =1;j<=i;j++){
                System.out.printf("%d ",start);
                start++;
            }
            System.out.println();
        }


        System.out.println("\n-------------------------------------------------\n");
    }

    public static void main(String[] args) {
        patternDecode(3);
        patternDecode(6);
    }
}
