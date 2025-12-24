package DSA.ch10_String.basic;
/**
 * @author onyxwizard
 * @date 24-12-2025
 */

import java.text.StringCharacterIterator;
import java.text.CharacterIterator;


/**
 * Built-in (s.length())	O(1) - Constant time	O(1) - No extra space	Excellent	Always use this
 * Manual (For loop)	O(n) - Linear time	O(1)	Good	Learning/teaching purposes
 * Iterator	O(n) - Linear time	O(n) - Iterator overhead	Fair	When needing character iteration anyway
 */

public class LengthString {
  public int getLengthBuiltIn(String s) {
    return s.length(); // Most efficient and readable
  }
  
  public int getLengthManual(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
        count++;
    }
    return count;
}

// Alternative with toCharArray()
public int getLengthManual2(String s) {
  int count = 0;
  for (char c : s.toCharArray()) {
    count++;
  }
  return count;
}

public int getLengthIterator(String s) {
    int count = 0;
    CharacterIterator it = new StringCharacterIterator(s);
    while (it.current() != CharacterIterator.DONE) {
        count++;
        it.next();
    }
    return count;
}
}
