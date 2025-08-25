import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * New Year Chaos
     * Finds the minimum bribes needed or prints "Too chaotic"
     */
    public static void minimumBribes(List<Integer> q) {
        int bribes = 0;

        for (int i = 0; i < q.size(); i++) {
            int person = q.get(i);

            // If someone has moved forward more than 2 spots => impossible
            if (person - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }

            // Count how many people overtook this person
            int start = Math.max(0, person - 2);
            for (int j = start; j < i; j++) {
                if (q.get(j) > person) {
                    bribes++;
                }
            }
        }

        System.out.println(bribes);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());

        IntStream.range(0, t).forEach(test -> {
            try {
                int n = Integer.parseInt(br.readLine().trim());

                List<Integer> q = Stream.of(br.readLine().trim().split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList());

                Result.minimumBribes(q);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        br.close();
    }
}
