package data;

import java.util.Set;

public class Officiant {
    private int id;
    private String firstName;
    private String secondName;
    private Set<Order> orders;

    public Officiant() {

    }

    public Officiant(String firstName, String secondName, Set<Order> orders) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.orders = orders;
    }

    protected Officiant(int id, String firstName, String secondName, Set<Order> orders) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void add(Order order) {
        orders.add(order);
    }

    public Order remove(Order order) {
        return orders.remove(order) ? order : null;
    }

    public boolean contains(Order order) {
        return orders.contains(order);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setId() {

    }

    public int getId() {
        return id;
    }
}
