package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);


      Car car1 = new Car("Lexus", 2019);
      Car car2 = new Car("Tayota", 2020);
      Car car3 = new Car("Honda", 2018);
      Car car4 = new Car("Jeep", 2022);


      userService.add(car1);
      userService.add(car2);
      userService.add(car3);
      userService.add(car4);


      userService.add(new User(car1, "User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User(car2,"User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User(car3, "User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User(car4,"User4", "Lastname4", "user4@mail.ru"));




      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
     User user = userService.getUserByModelAndSeries("Jeep", 2022);
      System.out.println(user.getId());

      context.close();
   }
}
