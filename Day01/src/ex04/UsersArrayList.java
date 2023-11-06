package ex04;

public class UsersArrayList implements UsersList {
    private User[] users;
    private int size; // количество записей
    private int capacity; // размер массива

    public UsersArrayList() {
        this.capacity = 10;
        this.size = 0;
        this.users = new User[capacity];
    }

    private void increaseCapacity() {
        capacity = (capacity * 3) / 2 + 1;
        User[] newUsers = new User[capacity];
        System.arraycopy(users, 0, newUsers, 0, size);
        users = newUsers;
    }

    @Override
    public void addUser(User user) {
        if (size == capacity) {
            increaseCapacity();
        }
        users[size++] = user;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getIdentifier() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with ID " + id + " does not exist.");
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index < 0 || index >= size) {
            throw new UserNotFoundException("User with index " + index + " does not exist.");
        }
        return users[index];
    }

    @Override
    public int getNumberOfUsers() {
        return size;
    }
}