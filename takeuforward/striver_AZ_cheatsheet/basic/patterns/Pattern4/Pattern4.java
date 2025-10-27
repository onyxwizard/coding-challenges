public class Pattern4 {
    public static void patternDecode(int n){
        System.out.println("-------------------------------------");
        System.out.printf("Pattern 4:Right-Angled Number Pyramid - II | n= %d\n",n);
        System.out.println("-------------------------------------");
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.printf("%d ",i);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern4.patternDecode(n);
        n = 6;
        Pattern4.patternDecode(n);
    }
}
