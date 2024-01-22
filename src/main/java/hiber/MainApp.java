package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      carService.add(new Car("model1", 1, userService.findById(1)));
      carService.add(new Car("model2", 2, userService.findById(2)));
      carService.add(new Car("model3", 3, userService.findById(3)));
      carService.add(new Car("model4", 4, userService.findById(4)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + "-" + user.getCar().getSeries()); // car is fetched eager
         System.out.println();
      }

      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println("Id = " + car.getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println("Owner = " + car.getOwner().getFirstName()); // user is fetched eager
      }

      User user1 = userService.findByCarModelAndSeries("model1", 1);
      System.out.println(user1);
//      User notExistUser = userService.findByCarModelAndSeries("not model", 100500);
//      System.out.println(notExistUser);

      context.close();
   }
}
