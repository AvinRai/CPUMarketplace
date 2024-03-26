/**
 * CustomerInterface.java
 * @author DSA Team 1
 * Team 1 Final Project
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    static int orderCount; //calculated when read
    static final int SIZE = 100;
    static int orderID = 0;
    static Heap<Order> orders;
    static boolean exit = false;

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
        orders = new Heap<>(new ArrayList(), priorityComparator);
        while (!exit) {
            System.out.println("Welcome to the CPU Store!");
            inputInfo();
            user = logIn();
            if (user instanceof Customer) {
                customerInterface();
            } else if (user instanceof Employee) {
                employeeInterface();
            } else if (user instanceof Employee && ((Employee)user).getIsManager()) {
                managerInterface();
            }
        }
    }

    private static void managerInterface() {
        System.out.println("Welcome to Microcenter's CPU store!");
        boolean finished1 = false;
        boolean finished2;
        int choice;
        int searchOption;
        String searchKey;
        while(!finished1) {
            System.out.println("Customer Options: ");
            System.out.println("1: Search for an order");
            System.out.println("2: View order with highest priority");
            System.out.println("3: View all orders sorted by priority");
            System.out.println("4: Ship and order");
            System.out.println("5: Log out ");
            System.out.println("5: Log out ");
            System.out.println("5: Log out ");
            System.out.print("Please enter your option:  ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                searchForOrder();
                break;
                case 2:
                    System.out.print(cpusByName.inOrderString());
                break;

                case 3:

                break;

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
                break;
                case 5:
                    //call read to file and quit method
                    System.out.println("Quiting program. Thanks for choosing Microcenter's CPU Store!");
                    finished1 = true;
                break;
                case 6:
                // System.out.print()
                break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    /**
     * Runs interface for Employee Users
     */
    private static void employeeInterface() {
        System.out.println("Welcome to Microcenter's CPU store!");
        boolean finished1 = false;
        boolean finished2;
        int choice;
        int searchOption;
        String searchKey;
        while(!finished1) {
            System.out.println("Customer Options: ");
            System.out.println("1: Search for an order");
            System.out.println("2: View order with highest priority");
            System.out.println("3: View all orders sorted by priority");
            System.out.println("4: Ship and order");
            System.out.println("5: Log out ");
            System.out.print("Please enter your option:  ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                searchForOrder();
                break;
                case 2:
                    System.out.print(cpusByName.inOrderString());
                break;

                case 3:

                break;

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
     * Runs interface for Customer Users
     */
    private static void customerInterface() {
        System.out.println("Welcome to Microcenter's CPU store!\n");
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
            System.out.println("5: Log out ");
            System.out.print("\nPlease enter your option: ");
            choice = input.nextInt();
            input.nextLine();
            System.out.print("\n");
            switch (choice) {
                case 1:
                if (searchForProduct() != null) {
                    System.out.print("Product was found.\n\n");
                } else {
                    System.out.print("Sorry, we don't carry this product.\n\n");
                }
                break;
                case 2:
                    System.out.print(cpusByName.inOrderString());
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                        System.out.println("View Purchases Options:\n1: View Shipped orders\n2: View Unshipped orders");
                        System.out.print("Please enter the number of your option: ");
                        searchOption = input.nextInt();
                        input.nextLine();
                        System.out.print("\n\n");
                        if (searchOption == 1) {
                            viewShippedOrders();
                        } else if (searchOption == 2) {
                            viewUnshippedOrders();
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                        break;
                    //call method to view purchases
                case 5:
                    //call read to file and quit method
                    System.out.println("Quiting program. Thanks for choosing Microcenter's CPU Store!");
                    finished1 = true;
                    break;


                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    /***EMPLOYEE METHODS***/

    private static void searchForOrder() {
        System.out.print("Search options:\n\n1. Search by order id\n2. Search by customer first and last name\nEnter choice: ");
        int searchOption = Integer.parseInt(input.next());

        switch (searchOption) {
            case 1:
                //Search for order with order ID
                // System.out.print("Enter order ID: "); 
                // int orderID = Integer.parseInt(input.next());
                // orders.getElement(orderID);
                break;
            case 2:
                System.out.print("Enter the customer's first name: ");
                String firstName = input.next();
                System.out.print("Enter the customer's last name: ");
                String lastName = input.next();
                Customer customerToSearchFor = new Customer(firstName, lastName, null, null);
                
                for (int i = 1; i < orders.getHeapSize() + 1; i++) {
                    Customer temp = orders.getElement(i).getCustomer();

                    if (temp.getFirstName().compareTo(firstName) == 0 && temp.getLastName().compareTo(lastName) == 0) {
                        System.out.print(temp.printUnshippedOrders());
                        System.out.print(temp.printShippedOrders());
                    } 
                }
                break;
            default:
                System.out.print("Invalid input. Please try again.");
                break;
        }

    }

    /**
     * Updates the CPU BSTs to include a new product
     * @param newCPU the CPU to be added
     * @return if the product (CPU) was successfully added
     */
    public boolean addProduct(CPU newCPU) {
        if (!((Employee)user).getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        cpusByName.insert(newCPU, cpuNameComparator);
        cpusByPrice.insert(newCPU, cpuPriceComparator);

        addCpuToFile(newCPU);

        return true;
    }

    private void addCpuToFile(CPU newCPU) {
        try {
            FileWriter writer = new FileWriter(new File("Users.txt"), true);
            String lineToAdd = newCPU.getName() + " " + newCPU.getBrand() + " " + newCPU.getClockSpeed() + " " + newCPU.getCores() + " " + newCPU.getThreads() + " " + newCPU.getPrice() + " " + newCPU.getStockNum();
            writer.write("\n" + lineToAdd);
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }

    /**
     * Updates an existing product's stock in the CPU BSTs
     * @param updateCPU the CPU to be updated
     * @return if the product (CPU) was successfully updated
     */
    public boolean updateProductStock(CPU updateCPU, int updateStock) {
        if (!((Employee)user).getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        if (cpusByName.search(updateCPU, cpuNameComparator) == null) {
            System.out.println("Invalid request: The CPU Store does not carry this product");
            return false;
        }
        CPU tempCPU = cpusByName.search(updateCPU, cpuNameComparator);

        cpusByName.remove(updateCPU, cpuNameComparator);
        cpusByPrice.remove(updateCPU, cpuPriceComparator);

        tempCPU.updateStock(updateStock); //need to add updateStock() method to CPU class, written below

        cpusByName.insert(tempCPU, cpuNameComparator);
        cpusByPrice.insert(tempCPU, cpuPriceComparator);
        return true;
    }

    /**
     * Updates an existing product's price in the CPU BSTs
     * @param updateCPU the CPU to be updated
     * @return if the product (CPU) was successfully updated
     */
    public boolean updateProductPrice(CPU updateCPU, double updatePrice) {
        if (!((Employee)user).getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        if (cpusByName.search(updateCPU, cpuNameComparator) == null) {
            System.out.println("Invalid request: The CPU Store does not carry this product");
            return false;
        }
        CPU tempCPU = cpusByName.search(updateCPU, cpuNameComparator);

        cpusByName.remove(updateCPU, cpuNameComparator);
        cpusByPrice.remove(updateCPU, cpuPriceComparator);

        tempCPU.updatePrice(updatePrice); //need to add setters to CPU class

        cpusByName.insert(tempCPU, cpuNameComparator);
        cpusByPrice.insert(tempCPU, cpuPriceComparator);
        return true;
    }

    /**
     * Updates the CPU BSTs to remove a product
     * @param removeCPU the CPU to be removed
     * @return if the product (CPU) was successfully removed
     */
    public boolean removeProduct(CPU removeCPU) {
        if (!((Employee) user).getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return false;
        }
        if (cpusByName.search(removeCPU, cpuNameComparator) == null) {
            System.out.println("Invalid request: The CPU Store does not carry this product");
            return false;
        }
        cpusByName.remove(removeCPU, cpuNameComparator);
        cpusByPrice.remove(removeCPU, cpuPriceComparator);
        return true;
    }

    /***CUSTOMER METHODS***/

    /**
     * Creates a new order
     * @param shippedSpeed the type of shippingSpeed
     *
     */
    public static void placeOrder() {
        //orderCount++;
        // Order newOrder = new Order(orderCount, this, orderContents, shippedSpeed);
        //add the order to heap of orders
       // System.out.print("")
       System.out.print(cpusByName.inOrderString());
       System.out.print("Please enter the name of the product you wish to purchase: ");
       String itemName = input.next();
       CPU cpuForSearch = new CPU(itemName);
       CPU returnedCpu = cpusByName.search(cpuForSearch, cpuNameComparator);
       if (returnedCpu == null) {
            System.out.print("Item not found. Please try again.");
       } else {
            System.out.print("Item found. What quantity do you wish to purchase? Only " + returnedCpu.getStockNum() + " remain.\nEnter the quantity: ");
            int quantity = Integer.parseInt(input.next()); 
            LinkedList<CPU> orderContents = new LinkedList<>();
            for (int i = 0; i < quantity; i++) {
                orderContents.addLast(returnedCpu);
            }
            System.out.print("Please select a shipping option (standard, rush, overnight):");
            String shippingOption = input.next();
            Order order = new Order(orderID, (Customer) user, orderContents, shippingOption);
            ((Customer) user).addOrder(order);
            orders.insert(order);
            returnedCpu.updateStock(-1 * quantity);
            orderID++;
            System.out.print("Order has been successfully placed.\n\n");
       }
    }

    /**
     * Searches for a product depending on the key passed
     * @return the cpu searched for if it exists
     */
    private static CPU searchForProduct() {
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
     * Views shipped orders
     * @postcondition prints a list of user's current shipped orders
     */
    private static void viewShippedOrders() {
        try {
            LinkedList<Order> shipped = ((Customer) user).getShippedOrders();
            shipped.positionIterator();
            for (int i = 0; i < shipped.getLength(); i++) {
                System.out.println(shipped.getIterator());
                shipped.advanceIterator();
            }
        } catch (NullPointerException e) {
            System.out.println("No shipped orders!");
        }
    }

    /**
     * Views unshipped orders
     * @postcondition prints a list of user's current unshipped orders
     */
    private static void viewUnshippedOrders() {
        try {
            LinkedList<Order> shipped = ((Customer) user).getUnshippedOrders();
            shipped.positionIterator();
            for (int i = 0; i < shipped.getLength(); i++) {
                System.out.println(shipped.getIterator());
                shipped.advanceIterator();
            }
        } catch (NullPointerException e) {
            System.out.println("No unshipped orders!");
        }
    }



    /***ADDITIONAL METHODS***/

    /**
     * Prompt the user to login
     * @postcondition enable the user to log in or register/sign in as guest
     */
    private static User logIn() {
        int choice;
        while (!exit) {
            System.out.println("\nLogin options: ");
            System.out.println("1. Login as a Customer");
            System.out.println("2. Create a new Customer account");
            System.out.println("3. Login as a Guest");
            System.out.println("4. Login as an Employee or Manager");
            System.out.println("5. Exit program");
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
                    addCustomerToFile(newCustomer);
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
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        return null;
    }

    private static void addCustomerToFile(Customer newCustomer) {
        try {
            FileWriter writer = new FileWriter(new File("Users.txt"), true);
            String lineToAdd = "C " + newCustomer.getFirstName() + " " 
            + newCustomer.getLastName() 
            + " " + newCustomer.getUsername() 
            + " " + newCustomer.getPassword();
            writer.write("\n" + lineToAdd);
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }

    /**
     * Read from CPUs.txt and Users.txt
     * @postcondition all information from CPUs.txt and Users.txt is inputted
     */
    private static void inputInfo() {
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