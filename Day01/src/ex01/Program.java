package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Mary", 2000);
        User user2 = new User("Tom", 4000);

        System.out.println(user1);
        System.out.println(user2);

        System.out.println(UserIdsGenerator.getInstance().generateId());

    }
}
