public class Pattern12 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 12: Number Crown Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");

        for(int i=1;i<=n;i++){
            //Left
            for(int j=1;j<=i;j++) {
                System.out.print(j);
            }

            //middle
            for(int j=n;j>i;j--){
                System.out.print("  ");
            }

            //right
            for(int j=i;j>=1;j--){
                System.out.print(j);
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
