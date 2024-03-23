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
	
    /**
     * Updates the CPU BSTs to include a new product
     * @param newCPU the CPU to be added
     * @return if the product (CPU) was successfully added
     */
    public boolean addProduct(CPU newCPU, BST<CPU> cpusByName, BST<CPU> cpusByBrand, 
    		Comparator<T> cmpName, Comparator<T> cmpBrand) { 
    	//should change parameters to exist inside this class, static member variable?
    	//the BSTs need to be updated everywhere, and cannot return two BSTs within method
    	if(!getIsManager()) {
    		System.out.println("Invalid request: Restricted to manager");    		
    		return false;
    	}
    	cpusByName.insert(newCPU, cmpName);
    	cpusByBrand.insert(newCPU, cmpBrand);
    	return true;
    }
    
    /**
     * Updates an existing product's stock in the CPU BSTs
     * @return if the product (CPU) was successfully updated
     */
    public boolean updateProductStock(CPU updateCPU, int updateStock, BST<CPU> cpusByName, BST<CPU> cpusByBrand, 
    		Comparator<T> cmpName, Comparator<T> cmpBrand) {
    	//should change parameters to exist inside this class, static member variable?
    	//the BSTs need to be updated everywhere, and cannot return two BSTs within method
    	if(!getIsManager()) {
    		System.out.println("Invalid request: Restricted to manager");    		
    		return false;
    	}
    	if(cpusByName.search(updateCPU, cmpName) == null) {
    		System.out.println("Invalid request: The CPU Store does not carry this product");
    		return false;
    	}
    	CPU tempCPU = cpusByName.search(updateCPU, cmpName);
    	
    	cpusByName.remove(updateCPU, cmpName);
    	cpusByBrand.remove(updateCPU, cmpBrand);
    	
    	tempCPU.updateStock(updateStock); //need to add updateStock() method to CPU class, written below
    	
    	cpusByName.insert(tempCPU, cmpName);
    	cpusByBrand.insert(tempCPU, cmpBrand);
    	return true;
    }
    
    //COPY AND PASTE INTO CPU CLASS
    /**
     * Increases/Decreases the stock by adding to/subtracting from the existing stock.
     * @param stock the amount of stock to add
     */
    /*public void updateStock(int stock) {
       this.stock += stock;
      }
     */
    
    
    /**
     * Updates an existing product's stock in the CPU BSTs
     * @return if the product (CPU) was successfully updated
     */
    public boolean updateProductPrice(CPU updateCPU, double updatePrice, BST<CPU> cpusByName, BST<CPU> cpusByBrand, 
    		Comparator<T> cmpName, Comparator<T> cmpBrand) {
    	//should change parameters to exist inside this class, static member variable?
    	//the BSTs need to be updated everywhere, and cannot return two BSTs within method
    	if(!getIsManager()) {
    		System.out.println("Invalid request: Restricted to manager");    		
    		return false;
    	}
    	if(cpusByName.search(updateCPU, cmpName) == null) {
    		System.out.println("Invalid request: The CPU Store does not carry this product");
    		return false;
    	}
    	CPU tempCPU = cpusByName.search(updateCPU, cmpName);
    	
    	cpusByName.remove(updateCPU, cmpName);
    	cpusByBrand.remove(updateCPU, cmpBrand);
    	
    	tempCPU.setPrice(updatePrice); //need to add setters to CPU class
    	
    	cpusByName.insert(tempCPU, cmpName);
    	cpusByBrand.insert(tempCPU, cmpBrand);
    	return true;
    }
    
    /**
     * Updates the CPU BSTs to remove a product
     * @param removeCPU the CPU to be removed
     * @return if the product (CPU) was successfully removed
     */
    public boolean removeProduct(CPU removeCPU, BST<CPU> cpusByName, BST<CPU> cpusByBrand, 
    		Comparator<T> cmpName, Comparator<T> cmpBrand) { 
    	//should change parameters to exist inside this class, static member variable?
    	//the BSTs need to be updated everywhere, and cannot return two BSTs within method
    	if(!getIsManager()) {
    		System.out.println("Invalid request: Restricted to manager");    		
    		return false;
    	}
    	if(cpusByName.search(removeCPU, cmpName) == null) {
    		System.out.println("Invalid request: The CPU Store does not carry this product");
    		return false;
    	}
    	cpusByName.remove(removeCPU, cmpName);
    	cpusByBrand.remove(removeCPU, cmpBrand);
    	return true;
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
