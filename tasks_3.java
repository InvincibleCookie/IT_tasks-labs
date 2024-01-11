import java.util.ArrayList;
import java.util.List;


public class tasks_3 {
    public static void main(String[] args) {
        System.out.println(replaceVowels("Even if you did this task not by yourself, you have to understand every single line of code."));
        System.out.println(stringTransform("bookkeeper"));
        System.out.println(doesBlockFit(1, 2, 2, 1, 1));
        System.out.println(numCheck(52));
        System.out.println(countRoots(new int[]{1, 1, 9}));
        String[][] data = {
                {"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Banana", "Shop1", "Shop2", "Shop3", "Shop4"},
                {"Orange", "Shop1", "Shop3", "Shop4"},
                {"Pear", "Shop2", "Shop4"}
        };

        System.out.println(salesData(data));
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(WaveForm(new int[]{3, 1, 4, 2, 7, 5}));
        System.out.println(commonVovel("Helloee world"));

        int[][] example2 = {
                {6, 4, 19, 0, 0},
                {81, 25, 3, 1, 17},
                {48, 12, 60, 32, 14},
                {91, 47, 16, 65, 217},
                {5, 73, 0, 4, 21}
        };

        System.out.println(dataScience(example2));
    }

    public static String replaceVowels(String s) {
        StringBuilder replaced = new StringBuilder();
        String vowels = "eyuioaEYUIOA";
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (vowels.contains(String.valueOf(currentChar))) {
                replaced.append("*");
            } else {
                replaced.append(currentChar);
            }
        }
        return replaced.toString();
    }

    public static String stringTransform(String s) {
        StringBuilder doubled = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if ((i + 1 < s.length()) && (String.valueOf(s.charAt(i))).toLowerCase().equals((String.valueOf(s.charAt(i + 1))).toLowerCase())) {
                doubled.append("Double");
                doubled.append((String.valueOf(s.charAt(i)).toUpperCase()));
                i++;
            } else {
                doubled.append((String.valueOf(s.charAt(i))));
            }

        }
        return doubled.toString();

    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        return a * b <= w * h || a * c <= w * h || b * c <= w * h;
    }

    public static boolean numCheck(int n) {
        String s = String.valueOf(n);
        int squares = 0;
        for (int i = 0; i < s.length(); i++) {
            squares += ((int) s.charAt(i)) * ((int) s.charAt(i));
        }
        return n % 2 == squares % 2;
    }

    public static int countRoots(int[] coefficients) {
        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        int discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            return 2;
        } else if (discriminant == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static String salesData(String[][] data) {
        List<String> uniqueElementsList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();

        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data[i].length; j++) {
                String currentElement = data[i][j];

                if (!uniqueElementsList.contains(currentElement)) {
                    uniqueElementsList.add(currentElement);
                }
            }
        }

        for (String[] datum : data) {
            boolean containsAll = true;
            for (String element : uniqueElementsList) {
                if (!containsElement(datum, element)) {
                    containsAll = false;
                    break;
                }
            }
            if (containsAll) {
                resultList.add("\"" + datum[0] + "\"");
            }
        }

        return resultList.toString();
    }

    private static boolean containsElement(String[] array, String element) {
        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validSplit(String s){
        String[] words = s.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if ((words[i].charAt(words[i].length() - 1)) != (words[i + 1].charAt(0))) {
                return false;
            }
        }
        return true;
        }

    public static boolean WaveForm(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (i != 0){
                if (arr[i - 1] > arr[i] && arr[i] < arr[i + 1] || arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                    continue;
                }
                else {
                    return false;
                }
            }
        }

        return true;
    }
    public static char commonVovel(String s) {
        s = s.toLowerCase();
        int[] vowelCount = new int[6];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                vowelCount[0]++;
            } else if (ch == 'e') {
                vowelCount[1]++;
            } else if (ch == 'i') {
                vowelCount[2]++;
            } else if (ch == 'o') {
                vowelCount[3]++;
            } else if (ch == 'u') {
                vowelCount[4]++;
            } else if (ch == 'y') {
                vowelCount[5]++;
            }
        }

        int maxCount = 0;
        int maxIndex = -1;
        for (int i = 0; i < vowelCount.length; i++) {
            if (vowelCount[i] > maxCount) {
                maxCount = vowelCount[i];
                maxIndex = i;
            }
        }

        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        return vowels[maxIndex];
    }

    public static String dataScience(int[][] arrays) {
        int n = arrays.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                if (i == j) {
                    for (int k = 0; k < n; k++) {
                        if (k != j) {
                            sum += arrays[k][i];
                        }
                    }
                    result[i][j] = (int) Math.round((double) sum / (n - 1));
                }
                if (i != j){
                    result[i][j] = arrays[i][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < n; i++) {
            sb.append("[");
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]);
                if (j < n - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            if (i < n - 1) {
                sb.append(",\n");
            }
        }
        sb.append("]");

        return sb.toString();
    }





}
