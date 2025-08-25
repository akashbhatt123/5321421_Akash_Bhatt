import java.io.*;
import java.util.*;

class Result {

    public static String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                // opening bracket - push to stack
                stack.push(ch);
            } else {
                // closing bracket - check top of stack
                if (stack.isEmpty()) return "NO";

                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return "NO";
                }
            }
        }

        // if stack is empty, brackets are balanced
        return stack.isEmpty() ? "YES" : "NO";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < t; i++) {
            String s = br.readLine();
            String result = Result.isBalanced(s);
            bw.write(result);
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
