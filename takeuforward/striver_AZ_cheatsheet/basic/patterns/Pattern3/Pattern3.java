public class Pattern3 {
    public static void patternDecode(int n){
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern3 : Right-Angled Number Pyramid -> %d\n",n);
        System.out.println("--------------------------------------------------");
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.printf("%d ",j);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern3.patternDecode(n);
        n = 6;
        Pattern3.patternDecode(n);
    }
}
