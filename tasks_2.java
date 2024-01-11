import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class tasks_2 {
    public static void main(String[] args) {
        System.out.println(duplicateCharts("Donald"));
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55}));
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(indexMult(new int[]{3, 3, -2, 408, 3, 31}));
        System.out.println(reverse("Hello, world!"));
        System.out.println(Tribonacci(11));
        System.out.println(pseudoHash(25));
        System.out.println(botHelper("Hello, I’m under the water, please help me"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
    }

    public static boolean duplicateCharts(String s){
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static String getInitials(String name) {
        String[] s = name.split(" ");
        return String.valueOf(s[0].charAt(0)) + String.valueOf(s[1].charAt(0));
    }
    public static int differenceEvenOdd(int[] arr) {
        int even = 0;
        int odd = 0;

        for (int n : arr) {
            if (n % 2 == 0) {
                even += n;
            } else {
                odd += n;
            }
        }

        return Math.abs(even - odd);
    }

    public static boolean equalToAvg(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        double average = (double) sum / arr.length;

        for (int n : arr) {
            if (n == average) {
                return true;
            }
        }

        return false;
    }
    public static String indexMult(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * i;
        }

        return Arrays.toString(arr);
    }
    public static String reverse(String str) {
        StringBuilder reversed_str = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            reversed_str.append(str.charAt(i));
        }

        return reversed_str.toString();
    }
    public static int Tribonacci(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return 0;
        } else if (n == 3) {
            return 1;
        } else {
            int[] tribonacci = new int[n + 1];
            tribonacci[0] = 0;
            tribonacci[1] = 0;
            tribonacci[2] = 1;

            for (int i = 3; i <= n; i++) {
                tribonacci[i] = tribonacci[i - 1] + tribonacci[i - 2] + tribonacci[i - 3];
            }

            return tribonacci[n - 1];
        }
    }
    public static String pseudoHash(int len) {
        if (len <= 0) {
            return "";
        }

        String characters = "0123456789abcdef";
        Random random = new Random();
        StringBuilder hash = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char randomChar = characters.charAt(random.nextInt(16));
            hash.append(randomChar);
        }

        return hash.toString();
    }
    public static String botHelper(String s) {
        String[] arr = s.toLowerCase().split("\\s+");
        for (String st : arr){
            if (st.equals("help")){
                return "Calling for a staff member";
            }
        }
        return "Keep waiting";
    }
    public static boolean isAnagram(String a, String b) {
        char[] chars1 = a.toLowerCase().toCharArray();
        char[] chars2 = b.toLowerCase().toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }
}
