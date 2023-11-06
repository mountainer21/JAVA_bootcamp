package ex05;
import ex05.TransactionsService;

public class Program {
    public static void main(String[] args) {
        boolean isDevMode = false;

        // Поиск параметра --profile=dev в массиве args
        for (String arg : args) {
            if (arg.equals("--profile=dev")) {
                isDevMode = true;
                break;
            }
        }

        TransactionsService transactionsService = new TransactionsService();

        Menu menu = new Menu(transactionsService, isDevMode);
        menu.display();
    }
}