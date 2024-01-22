package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User findById(long id);
    User findByCarModelAndSeries(String model, int series);
}
