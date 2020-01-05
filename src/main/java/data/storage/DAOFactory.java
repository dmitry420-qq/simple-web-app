package data.storage;

import data.OrdersDAO;
import data.ItemsDAO;
import data.OfficiantsDAO;

public abstract class DAOFactory {

    private static DAOFactory instance;

    protected DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new SqlPerRequestDAOFactory();
        }
        return instance;
    }

    public abstract OrdersDAO getOrdersDAO();

    public abstract OfficiantsDAO geOfficiantsDAO();

    public abstract ItemsDAO getItemsDAO();

}
