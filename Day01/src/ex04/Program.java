package ex04;

public class Program {
    public static void main(String[] args) {

    User user1 = new User("Mary", 2000);
    User user2 = new User("Tom", 4000);

    TransactionsService transactionsService = new TransactionsService();
    transactionsService.addUser(user1);
    transactionsService.addUser(user2);

    System.out.println(transactionsService.getUserBalance(user1.getIdentifier()));
    System.out.println(transactionsService.getUserBalance(user2.getIdentifier()));

    transactionsService.transfer(user1.getIdentifier(), user2.getIdentifier(), 1500);

    System.out.println(transactionsService.getUserBalance(user1.getIdentifier()));
    System.out.println(transactionsService.getUserBalance(user2.getIdentifier()));

    // try {
    //   transactionsService.transfer(user1.getIdentifier(), user2.getIdentifier(), 1000);
    // } catch (IllegalTransactionException e) {
    //   System.out.println(e.getMessage());
    // }

    // try {
    //   transactionsService.transfer(user1.getIdentifier(), user2.getIdentifier(), -1000);
    // } catch (IllegalTransactionException e) {
    //   System.out.println(e.getMessage());
    // }

    transactionsService.transfer(user2.getIdentifier(), user1.getIdentifier(), 1500);

    Transaction[] transactions = transactionsService.getUserTransactions(user2.getIdentifier());

    for (Transaction i : transactions) {
      System.out.println(i);
    }

    transactionsService.removeTransaction(user1.getIdentifier(), transactions[0].getIdentifier());
    Transaction[] invalidTransactions = transactionsService.getUnpairedTransactions();


    for (Transaction i : invalidTransactions) {
      System.out.println(i);
    }

    }
}
