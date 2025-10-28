package ReverseString;

public class ReverseString {

    public static void reverseString(char[] s) {
        int left=0, right=s.length-1;
        while(left < right){
            swap(s,left,right);
            left++;
            right--;
        }
    }

    public static void swap(char[] c,int left, int right){
        char temp = c[left];
        c[left] = c[right];
        c[right] = temp;
    }

    public static void main(String[] args) {
        char[] charArr= {'h','e','l','l','o'};
        reverseString(charArr);

    }
}
