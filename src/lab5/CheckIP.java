package lab5;
import java.util.regex.*;

public class CheckIP {
    public static void main(String[] args) {
        System.out.println(checkIP("0.1.2"));
        System.out.println(checkIP("142..."));
        System.out.println(checkIP("1.1.1.15.16"));
        System.out.println(checkIP("255.5.42.255"));
    }

    public static boolean checkIP(String ip){
        try {
            Pattern pattern = Pattern.compile(
                    "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        }catch (Exception ex){
            System.out.println("Возникла ошибка");
            return false;
        }

    }
}
