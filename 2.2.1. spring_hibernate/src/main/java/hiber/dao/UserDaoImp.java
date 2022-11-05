package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      Session session = sessionFactory.getCurrentSession();
      user.setCar(car);
      session.save(user);
   }
   @Override
   public User getUserByCar(Car car) {
      String hql = "FROM User WHERE car.model=: model AND car.series=: series";

      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class)
              .setParameter("model", car.getModel())
              .setParameter("series", car.getSeries());

      return query.getSingleResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
