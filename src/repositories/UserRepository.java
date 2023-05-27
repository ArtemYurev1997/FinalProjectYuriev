package repositories;

import entities.Account;
import entities.User;
import exceptions.AccountException;
import exceptions.ClientException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends FileWorker {
    public static List<User> users = new ArrayList<>();


    public static String FILE = "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/users";

    public User findByLogin(String clientLogin) {
        users = userList();
        return users.stream().filter(account -> account.getLogin().equals(clientLogin)).findFirst().orElseThrow();
    }

    public List<User> updateUsers() {
        Object object = deserializeObject(FILE);
        if (object instanceof List<?>) {
            users = (List<User>) object;
        }
        return users;
    }

    public List<User> userList()  {
        Object object = deserializeObject(FILE);
        List<User> user = new ArrayList<>();
        if (object instanceof List<?>) {
            user = (List<User>) object;
        }
        return user;
    }

    public User addUser(User user) {
        users = userList();
        users.add(user);
        serializeObject(users, FILE);
        return user;
    }

    public User deleteUser(User user) {
        users = userList();
        users.remove(user);
        serializeObject(users, FILE);
        return user;
    }

    public void saveChangesWithUsers() {
        serializeObject(users, FILE);
        updateUsers();
    }

    public User findById(Long userId) {
        users = userList();
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst().orElseThrow(() -> new ClientException("Пользователь с id " + userId + " не найден"));
    }

    public Long getLastId() {
        users = userList();
        if(users.isEmpty()) {
            return 0L;
        }
        return users.get(users.size() - 1).getId();
    }

    public User save(User user) {
        users.add(user);
        serializeObject(users, FILE);
        return user;
    }


}
