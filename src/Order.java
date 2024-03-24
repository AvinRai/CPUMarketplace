/*
 * Order.java
 * @author Trista Chen
 * CIS 22C Team 1 Final Project
 */
import java.util.LinkedList;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
public class Order {
    private int orderId;
    private Customer customer;
    private LocalDateTime dateTime;
    private LinkedList<CPU> orderContents;
    private String shippedSpeed;
    private int priority;

    /* Constructors */

    // Default constructor
    public Order() {
        this.orderId = 0;
        this.customer = null;
        this.dateTime = null;
        this.orderContents = new LinkedList<>();
        this.shippedSpeed = "nil";
        this.priority = 0;
    }

    public Order(int orderId, Customer customer, LocalDateTime dateTime, LinkedList<CPU> orderContents,
                 String shippedSpeed, int priority) {
        this.orderId = orderId;
        this.customer = customer;
        this.dateTime = dateTime;
        this.orderContents = orderContents;
        this.shippedSpeed = shippedSpeed;
        this.priority = calculatePriority(dateTime, shippedSpeed);
    }

    /* Accessors */

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the current date and time using the formatter
        return dateTime.format(formatter);
    }

    public LinkedList<CPU> getOrderContents() {
        return orderContents;
    }

    public String getShippedSpeed() {
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

    public void setDate(LocalDateTime date) {

        this.dateTime = date;
    }

    public void setOrderContents(LinkedList<CPU> orderContents) {
        this.orderContents = orderContents;
    }

    public void setShippedSpeed(String shippedSpeed) {
        this.shippedSpeed = shippedSpeed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Determines if 2 orders are the same
     * @param obj order that this order will be compared to
     * @return true if both orders are the same, false otherwise
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        } else if (!(obj instanceof Order)) {
            return false;
        } else {
            Order temp = (Order) obj;
            return this.orderId == temp.orderId &&
            this.customer.equals(temp.customer) &&
            this.dateTime.equals(temp.dateTime) &&
                    this.shippedSpeed.equals(temp.shippedSpeed) &&
            this.priority == temp.priority &&
            this.orderContents.equals(temp.orderContents);
        }
    }
    // Method to calculate priority value based on order date/time and shipping speed
    public int calculatePriority(LocalDateTime orderDateTime, String shippingSpeed) {
        // Define priority factors (adjust these values based on your requirements)
        final int BASE_PRIORITY = 100;  // Base priority value
        final int DATE_FACTOR = 10;      // Priority increase per day
        final int SPEED_FACTOR = 20;     // Priority increase per faster shipping speed

        // Calculate days until order needs to be delivered
        LocalDateTime currentDateTime = LocalDateTime.now();
        long daysUntilDelivery = ChronoUnit.DAYS.between(currentDateTime, orderDateTime);

        // Calculate priority based on days until delivery and shipping speed
        int priority = BASE_PRIORITY - (int) (daysUntilDelivery * DATE_FACTOR);

        switch (shippingSpeed) {
            case "standard":
                priority -= 0;  // No change in priority for standard shipping
                break;
            case "rush":
                priority -= SPEED_FACTOR;  // Increase priority for express shipping
                break;
            case "overnight":
                priority -= 2 * SPEED_FACTOR;  // Increase priority even more for overnight shipping
                break;
            // Add more cases if needed for other shipping speeds
            default:
                System.out.println("Unknown shipping speed: " + shippingSpeed);
        }

        return priority;
    }
}// end class Order

class CustomerUsernameComparator implements Comparator<Order> {
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
class PriorityComparator implements Comparator<Order> {
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

class OrderIdComparator implements Comparator<Order> {
    /**
     * Compares the two orders by username of the customers who made the order
     * uses whichever order ID is higher
     *
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


