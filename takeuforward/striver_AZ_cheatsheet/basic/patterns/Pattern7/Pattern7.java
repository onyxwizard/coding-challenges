public class Pattern7 {
    public static void patternDecode(int n){
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 7: Star Pyramid-> %d\n",n);
        System.out.println("--------------------------------------------------");
        for(int i=1;i<=n;i++){
            for(int j=n;j>i;j--){
                System.out.print(" ");
            }

            for(int j=1;j<=i+(i-1);j++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern7.patternDecode(n);
        n = 6;
        Pattern7.patternDecode(n);
    }
}
