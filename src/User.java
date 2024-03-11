/*
 * @author Shreyana Bolleddu
 * CIS 22C Team 1 Final Project
 */

public abstract class User {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
    /**CONSTRUCTORS*/	
	
    /**
     * Creates a new User when only email
     * and password are known
     * @param username the User username
     * @param password the User password
     * Assigns firstName to "first name unknown"
     * Assigns lastName to "last name unknown"
     */
    public User(String username, String password) {
       this.username = username;
       this.password = password;
       this.firstName = "first name unknown";
       this.lastName = "last name unknown";
    }
    
    /**
     * Creates a new User
     * @param username the User username
     * @param password the User password
     * @param firstName User first name
     * @param lastName User last name
     */
    public User(String firstName, String lastName, String username, String password) {
       this.username = username;
       this.password = password;
       this.firstName = firstName;
       this.lastName = lastName;
    }
	
    /**ACCESSORS*/

    /**
     * Accesses the User first name
     * @return the first name
     */
    public String getFirstName() {
       return firstName;
    }
    
    /**
     * Accesses the User last name
     * @return the last name
     */
    public String getLastName() {
       return lastName;
    }
	
    /**
     * Accesses the User username
     * @return the username
     */
    public String getUsername() {
    	return username;
    }
    
    /**
     * Accesses the User password
     * @return the password
     */
    public String getPassword() {
    	return password;
    }
    
    /**MUTATORS*/

    /**
     * Updates the User first name
     * @param firstName a new first name
     */
    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }

    /**
     * Updates the User last name
     * @param lastName a new last name
     */
    public void setLastName(String lastName) {
       this.lastName = lastName;
    }

    /**
     * Updates the value of the username
     * @param name the User username
     */
    public void setEmail(String username) {
       this.username = username;
    }

    /**
     * Updates the value of the password
     * @param password the User password
     */
    public void setPassword(String password) {
       this.password = password;
    }
}
