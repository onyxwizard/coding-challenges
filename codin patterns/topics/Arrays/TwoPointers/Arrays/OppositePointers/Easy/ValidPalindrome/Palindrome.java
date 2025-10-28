package ValidPalindrome;

public class Palindrome{

    public static boolean isPalindrome(String s) {

        int left=0, right = s.length()-1;

        while (left < right){
            if(!Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }

            else if(!Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }

            else if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            else{
                left++;
                right--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "Angel";
        if(isPalindrome(s)) System.out.println("Its is ValidPalindrome.Palindrome");
        else System.out.println("Its is Not ValidPalindrome.Palindrome");

        s = "Radar";
        if(isPalindrome(s)) System.out.println("Its is ValidPalindrome.Palindrome");
        else System.out.println("Its is Not ValidPalindrome.Palindrome");
    }
}