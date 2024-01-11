package lab5;

import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        String text = "The pri42ce of the produc2t is 5. $19.99s 0 333 007";
        Pattern pattern = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            try {
                System.out.println(matcher.group(0));
            } catch (Exception ex) {
                System.out.println("Возникла ошибка");
            }
        }
    }
}
