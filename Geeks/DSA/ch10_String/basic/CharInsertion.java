package DSA.ch10_String.basic;

public class CharInsertion {
  static String insertChar(String s, char c, int pos) {
    StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
      
            // Insert the character at the given position
            if (i == pos) 
                res.append(c);
      
            // Insert the original characters
            res.append(s.charAt(i));
        }
  
        // If the given pos is beyond the length,  
        // append the character at the end
        if (pos >= s.length()) 
            res.append(c);
      
        return res.toString();
  }
  public static void main(String[] args) {
    String s = CharInsertion.insertChar("Hello", 'z', 0);
    System.out.println(s);
  }
}
