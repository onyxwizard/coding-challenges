public class Pattern19 {
    public static void patternDecode(int n) {
        System.out.println("---------------------------------------------------");
        System.out.printf(" Pattern 19: Symmetric Void Pattern -> %d\n", n);
        System.out.println("--------------------------------------------------");

        //Left Part
        for(int i =n;i>0;i--){
            for(int j=i;j>=1;j--){
                System.out.print("*");
            }

            for(int j=i;j<n;j++){
                System.out.print("  ");
            }

            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }

        //Right Part

        for(int i =1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }

            for(int j=i;j<n;j++){
                System.out.print("  ");
            }

            for(int j=1;j<=i;j++){
                System.out.print("*");
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
