/*
 * @author Shreyana Bolleddu
 * CIS 22C Team 1 Final Project
 */

public abstract class User {
	private String firstName;
	private String lastName;
	private String username;
	private String password;	
	
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

