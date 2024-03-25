/**
 * CustomerInterface.java
 * @author DSA Team 1
 * Team 1 Final Project
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {

    static User user;
    static BST<CPU> cpusByName;
    static BST<CPU> cpusByPrice;
    static PriorityComparator priorityComparator;
    static OrderIdComparator orderIdComparator;
    static CpuNameComparator cpuNameComparator;
    static CpuPriceComparator cpuPriceComparator;
    static HashTable<Customer> customers;
    static HashTable<Employee> employees;
    static Scanner input;
    static final int SIZE = 100;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        customers = new HashTable<>(SIZE);
        employees = new HashTable<>(SIZE);
        priorityComparator = new PriorityComparator();
        orderIdComparator = new OrderIdComparator();
        cpuNameComparator = new CpuNameComparator();
        cpuPriceComparator = new CpuPriceComparator();
        cpusByName = new BST<>();
        cpusByPrice = new BST<>();
        System.out.println("Welcome to the CPU Store!");
        inputInfo(customers, employees, cpusByName, cpusByPrice, cpuNameComparator, cpuPriceComparator);
        User user = logIn(input, customers, employees);
        if (user instanceof Customer) {
            customerInterface(cpusByName, cpusByPrice, user, input);
        } else if (user instanceof Employee) {
            employeeInterface(cpusByName, cpusByPrice, user, input);
        }
    }

    /**
     * Interface for Employee Users
     * @param cpusByName  BST of cpus sorted by name
     * @param cpusByPrice BST of cpus sorted by cpusByPrice
     * @param user of current user
     * @param input to read user input
     */
    private static void employeeInterface(BST<CPU> cpusByName, BST<CPU> cpusByPrice, User user, Scanner input) {
        System.out.println("Welcome to Microcenter's CPU store!");
        boolean finished1 = false;
        boolean finished2;
        int choice;
        int searchOption;
        String searchKey;
        while(!finished1) {
            System.out.println("Customer Options: ");
            System.out.println("1: Search for a product");
            System.out.println("2: List Database of Products");
            System.out.println("3: Place an order");
            System.out.println("4: View Purchases");
            System.out.println("5: Quit/Finish ");
            System.out.print("Please enter your option:  ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    //call method for search product

                case 2:
                    //call method to list database of products

                case 3:
                    finished2 = false;
                    while(!finished2) {
                        System.out.println("Place Order Options:\n1: Search by order id\n2: Search by customer name");
                        System.out.print("Please enter the number of your option: ");
                        searchOption = input.nextInt();
                        input.nextLine();
                        switch(searchOption) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            default:
                        }
                    }
                    //call method to place order

                case 4:
                    finished2 = false;
                    while(!finished2) {
                        System.out.println("View Purchases Options:\n1: View Shipped orders\n2: View Unshipped orders");
                        System.out.print("Please enter the number of your option: ");
                        searchOption = input.nextInt();
                        input.nextLine();
                        if (searchOption == 1) {
                            //call method for view shipped orders
                            finished2 = true;
                        } else if (searchOption == 2) {
                            //call method for view unshipped orders
                            finished2 = true;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                    //call method to view purchases
                case 5:
                    //call read to file and quit method
                    System.out.println("Quiting program. Thanks for choosing Microcenter's CPU Store!");
                    finished1 = true;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    /**
     * Interface for Customer Users
     * @param cpusByName  BST of cpus sorted by name
     * @param cpusByPrice BST of cpus sorted by price
     * @param user of current user
     * @param input to read user input
     */
    private static void customerInterface(BST<CPU> cpusByName, BST<CPU> cpusByPrice, User user, Scanner input) {
        System.out.println("Welcome to Microcenter's CPU store!");
        boolean finished1 = false;
        boolean finished2;
        int choice;
        int searchOption;
        String searchKey;
        while(!finished1) {
            System.out.println("Customer Options: ");
            System.out.println("1: Search for a product");
            System.out.println("2: List Database of Products");
            System.out.println("3: Place an order");
            System.out.println("4: View Purchases");
            System.out.println("5: Quit/Finish ");
            System.out.print("Please enter your option:  ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    //call method for search product

                case 2:
                    //call method to list database of products

                case 3:
                    finished2 = false;
                    while(!finished2) {
                        System.out.println("Place Order Options:\n1: Search by order id\n2: Search by customer name");
                        System.out.print("Please enter the number of your option: ");
                        searchOption = input.nextInt();
                        input.nextLine();
                        switch(searchOption) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            default:
                        }
                    }
                    //call method to place order

                case 4:
                    finished2 = false;
                    while(!finished2) {
                        System.out.println("View Purchases Options:\n1: View Shipped orders\n2: View Unshipped orders");
                        System.out.print("Please enter the number of your option: ");
                        searchOption = input.nextInt();
                        input.nextLine();
                        if (searchOption == 1) {
                            //call method for view shipped orders
                            finished2 = true;
                        } else if (searchOption == 2) {
                            //call method for view unshipped orders
                            finished2 = true;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                    //call method to view purchases
                case 5:
                    //call read to file and quit method
                    System.out.println("Quiting program. Thanks for choosing Microcenter's CPU Store!");
                    finished1 = true;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

/* EMPLOYEE METHODS*/

    /**
     * Updates the CPU BSTs to include a new product
     * @param emp current employee user
     * @param newCPU the CPU to be added
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByPrice BST of cpus sorted by price
     * @param cmpName compares CPUs by name
     * @param cmpPrice compares CPUs by price
     * @return if the product (CPU) was successfully added
     */
    public boolean addProduct(Employee emp, CPU newCPU, BST<CPU> cpusByName, BST<CPU> cpusByPrice,
                              CpuNameComparator cmpName, CpuPriceComparator cmpPrice) {
        if (!emp.getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        cpusByName.insert(newCPU, cmpName);
        cpusByPrice.insert(newCPU, cmpPrice);
        return true;
    }

    /**
     * Updates an existing product's stock in the CPU BSTs
     * @param emp current employee
     * @param updateCPU the CPU to be updated
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByPrice BST of cpus sorted by price
     * @param cmpName compares CPUs by name
     * @param cmpPrice compares CPUs by price
     * @return if the product (CPU) was successfully updated
     */

    public boolean updateProductStock(Employee emp, CPU updateCPU, int updateStock, BST<CPU> cpusByName,
                                      BST<CPU> cpusByPrice, CpuNameComparator cmpName, CpuPriceComparator cmpPrice) {
        if (!emp.getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        if (cpusByName.search(updateCPU, cmpName) == null) {
            System.out.println("Invalid request: The CPU Store does not carry this product");
            return false;
        }
        CPU tempCPU = cpusByName.search(updateCPU, cmpName);

        cpusByName.remove(updateCPU, cmpName);
        cpusByPrice.remove(updateCPU, cmpPrice);

        tempCPU.updateStock(updateStock); //need to add updateStock() method to CPU class, written below

        cpusByName.insert(tempCPU, cmpName);
        cpusByPrice.insert(tempCPU, cmpPrice);
        return true;
    }

    /**
     * Updates an existing product's price in the CPU BSTs
     * @param emp current employee
     * @param updateCPU the CPU to be updated
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByPrice BST of cpus sorted by price
     * @param cmpName compares CPUs by name
     * @param cmpPrice compares CPUs by price
     * @return if the product (CPU) was successfully updated
     */
    public boolean updateProductPrice(Employee emp, CPU updateCPU, double updatePrice, BST<CPU> cpusByName,
                                      BST<CPU> cpusByPrice, CpuNameComparator cmpName, CpuPriceComparator cmpPrice) {
        if (!emp.getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        if (cpusByName.search(updateCPU, cmpName) == null) {
            System.out.println("Invalid request: The CPU Store does not carry this product");
            return false;
        }
        CPU tempCPU = cpusByName.search(updateCPU, cmpName);

        cpusByName.remove(updateCPU, cmpName);
        cpusByPrice.remove(updateCPU, cmpPrice);

        tempCPU.updatePrice(updatePrice); //need to add setters to CPU class

        cpusByName.insert(tempCPU, cmpName);
        cpusByPrice.insert(tempCPU, cmpPrice);
        return true;
    }

    /**
     * Updates the CPU BSTs to remove a product
     * @param emp current employee
     * @param removeCPU the CPU to be removed
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByPrice BST of cpus sorted by price
     * @param cmpName compares CPUs by name
     * @param cmpPrice compares CPUs by price
     * @return if the product (CPU) was successfully removed
     */
    public boolean removeProduct(Employee emp, CPU removeCPU, BST<CPU> cpusByName, BST<CPU> cpusByPrice,
                                 CpuNameComparator cmpName, CpuPriceComparator cmpPrice) {
        if (!emp.getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        if (cpusByName.search(removeCPU, cmpName) == null) {
            System.out.println("Invalid request: The CPU Store does not carry this product");
            return false;
        }
        cpusByName.remove(removeCPU, cmpName);
        cpusByPrice.remove(removeCPU, cmpPrice);
        return true;
    }

    /*CUSTOMER METHODS8/

     */
    /**
     * Searches for a product depending on the key passed
     * @param cpusByName the cpus available
     * @param key the key provided. either the model name or the price
     * @param cpuNameComparator compares CPUs by name
     * @param cpuPriceComparator compares CPUs by price
     * @return the cpu searched for if it exists
     */
    private static CPU searchForProduct(BST<CPU> cpusByName, CPU key, CpuNameComparator cpuNameComparator, CpuPriceComparator cpuPriceComparator) {
        CPU findByName = cpusByName.search(key, cpuNameComparator);
        CPU findByValue = cpusByName.search(key, cpuPriceComparator);

        if (findByName != null) {
            return findByName;
        } else if (findByValue != null) {
            return findByValue;
        } else {
            return null;
        }

    }

    /* ADDITIONAL METHODS*/

    /**
     * Searches for a product depending on the key passed
     * @param cpusByName the cpus available
     * @param key the key provided. either the model name or the price
     * @param cpuNameComparator compares CPUs by name
     * @param cpuPriceComparator compares CPUs by price
     * @return the cpu searched for if it exists
     */
    private static CPU searchForProduct(BST<CPU> cpusByName, CPU key, CpuNameComparator cpuNameComparator, CpuPriceComparator cpuPriceComparator) {
        System.out.print("Please enter the model name or the price of the cpu you are looking for: ");
        String key = input.next();
        CPU cpuToLookFor;
        if (key.matches("\\d{3}\\.\\d{2}")) {
            double price = Double.parseDouble(key);
            cpuToLookFor = new CPU(price);
        } else {
            cpuToLookFor = new CPU(key);
        }

        CPU findByName = cpusByName.search(cpuToLookFor, cpuNameComparator);
        CPU findByValue = cpusByName.search(cpuToLookFor, cpuPriceComparator);

        if (findByName != null) {
            return findByName;
        } else if (findByValue != null) {
            return findByValue;
        } else {
            return null;
        }


    }

    /**
     * Prompt the user to login
     * @param input to read user input
     * @param customers hashtable of customers
     * @param employees hashtable of employees
     * @postcondition enable the user to log in or register/sign in as guest
     */
    private static User logIn(Scanner input, HashTable<Customer> customers, HashTable<Employee> employees) {
        int choice;
        while (true) {
            System.out.println("\nLogin options: ");
            System.out.println("1. Login as a Customer");
            System.out.println("2. Create a new Customer account");
            System.out.println("3. Login as a Guest");
            System.out.println("4. Login as an Employee or Manager");
            System.out.print("\nPlease select an option: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = input.nextLine();
                    System.out.print("Enter password: ");
                    String password = input.nextLine();

                    Customer customer = customers.get(new Customer(null, null, username, password));
                    if (customer != null) {
                        System.out.println("Login successful!\n");
                        return customer;
                    } else {
                        System.out.println("Invalid username or password.");
                        continue;
                    }

                case 2:
                    System.out.print("Enter first name: ");
                    String firstName = input.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = input.nextLine();
                    System.out.print("Enter username: ");
                    String newUsername = input.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = input.nextLine();

                    Customer newCustomer = new Customer(firstName, lastName, newUsername, newPassword);
                    customers.add(newCustomer);
                    System.out.println("New customer account created successfully!\n");
                    return newCustomer;

                case 3:
                    System.out.println("Logged in as a Guest.\n");
                    return new Customer("Guest", "abc123");

                case 4:
                    System.out.print("Enter username: ");
                    String employeeUsername = input.nextLine();
                    System.out.print("Enter password: ");
                    String employeePassword = input.nextLine();

                    Employee employee = employees.get(new Employee(null, null, employeeUsername, employeePassword));
                    if (employee != null) {
                        System.out.println("Login successful!\n");
                        return employee;
                    } else {
                        System.out.println("Invalid username or password.");
                        continue;
                    }

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Read from CPUs.txt and Users.txt
     * @param customers hash table of customers
     * @param employees hash table of employees
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByPrice BST of cpus sorted by price
     * @param cpuNameComparator compares CPUs by name
     * @param cpuPriceComparator compares CPUs by price
     */
    private static void inputInfo(HashTable<Customer> customers, HashTable<Employee> employees,
                                  BST<CPU> cpusByName, BST<CPU> cpusByPrice, CpuNameComparator cpuNameComparator, CpuPriceComparator cpuPriceComparator) {
        String firstName, lastName, username, password, cpuName, brand;
        int cores, threads, stock;
        double clockSpeed, price;
        try {
            File cpuFile = new File("CPUs.txt");
            File userFile = new File("users.txt");
            Scanner cpuReader = new Scanner(cpuFile);
            Scanner userReader = new Scanner(userFile);
            while (cpuReader.hasNextLine()) {
                cpuName = cpuReader.next();
                brand = cpuReader.next();
                clockSpeed = cpuReader.nextDouble();
                cores = cpuReader.nextInt();
                threads = cpuReader.nextInt();
                price = cpuReader.nextDouble();
                stock = cpuReader.nextInt();
                CPU cpu = new CPU(cpuName, brand, clockSpeed, cores, threads, price, stock);
                cpusByName.insert(cpu, cpuNameComparator);
                cpusByPrice.insert(cpu, cpuPriceComparator);
            }

            while (userReader.hasNextLine()) {
                String userType = userReader.next();
                firstName = userReader.next();
                lastName = userReader.next();
                username = userReader.next();
                password = userReader.next();

                if (userType.equals("C")) {
                    Customer customer = new Customer(firstName, lastName, username, password);
                    customers.add(customer);
                } else if (userType.equals("E")) {
                    Employee employee = new Employee(firstName, lastName, username, password);
                    employees.add(employee);
                } else if (userType.equals("M")) {
                    Employee employee = new Employee(firstName, lastName, username, password, true);
                    employees.add(employee);
                }
            }

            cpuReader.close();
            userReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
