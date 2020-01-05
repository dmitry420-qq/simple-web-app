package data.storage;

import data.ItemsDAO;
import data.OfficiantsDAO;
import data.OrdersDAO;
import data.ItemsDAOImpl;
import data.OfficiantsDAOImpl;
import data.OrdersDAOImpl;

public class SqlPerRequestDAOFactory extends DAOFactory {
    public OrdersDAO getOrdersDAO() {
        return new OrdersDAOImpl();
    }

    public OfficiantsDAO geOfficiantsDAO() {
        return new OfficiantsDAOImpl();
    }

    public ItemsDAO getItemsDAO() {
        return new ItemsDAOImpl();
    }
}