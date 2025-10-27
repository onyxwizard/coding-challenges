public class Pattern2 {
    public static void patternDecode(int n){
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 2: Right-Angled Triangle Pattern | n = %d\n",n);
        System.out.println("--------------------------------------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern2.patternDecode(n);
        n = 6;
        Pattern2.patternDecode(n);
    }
}
