public class Pattern17 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 17: Alpha Hill Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");

        for(int i=0;i<n;i++){
            for(int j=n-1;j>i;j--){
                System.out.print(" ");
            }

            for(int j=0;j<=i;j++){
                System.out.printf("%c",(char) (65+j));
            }

            for(int j=i-1;j>-1;j--){
                System.out.printf("%c",(char) (65+j));
            }
            System.out.println();
        }

        System.out.println("-------------------------------------------------\n");
    }

    public static void main(String[] args) {
        patternDecode(3);
        patternDecode(6);
    }
}
