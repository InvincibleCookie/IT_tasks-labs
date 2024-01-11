public class tasks_1 {
    public static void main(String[] args) {
        System.out.println(convert(8));
        System.out.println(fitCalc(15, 2));
        System.out.println(containers(3, 4, 2));
        System.out.println(triangleType(3, 4, 2));
        System.out.println(ternaryEvaluation(15, 2));
        System.out.println(howManyItems(22, 1.4, 2));
        System.out.println(factorial(5));
        System.out.println(gcd(18, 48));
        System.out.println(ticketSaler(70, 1500));
        System.out.println(tables(123, 58));
    }

    public static float convert(int x) {
        return x * 3.785f;
    }

    public static int fitCalc(int x, int y) {
        return x * y;
    }

    public static int containers(int x, int y, int z) {

        return x * 20 + y * 50 + z * 100;
    }

    public static String triangleType(int x, int y, int z) {
        if (x + y <= z || y + z <= x || x + z <= y) {
            return "not a triangle";
        }
        if (x == y && y == z) {
            return "isosceles";
        }
        if (x == y || x == z || y == z) {
            return "equilateral";
        }
        return "different-sided";
    }

    public static int ternaryEvaluation(int x, int y) {
        return x > y ? x : y;
    }
    public static int howManyItems(double n, double w, double h) {
        return (int) (n / (w * h * 2));
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
    public static int ticketSaler(int a, int b) {
        return (int) (a * b * 0.72);
    }
    public static int tables(int a, int b) {
        if (a <= (b * 2)) {
            return 0;
        }
        if ((a % (b * 2)) % 2 == 0) {
            return (a % (b * 2)) / 2;
        }
        return (a % (b * 2)) / 2 + 1;
    }
}

