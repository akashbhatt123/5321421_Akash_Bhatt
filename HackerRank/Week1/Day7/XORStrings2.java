// XOR Strings 2 solution
import java.util.*;

public class XORStrings2 {

    public static String strings_xor(String s, String t) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // XOR of each character
            res.append(s.charAt(i) == t.charAt(i) ? '0' : '1');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        System.out.println(strings_xor(s, t));
    }
}
