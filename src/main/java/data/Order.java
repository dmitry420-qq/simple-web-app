package data;

import java.time.LocalDate;
import java.util.Map;

public class Order {
    private int id;
    private LocalDate date;
    private Officiant officiant;
    private Map<Item, Integer> items;

    public Order() {
    }

    public Order(LocalDate date, Officiant officiant, Map<Item, Integer> items) {
        this.date = date;
        this.officiant = officiant;
        this.items = items;
    }

    protected Order(int id, LocalDate date, Officiant officiant, Map<Item, Integer> items) {
        this.id = id;
        this.date = date;
        this.officiant = officiant;
        this.items = items;
    }

    public LocalDate getDate() {
        return date;
    }

    public Officiant getOfficiant() {
        return officiant;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOfficiant(Officiant officiant) {
        this.officiant = officiant;
    }

    public void addItem(Item item) {
        addItem(item, 1);
    }

    public void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.computeIfPresent(item, (k, v) -> v + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public void setQuantity(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.computeIfPresent(item, (k, v) -> quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public Map<Item, Integer> getItemsMap() {
        return items;
    }

    protected void setId() {
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
