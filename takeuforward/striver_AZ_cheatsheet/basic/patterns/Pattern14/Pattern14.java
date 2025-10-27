public class Pattern14 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 14: Increasing Letter Triangle Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");
        for(int i=1;i<=n;i++){
            for(int j =0;j<i;j++){
                System.out.printf("%c ",(char) (65+j));
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
