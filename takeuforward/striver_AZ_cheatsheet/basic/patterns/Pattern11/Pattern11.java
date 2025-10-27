public class Pattern11 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 11: Binary Number Triangle Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");

        for(int i=1;i<=n;i++){
            int start = i%2==0 ? 0 : 1;
            for(int j=1;j<=i;j++){
                System.out.print(start);
                if(start==0){
                    start = 1;
                }else{
                    start = 0;
                }
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
