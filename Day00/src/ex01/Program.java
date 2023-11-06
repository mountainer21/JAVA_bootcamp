import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        int steps = 1;
        boolean isPrime = true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            } else {
                steps++;
            }
        }
        if (isPrime) {
            System.out.println("true " + steps);
        } else {
            System.out.println("false " + steps);
        }
    }
}
