package hiber.dao;

import hiber.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User findById(long id);
   User findByCarModelAndSeries(String model, int series);
}
