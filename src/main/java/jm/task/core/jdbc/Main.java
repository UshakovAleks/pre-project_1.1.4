package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();


        User user = new User("John", "Smith", (byte) 55);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());

        user = new User("Alex", "Musk", (byte) 18);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());

        user = new User("Ivan", "Petrov", (byte) 23);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());

        user = new User("Lera", "Ivanova", (byte) 44);
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());


        System.out.println("Users:");
        for (var a : userService.getAllUsers()) {
            System.out.println(a);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
        }

}
