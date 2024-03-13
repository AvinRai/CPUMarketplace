/*
 * order.java

 * @author Trista Chen
 * CIS 22C Team 1 Final Project
 */
import java.util.LinkedList;
import java.util.Comparator;
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
}// end class Order

class customerUsernameComparator implements Comparator<Order> {
    /**
     * Compares the two orders by username of the customers who made the order
     * uses the String compareTo method to make the comparison
     * @param order1 the first order
     * @param order2 the second order
     * @return The comparison.
     */
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getCustomer().getUsername().compareTo(order2.getCustomer().getUsername());
    }
}
//waiting for priority queue to be finished. Assuming it will look a little like this...
class priorityComparator implements Comparator<Order> {
    /**
     * Compares the two orders by priority
     * uses which order is highest in the heap to determine which is of higher priority.
     * @param order1 the first order
     * @param order2 the second order
     * @return The comparison.
     */
    @Override
    public int compare(Order order1, Order order2) {
        Integer priority1 = (Integer) order1.getPriority();
        Integer priority2 = (Integer) order2.getPriority();
        
        return Integer.compare(priority1, priority2); 
    }
}

class orderIdComparator implements Comparator<Order> {
    /**
     * Compares the two orders by username of the customers who made the order
     * uses whichever order ID is higher
     * @param order1 the first order
     * @param order2 the second order
     * @return The comparison.
     */
    @Override
    public int compare(Order order1, Order order2) {
        Integer priority1 = (Integer) order1.getPriority();
        Integer priority2 = (Integer) order2.getPriority();
        
        return Integer.compare(priority1, priority2); 
    }

