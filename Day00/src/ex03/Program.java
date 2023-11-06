import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String week;
        int weeks_count = 0;
        long grade;
        long grade_store = 0;

        for (int i = 0; i < 18; i++) {
            // System.out.println("Input number of week:");
            week = scanner.nextLine();
            weeks_count++;

            if (week.equals("42")) {
                break;
            }

            if (!week.equals("Week " + (i + 1))) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            // System.out.println("Input grade:");    
            grade = scanner.nextInt();
            if (grade < 1 || grade > 9) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            for (int j = 0; j < 4; ++j) {
                int next_grade = scanner.nextInt();
                if (next_grade < 1 || next_grade > 9) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                if (grade > next_grade) {
                    grade = next_grade;
                }
            }
            scanner.nextLine();

            long digit_position = 1;
            for (int j = 0; j < i; j++) {
                digit_position *= 10;
            }
            grade_store += grade * digit_position;
        }

        for (int i = 1; i <= weeks_count; i++) {
            grade = (int) (grade_store % 10);
            grade_store /= 10;

            System.out.print("Week " + i + " ");
            for (int j = 0; j < grade; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }

    }
}