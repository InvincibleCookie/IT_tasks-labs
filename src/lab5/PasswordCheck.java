package lab5;
import java.util.regex.*;

public class PasswordCheck {
    public static void main(String[] args) {
        System.out.println(isValidPassword("1234567"));
        System.out.println(isValidPassword("1234567ы"));
        System.out.println(isValidPassword("1234567f"));
        System.out.println(isValidPassword("1234567F"));
    }

    private static boolean isValidPassword(String password) {
        // Регулярное выражение для проверки пароля
        String passwordPattern = "(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}";

        try {
            // Создаем объект Pattern
            Pattern pattern = Pattern.compile(passwordPattern);

            // Создаем объект Matcher
            Matcher matcher = pattern.matcher(password);

            // Проверяем соответствие регулярному выражению
            return matcher.matches();
        }
        catch (Exception ex) {
            System.out.println("Возникла ошибка");
        }
        return false;
    }

}
