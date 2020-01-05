package data;


import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemsDAOImpl implements ItemsDAO {
    private DataSource dataSource;

    public ItemsDAOImpl() {
        try {
            InitialContext initialContext = new InitialContext();
            Context env = (Context) initialContext.lookup("java:/comp/env");
            dataSource = (DataSource) env.lookup("jdbc/db");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Item item) {
        String query = "INSERT INTO items (id, name, description, cost) VALUES (?,?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, item.getId());
                statement.setString(2, item.getName());
                statement.setString(3, item.getDescription());
                statement.setDouble(4, item.getCost());
                return statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Item item) {
        String query = "DELETE FROM items WHERE id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, item.getId());
                return statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Item findByID(int id) {
        String query = "SELECT * FROM items WHERE id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    rs.next();
                    Item item = new Item(id, rs.getString("name"), rs.getString("description"),
                            rs.getDouble("cost"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Item item) {
        String query = "UPDATE items SET name = ?, description = ?, cost = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, item.getName());
                statement.setString(2, item.getDescription());
                statement.setDouble(3, item.getCost());
                statement.setInt(4, item.getId());
                return statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveOrUpdate(Item item) {
        String query = "SELECT * FROM items WHERE id = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, item.getId());
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return update(item);
                } else {
                    return insert(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Collection<Item> findByName(String name) {
        List<Item> list = new ArrayList<>();
        String query = "SELECT * FROM items WHERE name = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        list.add(new Item(rs.getInt("id"), rs.getString("name"),
                                rs.getString("description"), rs.getDouble("cost")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Collection<Item> findByCost(Double cost) {
        List<Item> list = new ArrayList<>();
        String query = "SELECT * FROM items WHERE name = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, cost);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        list.add(new Item(rs.getInt("id"), rs.getString("name"),
                                rs.getString("description"), rs.getDouble("cost")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Collection<Item> getAll() {
        List<Item> list = new ArrayList<>();
        String query = "select * from items";
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery(query)) {
                    while (rs.next()) {
                        list.add(new Item(rs.getInt("id"), rs.getString("name"),
                                rs.getString("description"), rs.getDouble("cost")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
