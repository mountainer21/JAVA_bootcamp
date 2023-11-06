package ex04;

import java.util.UUID;

import ex04.Transaction.TransferCategory;

public class TransactionsService {
    private UsersList UsersList;

    public TransactionsService() {
        UsersList = new UsersArrayList();
    }

    public void addUser(User user) {
        UsersList.addUser(user);
    }

    public double getUserBalance(int userId) {
        User user = UsersList.getUserById(userId);
        return user.getBalance();
    }

    public void transfer(int senderId, int recipientId, double amount) throws IllegalTransactionException {
        User sender = UsersList.getUserById(senderId);
        User recipient = UsersList.getUserById(recipientId);

        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("Sender " + senderId + " does not have enough funds for the transfer.");
        }

        Transaction debit = new Transaction(sender, recipient, TransferCategory.debit, amount);
        Transaction credit = new Transaction(sender, recipient, TransferCategory.credit, -amount);

        credit.setIdentifier(debit.getIdentifier());

    sender.setBalance(sender.getBalance() - amount);
    recipient.setBalance(recipient.getBalance() + amount);
    
    sender.getTransactionsList().addTransaction(credit);
    recipient.getTransactionsList().addTransaction(debit);
    
    }

    public Transaction[] getUserTransactions(int userId) {
        User user = UsersList.getUserById(userId);
        return user.getTransactionsList().toArray();
    }

    public void removeTransaction(int userId, UUID identifier) throws TransactionNotFoundException {
        User user = UsersList.getUserById(userId);
        user.getTransactionsList().removeTransaction(identifier);
    }

    public Transaction[] getUnpairedTransactions() {
    TransactionsList unpairedTransactions = new TransactionsLinkedList();
    Transaction[] allTransactionsArray = getArrayTransactions();

    if (allTransactionsArray != null) {
      for (Transaction transaction : allTransactionsArray) {
        int isPaired = 0;
        for (Transaction value : allTransactionsArray) {
          if (transaction.getIdentifier()
              .equals(value.getIdentifier())) {
            ++isPaired;
          }
        }
        if (isPaired < 2) {
          unpairedTransactions.addTransaction(transaction);
        }
      }
    }

    return unpairedTransactions.toArray();
}

  private Transaction[] getArrayTransactions() {
    TransactionsLinkedList allTransactions = new TransactionsLinkedList();
    for (int i = 0; i < UsersList.getNumberOfUsers(); ++i) {
      Transaction[] userTransactions = UsersList.getUserByIndex(i).getTransactionsList().toArray();
      if (userTransactions != null) {
        for (int j = 0; j < userTransactions.length; ++j) {
          allTransactions.addTransaction(userTransactions[j]);
        }
      }
    }
    return allTransactions.toArray();
  }
}
