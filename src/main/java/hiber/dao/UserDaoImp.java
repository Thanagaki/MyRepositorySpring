package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {

      sessionFactory.getCurrentSession().save(user);
   }

   public void add(Car car){
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery <User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   public  User getUserByModelAndSeries(String model, int series){

   Query<User> query = sessionFactory.getCurrentSession().createQuery(
              "From User u JOIN u.car car WHERE car.model  = :carModel and car.series  = :carSeries",User.class)
              .setParameter("carModel", model)
              .setParameter("carSeries", series);

      return query.getSingleResult();

   }

}
