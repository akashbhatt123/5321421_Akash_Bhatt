import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Bomberman Simulation
     * n -> time
     * grid -> initial layout
     */
    public static List<String> bomberMan(int n, List<String> grid) {

        // Base case: at t=1, nothing has changed
        if (n == 1) return grid;

        int rows = grid.size();
        int cols = grid.get(0).length();

        // A grid completely filled with bombs
        List<String> filledWithBombs = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char[] row = new char[cols];
            Arrays.fill(row, 'O');
            filledWithBombs.add(new String(row));
        }

        // Every even second is always full of bombs
        if (n % 2 == 0) return filledWithBombs;

        // First explosion result
        List<String> afterFirstBlast = detonate(grid, rows, cols);

        // Pattern repeats: odd > 1 seconds cycle between two states
        if (n % 4 == 3) return afterFirstBlast;

        // If not above, then it must be second explosion
        return detonate(afterFirstBlast, rows, cols);
    }

    // Helper function: simulates bombs exploding
    private static List<String> detonate(List<String> grid, int rows, int cols) {
        char[][] nextState = new char[rows][cols];

        // Start assuming everything is a bomb
        for (int i = 0; i < rows; i++) {
            Arrays.fill(nextState[i], 'O');
        }

        // Directions for blast effect
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        // Process explosions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.get(i).charAt(j) == 'O') {
                    // Bomb destroys itself
                    nextState[i][j] = '.';
                    // And its neighbors
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                            nextState[ni][nj] = '.';
                        }
                    }
                }
            }
        }

        // Convert char[][] back into List<String>
        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            result.add(new String(nextState[i]));
        }
        return result;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] input = br.readLine().trim().split(" ");
        int r = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        int n = Integer.parseInt(input[2]);

        List<String> grid = IntStream.range(0, r)
                .mapToObj(i -> {
                    try {
                        return br.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bw.write(result.stream().collect(joining("\n")) + "\n");

        br.close();
        bw.close();
    }
}
