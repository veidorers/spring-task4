package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("select u from User u", User.class).getResultList();
   }

   @Override
   public User findById(long id) {
      return sessionFactory.getCurrentSession().get(User.class, id);
   }

   @Override
   public User findByCarModelAndSeries(String model, int series) {
      return sessionFactory.getCurrentSession().createQuery("select u from User u " +
                                                            "JOIN u.car c " +
                                                            "WHERE c.model = :model AND c.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getSingleResult();
   }
}
