/**
 * Employee.java
 * @author Shreyana Bolleddu
 * CIS 22C Team 1 Final Project
 */

public class Employee extends User {
	private boolean isManager; 
	
	/**CONSTRUCTORS*/
	
    /**
     * Creates a new Employee when only email
     * and password are known
     * @param username the User username
     * @param password the User password
     * Assigns firstName to "first name unknown"
     * Assigns lastName to "last name unknown"
     */
    public Employee(String username, String password) {
    	super(username, password);
    }
    
    /**
     * Creates a new Employee with name, username, and password information
     * @param username the User username
     * @param password the User password
     * @param firstName User first name
     * @param lastName User last name
     */
    public Employee(String firstName, String lastName, String username, String password) {
    	super(firstName, lastName, username, password);
	this.isManager = false;
    }
    
    /**
     * Creates a new Employee with all information
     * @param username the User username
     * @param password the User password
     * @param firstName User first name
     * @param lastName User last name
     * @param isManager whether the employee is a manager
     */
    public Employee(String firstName, String lastName, String username, String password, boolean isManager) {
    	super(firstName, lastName, username, password);
    	this.isManager = isManager;
    }
    
    /**ACCESSORS*/
    
    /**
     * Accesses the Employee manager status
     * @return a boolean representing whether the employee is a manager
     */
    public boolean getIsManager() {
       return isManager;
    }
    
    /**MUTATORS*/

    /**
     * Updates the Employee manager status
     * @param isManager a boolean representing whether the employee is a manager
     */
    public void setIsManager(boolean isManager) {
       this.isManager = isManager;
    }
	
    /**ADDITIONAL OPERATIONS*/

    /**
     * Creates a String of Employee information in the form
     * Name: <firstName> <lastName>
     * Username: <userName>
     * Manager: <isManager>
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Name: " + getFirstName() + " " + getLastName());
    	sb.append("\nUsername: " + getUsername());
    	sb.append("\nManager: " + isManager);
    	return sb.toString();
    }

    /**
     * Compares this Employee to another Object for equality.
     * @param obj another Object
     * @return true if obj is a Employee and has a matching username and password
     * to this Employee.
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
          Employee employee = (Employee) obj;
          return this.getUsername().equals(employee.getUsername()) 
          && this.getPassword().equals(employee.getPassword());
       }
    }

    /**
     * Returns a consistent hash code for each Employee by summing
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
