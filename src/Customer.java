/**
 * @author Shreyana Bolleddu
 * CIS 22C Team 1 Final Project
 */

import java.time.LocalDateTime;
//import order package/class, add later

public class Customer extends User {
	private String address;
	private String city;
	private String state;
	private String zip;
	private LinkedList<Order> shippedOrders;
	private LinkedList<Order> unshippedOrders;
	
	/**CONSTRUCTORS*/
	
    /**
     * Creates a new Customer when only email
     * and password are known
     * @param username the User username
     * @param password the User password
     * Assigns firstName to "first name unknown"
     * Assigns lastName to "last name unknown"
     */
    public Customer(String username, String password) {
    	super(username, password);
        this.address = "address unknown";
        this.city = "city unknown";
        this.state = "state unknown";
        this.zip = "zip unknown";
        this.shippedOrders = new LinkedList<Order>();
        this.unshippedOrders = new LinkedList<Order>();
    }
    
    /**
     * Creates a new Customer with name, username, and password information
     * @param username the User username
     * @param password the User password
     * @param firstName User first name
     * @param lastName User last name
     */
    public Customer(String firstName, String lastName, String username, String password) {
    	super(firstName, lastName, username, password);
	    this.address = "address unknown";
    	this.city = "city unknown";
    	this.state = "state unknown";
    	this.zip = "zip unknown";
    	this.shippedOrders = new LinkedList<Order>();
    	this.unshippedOrders = new LinkedList<Order>();
    }
    
    /**
     * Creates a new Customer when all information is known
     * @param username the User username
     * @param password the User password
     * @param firstName User first name
     * @param lastName User last name
     */
    public Customer(String firstName, String lastName, String username, String password,
    		String address, String city, String state, String zip, LinkedList<Order> shippedOrders, 
    		LinkedList<Order> unshippedOrders) { 
    	//constructor can be changed to accommodate ArrayList for orders depending on code
    	super(firstName, lastName, username, password);
    	this.address = address;
    	this.city = city;
    	this.state = state;
    	this.zip = zip;
    	this.shippedOrders = shippedOrders;
    	this.unshippedOrders = unshippedOrders;
    	
    	//sample code if using ArrayList for Orders
    	/*
        for (int i = 0; i < al.size(); i++) {
          shippedOrders.addLast(al.get(i));
          unshippedOrders.addLast(al.get(i));
        }
        */
    }
	
    /*ACCESSORS*/

    /**
     * Accesses the Customer address
     * @return the address
     */
    public String getAddress() {
       return address;
    }
    
    /**
     * Accesses the Customer city
     * @return the city
     */
    public String getCity() {
       return city;
    }
	
    /**
     * Accesses the Customer state
     * @return the state
     */
    public String getState() {
    	return state;
    }
    
    /**
     * Accesses the Customer zip
     * @return the zip
     */
    public String getZip() {
    	return zip;
    }
    
    /**
     * Accesses the Customer list of shipped orders
     * @return the list of shipped orders
     */
    public LinkedList<Order> getShippedOrders() {
    	return shippedOrders;
    }
    
    /**
     * Accesses the Customer list of unshipped orders
     * @return the list of unshipped orders
     */
    public LinkedList<Order> getUnshippedOrders() {
    	return unshippedOrders;
    }
    
    /**
     * Accesses whether any shipped orders exist for this customer.
     * @return whether the customer currently has an shipped orders.
     */
    public boolean hasShippedOrders() {
       return !shippedOrders.isEmpty();
    }
    
    /**
     * Accesses whether any unshipped orders exist for this customer.
     * @return whether the customer currently has an unshipped orders.
     */
    public boolean hasUnshippedOrders() {
       return !unshippedOrders.isEmpty();
    }
	
    public int getTotalNumOrders() {
    	return shippedOrders.getLength() + unshippedOrders.getLength();
    }
    
    /**MUTATORS*/

    /**
     * Updates the Customer address
     * @param address a new address
     */
    public void setAddress(String address) {
       this.address = address;
    }
    
    /**
     * Updates the Customer city
     * @param city a new city
     */
    public void setCity(String city) {
       this.city = city;
    }
	
    /**
     * Updates the Customer state
     * @param state the new state
     */
    public void setState(String state) {
    	this.state = state;
    }
    
    /**
     * Updates the Customer zip
     * @param zip of the new zip
     */
    public void setZip(String zip) {
    	this.zip = zip;
    }
    
    /**
     * Updates the Customer list of shipped orders
     * @param shippedOrders the new list of shipped orders
     */
    public void setShippedOrders(LinkedList<Order> shippedOrders) {
    	this.shippedOrders = shippedOrders;
    }
    
    /**
     * Updates the Customer list of unshipped orders
     * @param unshippedOrders the new list of unshipped orders
     */
    public void setUnshippedOrders(LinkedList<Order> unshippedOrders) {
    	this.unshippedOrders = unshippedOrders;
    }
    
    //will update the below methods as needed, may need different methods depending
    //customer interface
    
    public void addOrder(Order order) {
      	unshippedOrders.addLast(order);
    }
    
    public void addShippedOrder(Order order) {
    	shippedOrders.addLast(order);
    }
    
    /**ADDITIONAL OPERATIONS*/

    /**
     * Creates a String of Customer information in the form
     * Name: <firstName> <lastName>
     * Username: <userName>
     * Address: <address> 
     * <city>, <state> <zip>
     * Order History
     * Shipped Orders: 
     * <shippedOrders>
     * Unshipped Orders: 
     * <unshippedOrders>
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Name: " + getFirstName() + " " + getLastName());
    	sb.append("\nUsername: " + getUsername());
    	sb.append("\nAddress: " + address);
    	sb.append("\n" + city + ", " + state + " " + zip);
    	sb.append("\nOrder History");
    	sb.append("\nShipped Orders\n" + shippedOrders.toString());
    	sb.append("\nUnshipped Orders\n" + unshippedOrders.toString());
        return sb.toString();
    }
	
    /**
     * Creates a string of customer information for file writing purposes
     * @return a string of customer information
     */
    public String toStringForFile() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("\n" + getFirstName());
    	sb.append("\n" + getLastName());
    	sb.append("\n" + getUsername());
    	sb.append("\n" + getPassword());
    	sb.append("\n" + getAddress());
    	sb.append("\n" + getCity());
    	sb.append("\n" + getState());
    	sb.append("\n" + getZip());
    	sb.append("\n" + getTotalNumOrders());
    	if (!shippedOrders.isEmpty()) {
    		//sb.append("\nshipped");
    		shippedOrders.positionIterator();
    		for (int i = 0; i < shippedOrders.getLength(); i++) {
    			sb.append("\nshipped");
    			Order currentOrder = shippedOrders.getIterator();
    			sb.append("\n" + currentOrder.getOrderId());
    			LinkedList<CPU> currentCPUList = currentOrder.getOrderContents();
    			sb.append("\n" + currentCPUList.getLength());
    			currentCPUList.positionIterator();
    			for (int j = 0; j < currentCPUList.getLength(); j++) {
    				sb.append("\n" + currentCPUList.getIterator().getName());
    				currentCPUList.advanceIterator();
    			}
    			sb.append("\n" + currentOrder.getShippedSpeed());
    			shippedOrders.advanceIterator();
    		}
    	}
    	
    	if (!unshippedOrders.isEmpty()) {
    		//sb.append("\nunshipped");
    		unshippedOrders.positionIterator();
    		for (int i = 0; i < unshippedOrders.getLength(); i++) {
    			sb.append("\nunshipped"); 
    			Order currentOrder = unshippedOrders.getIterator();
    			sb.append("\n" + currentOrder.getOrderId());
    			LinkedList<CPU> currentCPUList = currentOrder.getOrderContents();
    			sb.append("\n" + currentCPUList.getLength());
    			currentCPUList.positionIterator();
    			for (int j = 0; j < currentCPUList.getLength(); j++) {
    				sb.append("\n" + currentCPUList.getIterator().getName());
    				currentCPUList.advanceIterator();
    			}
    			sb.append("\n" + currentOrder.getShippedSpeed());
    			unshippedOrders.advanceIterator();
    		}
    	}
    	return sb.toString();
    }


    /**
     * Prints out all the Customer shipped orders.
     */
    public String printShippedOrders() {
       if (shippedOrders.getLength() == 0) {
           return "No shipped orders!\n";
       }
       return shippedOrders.toString();
    }

    /**
     * Prints out all the Customer unshipped orders.
     */
    public String printUnshippedOrders() {
      if (unshippedOrders.getLength() == 0) {
          return "No unshipped orders!\n";
      }
        return unshippedOrders.toString();
    }

    /**
     * Compares this Customer to another Object for equality.
     * @param obj another Object
     * @return true if obj is a Customer and has a matching username and password
     * to this Customer.
     */
    @Override
    public boolean equals(Object obj) {
       if (this == obj) {
          return true;
       }
       else if (!(obj instanceof Customer)) {
          return false;
       }
       else {
          Customer customer = (Customer) obj;
          return this.getUsername().equals(customer.getUsername()) 
          && this.getPassword().equals(customer.getPassword());
       }
    }

    /**
     * Returns a consistent hash code for each Customer by summing
     * the Unicode values of each character in the key.
     * Key = username + password
     * @return the hash code
     */
    @Override
    public int hashCode() {
       int sum = 0;
       String key = getUsername() + getPassword(); 
       for (int i = 0; i < key.length(); i++) {
          sum += (int)key.charAt(i);
       }
       return sum;
    }
}
