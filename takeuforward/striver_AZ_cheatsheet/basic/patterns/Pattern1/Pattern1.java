public class Pattern1 {
    public static void patternDecode(int n){
        System.out.println("-------------------------------------");
        System.out.printf("             Pattern1 -> %d        \n",n);
        System.out.println("-------------------------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------\n");
    }


    public static void main(String[] args) {
        int n = 3;
        Pattern1.patternDecode(n);
        n = 6;
        Pattern1.patternDecode(n);
    }
}
