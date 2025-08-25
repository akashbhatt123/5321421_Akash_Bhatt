import java.io.*;
import java.util.*;

class Result {

    /*
     * Sherlock and the Valid String
     * Returns "YES" if the string is valid, otherwise "NO".
     */
    public static String isValid(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        // Count frequency of each character
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Count how many times each frequency occurs
        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int f : freq.values()) {
            freqCount.put(f, freqCount.getOrDefault(f, 0) + 1);
        }

        // Case 1: All characters already same frequency
        if (freqCount.size() == 1) {
            return "YES";
        }

        // Case 2: Two distinct frequencies
        if (freqCount.size() == 2) {
            List<Integer> keys = new ArrayList<>(freqCount.keySet());
            int f1 = keys.get(0), f2 = keys.get(1);

            // Option A: One frequency occurs once and is 1 (remove that char completely)
            if ((freqCount.get(f1) == 1 && f1 == 1) ||
                (freqCount.get(f2) == 1 && f2 == 1)) {
                return "YES";
            }

            // Option B: One frequency occurs once and differs by 1 from the other
            if ((freqCount.get(f1) == 1 && f1 - f2 == 1) ||
                (freqCount.get(f2) == 1 && f2 - f1 == 1)) {
                return "YES";
            }

            return "NO";
        }

        // Case 3: More than two distinct frequencies -> invalid
        return "NO";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = br.readLine();
        String result = Result.isValid(s);

        bw.write(result);
        bw.newLine();

        br.close();
        bw.close();
    }
}
