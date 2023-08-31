package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersArchive {
    private static final List<User> userList = new ArrayList<>();

    public static  Optional<User> getLastAddedUser() {
        return Optional.ofNullable(userList.get(userList.size() - 1));
    }

    public static void addUser(User user) {
        userList.add(user);
    }
}
