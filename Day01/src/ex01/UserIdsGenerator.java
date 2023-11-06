package ex01;

public class UserIdsGenerator {
    private static UserIdsGenerator instance = new UserIdsGenerator();
    private static int id = 0;

    public UserIdsGenerator() {}

    public static UserIdsGenerator getInstance() {
        return instance;
    }

    public int generateId() {
        return ++id;
    }
}
