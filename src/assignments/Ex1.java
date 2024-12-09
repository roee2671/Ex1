package assignments;

/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {
    /**
     * Convert the given number (num) to a decimal representation (as int).
     * It the given number is not in a valid format returns -1.
     *
     * @param num a String representing a number in basis [2,16]
     * @return
     */
    public static int number2Int(String num) {
        int ans = -1;
        // add your code here
        if (!isNumber(num)) {
            return ans;
        }
        char[] c = num.toCharArray();
        int sum = 0;
        int base = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'b') {
                if (c[i + 1] >= '2' && c[i + 1] <= '9') {
                    base += c[i + 1] - '0';
                } else {
                    base += c[i + 1] - 'A' + 10;
                }
                break;
            } else {
                if (c[i] >= 'A' && c[i] <= 'G') {
                    sum = sum * 10 + (c[i] - 'A' + 10);
                }
                if (c[i] >= '0' && c[i] <= '9') {
                    sum = sum * 10 + (c[i] - '0');
                }
            }
        }
        if (base == 0) {
            base = 10;
        }
        ans = toDecimal(sum, base);
        /**
         * עכשיו מה שאני רוצה לעשות זה לקחת את sum ואת base ולחשב אותם על ידי int2Number
         * ואז להעביר אותם ל-string ולהכניס אותם ל-ans
         */
        ////////////////////
        return ans;
    }

    public static int toDecimal(int num, int base) {
        int ans = 0;
        if (num == 0) {
            return ans;
        }
        for (int power = 0; num > 0; power++) {
            int digit = num % 10;
            ans += digit * (int) Math.pow(base, power);
            num = num / 10;
        }
        return ans;
    }

    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     *
     * @param a a String representing a number
     * @return true iff the given String is in a number format
     */
    public static boolean isNumber(String a) {
        boolean ans = true;
        // add your code here
        if (a == null || a.isEmpty() || a.contains(" ")) {
            ans = false;
        } else {
            char[] c = a.toCharArray();
            int last = c.length - 1;
            int hasDigit = 0;
            int hasLetter = 0;
            if (!a.contains("b")) {
                for (char value : c) {
                    if (value < '0' || value > '9') {
                        ans = false;
                        break;
                    }
                }
            } else {
                if (last == 1 || c[last - 1] != 'b' || (c.length == 3 && c[0] == c[2]) || !"23456789ABCDEFG".contains(String.valueOf(c[last]))) {
                    ans = false;
                } else {
                    for (int i = 0; i < last - 1; i++) {
                        if ("0123456789".contains(String.valueOf(c[i]))) {
                            hasDigit = hasDigit + 1;
                        } else if ("ABCDEFG".contains(String.valueOf(c[i]))) {
                            hasLetter = hasLetter + 1;
                        } else {
                            ans = false;
                            break;
                        }
                    }
                    if (hasDigit != 0 && hasLetter != 0) {
                        ans = false;
                    }
                }
            }
        }
        ////////////////////
        return ans;
    }

    /**
     * Calculate the number representation (in basis base)
     * of the given natural number (represented as an integer).
     * If num<0 or base is not in [2,16] the function should return "" (the empty String).
     *
     * @param num  the natural number (include 0).
     * @param base the basis [2,16]
     * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
     */
    public static String int2Number(int num, int base) {
        String ans = "";
        // add your code here
        if (num < 0 || base < 2 || base > 16) {
            return ans;
        }
        if (num == 0) {
            return "0";
        }
        String digits = "0123456789ABCDEFG";
        while (num > 0) {
            int remainder = num % base;
            ans = digits.charAt(remainder) + ans;
            num /= base;
        }
        ////////////////////
        return ans + "b" + digits.charAt(base);
    }

    /**
     * Checks if the two numbers have the same value.
     *
     * @param n1 first number
     * @param n2 second number
     * @return true iff the two numbers have the same values.
     */
    public static boolean equals(String n1, String n2) {
        boolean ans = true;
        // add your code here
        if (number2Int(n1) != number2Int(n2)) {
            ans = false;
        }
        ////////////////////
        return ans;
    }

    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     *
     * @param arr an array of numbers
     * @return the index in the array in with the largest number (in value).
     */
    public static int maxIndex(String[] arr) {
        int ans = 0;
        // add your code here
        int maxVal = number2Int(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != null && number2Int(arr[i]) != -1) {
                int currentValue = number2Int(arr[i]);
                if (currentValue > maxVal) {
                    maxVal = currentValue;
                    ans = i;
                }
            }
        }
        ////////////////////
        return ans;
    }
}
