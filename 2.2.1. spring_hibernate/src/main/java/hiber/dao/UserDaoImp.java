package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u left join fetch u.car");
        return query.getResultList();
    }

    @Override
    public User findUserByCar(String model, int series) {
        String hql = "from Car c left join fetch c.user where c.model=:model and c.series=:series";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model);
        query.setParameter("series", series);
        Car car = (Car) query.setMaxResults(1).getSingleResult();
        return car.getUser();
    }
}
