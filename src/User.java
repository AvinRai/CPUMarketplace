public abstract class User {
  private String firstName;
	private String lastName;
	private String username;
	private String password;
		
    /**ACCESSORS*/

    /**
     * Accesses the customer first name
     * @return the first name
     */
    public String getFirstName() {
       return firstName;
    }
    
    /**
     * Accesses the customer last name
     * @return the last name
     */
    public String getLastName() {
       return lastName;
    }
	
    /**
     * Accesses the customer username
     * @return the username
     */
    public String getUsername() {
    	return username;
    }
    
    /**
     * Accesses the customer password
     * @return the password
     */
    public String getPassword() {
    	return password;
    }
    
    /**MUTATORS*/

    /**
     * Updates the user first name
     * @param firstName a new first name
     */
    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }

    /**
     * Updates the user last name
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
