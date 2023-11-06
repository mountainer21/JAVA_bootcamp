package ex05;

public class User {
    private final int id;
    private String name;
    private double balance;
    private TransactionsList transactionsList;

    public User(String name, double balance) {
        if (balance < 0) {
            System.err.println("Balance less than 0");
            System.exit(-1);
        }
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
        this.transactionsList = new TransactionsLinkedList();
    }

    public int getIdentifier() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }


    @Override
    public String toString() {
        return "\nIdentifier = " + id
                + "\nName = " + name
                + "\nBalance = " + balance;
    }
}