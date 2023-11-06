package ex01;

public class User {
    private final int id;
    private String name;
    private double balance;

    public User(String name, double balance) {
        if (balance < 0) {
            System.err.println("Balance less than 0");
            System.exit(-1);
        }
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
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

@Override
  public String toString() {
    return "\nIdentifier = " + id
        + "\nName = " + name
        + "\nBalance = " + balance;
  }
}