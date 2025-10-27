public class Recursion1 {
    public static void printName(int n,String name){
        if(n==1){
            System.out.print(name);
            return;
        }
        System.out.print(name);
        System.out.print(" ");
        printName(n-1,name);
    }
    public static void main(String[] args) {
        int n=3;
        String name = "AK";
        printName(n,name);
    }
}
