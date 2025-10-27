public class Recursion3 {
    public static int sumNaturalNumbers(int sum,int n){
        if(n==0){
            return sum;
        }

        return sumNaturalNumbers(sum+n,n-1);

    }
    public static void main(String[] args) {
        int n=5;
        int result = sumNaturalNumbers(0,n);
        System.out.println(result);
    }
}
