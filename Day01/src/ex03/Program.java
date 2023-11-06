package ex03;

import ex03.Transaction.TransferCategory;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Mary", 2000);
        User user2 = new User("Tom", 4000);
        System.out.println(user1);
        System.out.println(user2);

        Transaction transaction1 = new Transaction(user2, user1, TransferCategory.debit, 2000);
        Transaction transaction2 = new Transaction(user1, user2, TransferCategory.credit, -1000);
        TransactionsList list = new TransactionsLinkedList();

    list.addTransaction(transaction1);
    list.addTransaction(transaction2);

    Transaction[] transactionsArray = list.toArray();
    for (Transaction t : transactionsArray) {
      System.out.println(t);
    }

    list.removeTransaction(transaction1.getIdentifier());

    transactionsArray = list.toArray();
    for (Transaction t : transactionsArray) {
      System.out.println(t);
    }

    try {
      list.removeTransaction(transaction1.getIdentifier());
    } catch (TransactionNotFoundException e) {
      System.out.println(e.getMessage());
    }

    }
}