/*
 * order.java
 * @author Trista Chen
 * CIS 22C Team 1 Final Project
 */
import java.util.LinkedList;
public class Order {
    private int orderId;
    private Customer customer;
    private String date;
    private LinkedList<CPU> orderContents;
    private int shippedSpeed;
    private int priority;

    /* Constructors */

    // Default constructor
    public Order() {
        this.orderId = 0;
        this.customer = null;
        this.date = "";
        this.orderContents = new LinkedList<>();
        this.shippedSpeed = 0;
        this.priority = 0;
    }

    public Order(int orderId, Customer customer, String date, LinkedList<CPU> orderContents,
                 int shippedSpeed, int priority) {
        this.orderId = orderId;
        this.customer = customer;
        this.date = date;
        this.orderContents = orderContents;
        this.shippedSpeed = shippedSpeed;
        this.priority = priority;
    }

    /* Accessors */

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {
        return date;
    }

    public LinkedList<CPU> getOrderContents() {
        return orderContents;
    }

    public int getShippedSpeed() {
        return shippedSpeed;
    }

    public int getPriority() {
        return priority;
    }

    /* Mutators */

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderContents(LinkedList<CPU> orderContents) {
        this.orderContents = orderContents;
    }

    public void setShippedSpeed(int shippedSpeed) {
        this.shippedSpeed = shippedSpeed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}