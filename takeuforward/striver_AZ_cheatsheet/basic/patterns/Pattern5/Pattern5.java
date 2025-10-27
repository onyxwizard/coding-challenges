public class Pattern5 {
    public static void patternDecode(int n){
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern5: Inverted Right Pyramid -> %d\n",n);
        System.out.println("--------------------------------------------------");
        for(int i=0;i<n;i++){
            for(int j=n;j>i;j--){
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern5.patternDecode(n);
        n = 6;
        Pattern5.patternDecode(n);
    }
}
