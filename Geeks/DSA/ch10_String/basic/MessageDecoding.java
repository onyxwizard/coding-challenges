package DSA.ch10_String.basic;

public class MessageDecoding {
  public static boolean decode(String S) {
  String check = "hello";
    int pt = 0;
    int size = check.length();
    for (int i = 0; i < S.length() && pt != size;i++) {
      if (S.charAt(i) == check.charAt(pt)) {
          pt++;
        }
    }
    return pt == size;
  }
    public static void main(String[] args) {
      boolean res = MessageDecoding.decode("cxbvxbbvxvxbbcvhellOcnxbcvxvbvrqwrtr");
      System.out.println(res);
    }
}
