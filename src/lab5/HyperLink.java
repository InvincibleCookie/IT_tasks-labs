package lab5;

import java.util.regex.*;

public class HyperLink {
    public static void main(String[] args) {
        System.out.println(replaceLinks("Вот ссылки https://youtube.com https://google.com"));
    }

    private static String replaceLinks(String text){
        try {
            Pattern pattern = Pattern.compile("https://[^\\s]+");
            Matcher matcher = pattern.matcher(text);

            text = matcher.replaceAll(matchResult -> "<a href=\""
                    +  matchResult.group() + "\">" + matchResult.group() + "</a>");
        }
        catch (Exception ex){
            System.out.println("Возникла ошибка");
            return null;
        }

        return text;
    }
}