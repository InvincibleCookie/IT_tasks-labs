package lab5;
import java.util.regex.*;

public class LetterWords {
    public static void main(String[] args) {
        wordsLetter("Когда-то давно, четыре Народа жили в мире, но всё изменилось...", 'н');
    }
    public static void wordsLetter(String text, char letter) {
        try {

            Pattern pattern = Pattern.compile("(\\b" + Character.toLowerCase(letter) + "\\w*\\b)"
                            + "|(\\b" + Character.toUpperCase(letter) + "\\w*\\b)", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()){
                System.out.println(matcher.group());
            }
        }
        catch (Exception ex){
            System.out.println("Возникла ошибка");
        }
    }
}
