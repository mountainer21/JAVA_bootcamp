import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNext()) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        String inputString = scanner.nextLine();

        char[][] dataArr = new char[256][2];

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            dataArr[c][0] = c;
            dataArr[c][1]++;
                if (dataArr[c][1] >= 999) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
        }
        }

        boolean swapped;
        int n = dataArr.length;
        do {
            swapped = false;
            n--;
            for (int i = 0; i < n; i++) {
                if (dataArr[i][1] < dataArr[i + 1][1] || (dataArr[i][1] == dataArr[i + 1][1] && dataArr[i][0] > dataArr[i + 1][0])) {
                    char[] temp = dataArr[i];
                    dataArr[i] = dataArr[i + 1];
                    dataArr[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        int maxCount = dataArr[0][1];
        System.out.println();
        double coefficient = (double)maxCount / 10.0;

        for (int i = 11; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                int count = dataArr[j][1];
                if (dataArr[j][1] >= i * coefficient) {
                    System.out.print("#\t");
                } else if (dataArr[j][1] >= i * coefficient - coefficient){
                    System.out.print(count + "\t");
                } else {
                    System.out.print("\t");
                }

            }
            System.out.println();
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(dataArr[i][0] + "\t");
        }
        System.out.println();
    }
}
