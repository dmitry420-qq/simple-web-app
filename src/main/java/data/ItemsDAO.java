package data;

import java.util.Collection;

public interface ItemsDAO {
    public boolean insert(Item item);

    public boolean delete(Item item);

    public Item findByID(int id);

    public boolean update(Item item);

    public boolean saveOrUpdate(Item item);

    public Collection<Item> findByName(String name);

    public Collection<Item> findByCost(Double cost);

    Collection<Item> getAll();
}
