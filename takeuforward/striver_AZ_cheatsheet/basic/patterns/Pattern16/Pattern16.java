public class Pattern16 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 16: Alpha-Ramp Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");
        for(int i=0;i<n;i++){
            for(int j =0;j<=i;j++){
                System.out.printf("%c ",(char) (65+i));
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
