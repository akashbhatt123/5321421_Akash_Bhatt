import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine().trim());

        Stack<Integer> stackNewest = new Stack<>();
        Stack<Integer> stackOldest = new Stack<>();

        for (int i = 0; i < q; i++) {
            String[] parts = br.readLine().split(" ");
            int type = Integer.parseInt(parts[0]);

            switch (type) {
                case 1:
                    // Enqueue element
                    int value = Integer.parseInt(parts[1]);
                    stackNewest.push(value);
                    break;

                case 2:
                    // Dequeue element
                    if (stackOldest.isEmpty()) {
                        while (!stackNewest.isEmpty()) {
                            stackOldest.push(stackNewest.pop());
                        }
                    }
                    stackOldest.pop();
                    break;

                case 3:
                    // Print front element
                    if (stackOldest.isEmpty()) {
                        while (!stackNewest.isEmpty()) {
                            stackOldest.push(stackNewest.pop());
                        }
                    }
                    System.out.println(stackOldest.peek());
                    break;
            }
        }
    }
}
