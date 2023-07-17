package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
                new Car("BMW", 5)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                new Car("lada", 2112)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                new Car("AUDI", 7)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",
                new Car("PEUGEOT", 301)));

//        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
//        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
//        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
//        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
//
//        Car car1 = new Car("BMW", 5);
//        Car car2 = new Car("lada", 2112);
//        Car car3 = new Car("AUDI", 7);
//        Car car4 = new Car("PEUGEOT", 301);
//
//
//        userService.add(user1.setCar(car1).setUser(user1));
//        userService.add(user2.setCar(car2).setUser(user2));
//        userService.add(user3.setCar(car3).setUser(user3));
//        userService.add(user4.setCar(car4).setUser(user4));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        try {
            User user = userService.findByUser("AUDI", 7);
            System.out.println(user);
        } catch (NoResultException e) {
            System.out.println("Нет такого Users с такой машиной");
        }


        context.close();
    }
}
