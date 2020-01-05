package data;

import java.time.LocalDate;
import java.util.Collection;

public interface OrdersDAO {
    boolean insert(Order order);

    boolean delete(Order order);

    Order findByID(int id);

    boolean update(Order order);

    boolean saveOrUpdate(Order order);

    Collection<Order> findByDate(LocalDate date);

    Collection<Order> findByOfficiant(Officiant officiant);
}
