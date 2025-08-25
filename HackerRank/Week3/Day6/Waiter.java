import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {

    // Function to generate first q primes
    private static List<Integer> generatePrimes(int q) {
        List<Integer> primes = new ArrayList<>();
        int num = 2;
        while (primes.size() < q) {
            boolean isPrime = true;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(num);
            }
            num++;
        }
        return primes;
    }

    public static List<Integer> waiter(List<Integer> number, int q) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> current = new Stack<>();
        current.addAll(number); // stack order is already correct (last element = top)

        List<Integer> primes = generatePrimes(q);

        for (int i = 0; i < q; i++) {
            Stack<Integer> next = new Stack<>();
            Stack<Integer> divisible = new Stack<>();
            int prime = primes.get(i);

            // Divide into stacks
            while (!current.isEmpty()) {
                int num = current.pop();
                if (num % prime == 0) {
                    divisible.push(num);
                } else {
                    next.push(num);
                }
            }

            // Print stack B first (from top to bottom)
            while (!divisible.isEmpty()) {
                result.add(divisible.pop());
            }

            // Continue with stack A
            current = next;
        }

        // After q iterations, print remaining numbers in A
        while (!current.isEmpty()) {
            result.add(current.pop());
        }

        return result;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int n = Integer.parseInt(firstMultipleInput[0]);
        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> number = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> result = Result.waiter(number, q);

        bufferedWriter.write(
            result.stream().map(Object::toString).collect(Collectors.joining("\n")) + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
