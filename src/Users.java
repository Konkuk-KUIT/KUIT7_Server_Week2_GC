package src;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private final List<User> userList = new ArrayList<>();

    public void addUser(User user) {
        userList.add(user);
    }

    public User getUser(int index) {
        return userList.get(index);
    }
}
