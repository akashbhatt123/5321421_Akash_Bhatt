import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt(); // number of operations
        sc.nextLine();

        StringBuilder text = new StringBuilder();
        Stack<String> history = new Stack<>();

        for (int i = 0; i < q; i++) {
            String[] parts = sc.nextLine().split(" ");
            int type = Integer.parseInt(parts[0]);

            switch (type) {
                case 1: // append
                    history.push(text.toString()); // save state before change
                    text.append(parts[1]);
                    break;

                case 2: // delete
                    history.push(text.toString());
                    int k = Integer.parseInt(parts[1]);
                    text.delete(text.length() - k, text.length());
                    break;

                case 3: // print
                    int pos = Integer.parseInt(parts[1]);
                    System.out.println(text.charAt(pos - 1));
                    break;

                case 4: // undo
                    if (!history.isEmpty()) {
                        text = new StringBuilder(history.pop());
                    }
                    break;
            }
        }
        sc.close();
    }
}
