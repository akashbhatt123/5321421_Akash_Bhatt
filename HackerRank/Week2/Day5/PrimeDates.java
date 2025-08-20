import java.util.*;

public class Solution {

    // Days in each month (ignoring leap year for now)
    static int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

    // Check leap year (Feb will have 29 days)
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    // Move to the next day
    public static void nextDate(int[] date) {
        date[0]++;  // increase day
        int day = date[0];
        int month = date[1];
        int year = date[2];

        int maxDay = daysInMonth[month - 1];
        if (month == 2 && isLeapYear(year)) {
            maxDay = 29; // February in leap year
        }

        // If day exceeds max day, go to next month
        if (day > maxDay) {
            date[0] = 1;     // reset day
            date[1]++;       // increase month
            if (date[1] > 12) { // if month exceeds December
                date[1] = 1; // reset month
                date[2]++;   // increase year
            }
        }
    }

    // Check if two dates are same
    public static boolean isSameDate(int[] d1, int[] d2) {
        return d1[0] == d2[0] && d1[1] == d2[1] && d1[2] == d2[2];
    }

    // Make a number by joining day+month+year
    public static int dateToNumber(int day, int month, int year) {
        String numStr = day + "" + month + "" + year;
        return Integer.parseInt(numStr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input format: dd-mm-yyyy
        String start = sc.next();
        String end = sc.next();

        // Convert string "dd-mm-yyyy" → int array {dd, mm, yyyy}
        int[] startDate = Arrays.stream(start.split("-")).mapToInt(Integer::parseInt).toArray();
        int[] endDate = Arrays.stream(end.split("-")).mapToInt(Integer::parseInt).toArray();

        int count = 0;  
        int[] currentDate = Arrays.copyOf(startDate, 3);

        // Loop through all dates between start and end
        while (true) {
            int val = dateToNumber(currentDate[0], currentDate[1], currentDate[2]);

            // If number divisible by 4 or 7, count it
            if (val % 4 == 0 || val % 7 == 0) {
                count++;
            }

            // Stop when end date is reached
            if (isSameDate(currentDate, endDate)) {
                break;
            }

            // Move to next day
            nextDate(currentDate);
        }

        System.out.println(count);
        sc.close();
    }
}
