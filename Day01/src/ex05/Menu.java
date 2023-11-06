package ex05;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

import ex05.Transaction.TransferCategory;

public class Menu {
    private final TransactionsService transactionsService;
    private final boolean isDevMode;

    public Menu(TransactionsService transactionsService, boolean isDevMode) {
        this.transactionsService = transactionsService;
        this.isDevMode = isDevMode;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add a user");
            System.out.println("2. View user balances");
            System.out.println("3. Perform a transfer");
            System.out.println("4. View all transactions for a specific user");

            if (isDevMode) {
                System.out.println("5. Remove a transfer by ID");
                System.out.println("6. Check transfer validity");
            }

            System.out.println("7. Finish execution");
            System.out.print("-> ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера ввода

            switch (choice) {
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    viewUserBalances(scanner);
                    break;
                case 3:
                    performTransfer(scanner);
                    break;
                case 4:
                    viewAllTransactionsForUser(scanner);
                    break;
                case 5:
                    if (isDevMode) {
                        removeTransfer(scanner);
                    } else {
                        System.out.println("Invalid choice, please try again\n");
                    }
                    break;
                case 6:
                    if (isDevMode) {
                        checkTransferValidity();
                    } else {
                        System.out.println("Invalid choice, please try again\n");
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice, please try again\n");
                    break;
            }
        }
    }

    private void addUser(Scanner scanner) {
        System.out.println("Enter a user name and a balance");
        String name = "";
        double balance = 0.0;

        try {
            name = scanner.nextLine();
            balance = scanner.nextDouble();
            scanner.nextLine(); // очистка буфера ввода
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please enter a valid name and balance\n");
            scanner.nextLine(); // очистка буфера ввода
            return;
        }
        User user = new User(name, balance);
        transactionsService.UsersList.addUser(user);
        System.out.println("User with id = " + user.getIdentifier() + " is added");
    }

    private void viewUserBalances(Scanner scanner) {
        System.out.println("Enter a user ID");

        int userId = 0;

        try {
            userId = scanner.nextInt();
            scanner.nextLine(); // очистка буфера ввода
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please enter a valid user ID\n");
            scanner.nextLine(); // очистка буфера ввода
            return;
        }

        try {
            double balance = transactionsService.getUserBalance(userId);
            System.out.println(transactionsService.UsersList.getUserById(userId).getName() + " - " + balance);
        } catch (UserNotFoundException e) {
            System.out.println("User not found, please try again\n");
        }
    }

    private void performTransfer(Scanner scanner) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

        int senderId = 0;
        int recipientId = 0;
        double amount = 0.0;

        try {
            senderId = scanner.nextInt();
            recipientId = scanner.nextInt();
            amount = scanner.nextDouble();
            scanner.nextLine(); // очистка буфера ввода
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please enter valid sender ID, recipient ID, and transfer amount\n");
            scanner.nextLine(); // очистка буфера ввода
            return;
        }

        transactionsService.transfer(senderId, recipientId, amount);
        System.out.println("The transfer is completed");

    }

    private void viewAllTransactionsForUser(Scanner scanner) {
        System.out.println("Enter a user ID");

        int userId = 0;

        try {
            userId = scanner.nextInt();
            scanner.nextLine(); // очистка буфера ввода
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please enter a valid user ID\n");
            scanner.nextLine(); // очистка буфера ввода
            return;
        }

        try {
            Transaction[] allTransactionsUser = transactionsService.getUserTransactions(userId);

            for (Transaction transaction : allTransactionsUser) {
                String dest = (transaction.getTransferCategory() == TransferCategory.debit) ? "From" : "To";
                User user = (transaction.getTransferCategory() == TransferCategory.debit) ? transaction.getRecipient() : transaction.getSender();
                System.out.printf("%s %s(id = %d) %.2f with id = %s\n", dest, user.getName(),
                        user.getIdentifier(), transaction.getTransferAmount(), transaction.getIdentifier().toString());
            }
        } catch (UserNotFoundException e) {
            System.out.println("User not found, please try again\n");
        }
    }

    private void removeTransfer(Scanner scanner) {
        System.out.println("Enter a user ID and a transfer ID");

        int userId = 0;
        UUID transferId = null;

        try {
            userId = scanner.nextInt();
            transferId = UUID.fromString(scanner.next());
            scanner.nextLine(); // очистка буфера ввода
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Invalid input, please enter a valid user ID and transfer ID\n");
            scanner.nextLine(); // очистка буфера ввода
            return;
        }

        Transaction[] allTransactionsUser = transactionsService.getUserTransactions(userId);
        Transaction removeTransaction = null;

        try {
            transactionsService.removeTransaction(userId, transferId);
        } catch (TransactionNotFoundException e) {
            System.out.println("Transaction not found, please try again\n");
            return;
        }

        for (Transaction t : allTransactionsUser) {
            if (t.getIdentifier().equals(transferId)) {
                removeTransaction = t;
                break;
            }
        }

        double transferAmount =
                (removeTransaction.getTransferAmount() < 0) ? -removeTransaction.getTransferAmount()
                        : removeTransaction.getTransferAmount();

        String dest = (removeTransaction.getTransferCategory() == TransferCategory.debit) ? "From" : "To";
        User user = (removeTransaction.getTransferCategory() == TransferCategory.debit) ? removeTransaction.getRecipient() : removeTransaction.getSender();
        System.out.printf("Transfer %s %s(id = %d) %.2f removed", dest,
                user.getName(), user.getIdentifier(), transferAmount);
    }

    private void checkTransferValidity() {
        System.out.println("Check results:");
        Transaction[] invalidTransactions = transactionsService.getUnpairedTransactions();
        for (Transaction transaction : invalidTransactions) {
            User sender = transaction.getSender();
            User recipient = transaction.getRecipient();

            double transferAmount =
                    (transaction.getTransferAmount() < 0) ? -transaction.getTransferAmount() : transaction.getTransferAmount();

            System.out.printf(
                    "%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %.2f\n",
                    recipient.getName(), recipient.getIdentifier(), transaction.getIdentifier().toString(),
                    sender.getName(), sender.getIdentifier(), transferAmount);
            return;
        }
        System.out.println("No unpair transactions\n");
    }

}
