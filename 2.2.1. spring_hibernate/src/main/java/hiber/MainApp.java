package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class MainApp {



   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"),
              new Car("BMW",3));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"),
              new Car("Audi",5));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"),
              new Car("Lada",9));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car_model = " + user.getCar().getModel());
         System.out.println("Car_series = " + user.getCar().getSeries());
         System.out.println();
      }

      Car car = new Car("Audi",5);
      System.out.println("\n Машиной: " + new Car("Audi",5)
              + " владеет:\n" + userService.getUserByCar(car));

      context.close();
   }
}
