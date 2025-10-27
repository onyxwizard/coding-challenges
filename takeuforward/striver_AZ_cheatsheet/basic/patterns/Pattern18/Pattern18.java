public class Pattern18 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 18: Alpha-Triangle Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");

        for(int i=n-1;i>=0;i--){
            for(int j=i;j<n;j++){
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
