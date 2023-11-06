package ex00;

import ex00.Transaction.TransferCategory;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "Mary", 2000);
        User user2 = new User(2, "Tom", 4000);
        System.out.println(user1);
        System.out.println(user2);

        Transaction transaction1 = new Transaction(user2, user1, TransferCategory.debit, 2000);
        Transaction transaction2 = new Transaction(user1, user2, TransferCategory.credit, -1000);

        System.out.println(transaction1);
        System.out.println(transaction2);

    }
}