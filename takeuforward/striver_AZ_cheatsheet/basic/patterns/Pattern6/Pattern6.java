public class Pattern6 {
    public static void patternDecode(int n){
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 6:Inverted Numbered Right Pyramid -> %d\n",n);
        System.out.println("--------------------------------------------------");
        for(int i=n;i>0;i--){
            for(int j=1;j<=i;j++){
                System.out.printf("%d ",(j));
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern6.patternDecode(n);
        n = 6;
        Pattern6.patternDecode(n);
    }
}
