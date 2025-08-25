import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> result = new ArrayList<>();

        // Remove duplicates and keep descending order
        List<Integer> uniqueRanked = new ArrayList<>();
        for (int score : ranked) {
            if (uniqueRanked.isEmpty() || uniqueRanked.get(uniqueRanked.size() - 1) != score) {
                uniqueRanked.add(score);
            }
        }

        int i = uniqueRanked.size() - 1; // start from lowest rank
        for (int pScore : player) {
            // Move up the leaderboard while player's score >= ranked score
            while (i >= 0 && pScore >= uniqueRanked.get(i)) {
                i--;
            }
            // Rank is position + 2 (because i points to one score below player)
            result.add(i + 2);
        }

        return result;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(br.readLine().trim());
        List<Integer> ranked = Stream.of(br.readLine().trim().split(" "))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());

        int playerCount = Integer.parseInt(br.readLine().trim());
        List<Integer> player = Stream.of(br.readLine().trim().split(" "))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        for (int r : result) {
            bw.write(r + "\n");
        }

        br.close();
        bw.close();
    }
}
