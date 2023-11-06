package ex02;

public class Program {
    public static void main(String[] args) {
        UsersList list = new UsersArrayList();
        
        // add users
        list.addUser(new User("Nina", 1000));
        list.addUser(new User("Lena", 500));
        list.addUser(new User("Anton", 2000));

        System.out.println(list.getUserByIndex(0));
        System.out.println(list.getUserByIndex(1));
        System.out.println(list.getUserByIndex(2));

        // get user by ID
        User user1 = list.getUserById(1);
        System.out.println(user1);
            
        // get user by index
        User user2 = list.getUserByIndex(0);
        System.out.println(user2);
            
        // get number of users
        int numberOfUsers = list.getNumberOfUsers();
        System.out.println("Number of users: " + numberOfUsers);
        
        try {
            // getException
            User user3 = list.getUserByIndex(5);
            System.out.println(user3);

        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
