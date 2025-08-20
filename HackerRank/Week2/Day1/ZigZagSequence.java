
import java.io.*;
import java.util.*;

public class Solution {

    public static void findZigZagSequence(int[] arr, int n) {
        Arrays.sort(arr);
        
        int mid = (n - 1) / 2;

        // Swap middle and last elements
        int temp = arr[mid];
        arr[mid] = arr[n - 1];
        arr[n - 1] = temp;

        // Reverse elements after mid till second last element
        int st = mid + 1;
        int ed = n - 2;
        while (st <= ed) {
            temp = arr[st];
            arr[st] = arr[ed];
            arr[ed] = temp;
            st++;
            ed--;
        }

        // Print the zig zag sequence
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] parts = br.readLine().split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }

            findZigZagSequence(arr, n);
        }
    }
}
