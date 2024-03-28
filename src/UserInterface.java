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
    static boolean exit;

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
        exit = false;
        orders = new Heap<>(new ArrayList(), priorityComparator);
        System.out.println("Welcome to the CPU Store!");
        if (customers.getNumElements() != 0 && employees.getNumElements() != 0) {
            customers.clear();
            employees.clear();
        }
        inputInfo();
        user = logIn();
        if (user instanceof Customer) {
            customerInterface();
        } else if (user instanceof Employee && ((Employee)user).getIsManager()) {
            managerInterface();
        } else if (user instanceof Employee ) {
            employeeInterface();
        }
        //write to file
        writeToCPUsFile();
        writeToUserFile();
    }

    private static void managerInterface() {
        System.out.println("Welcome to Microcenter's CPU store!");
        boolean finished1 = false;
        boolean finished2;
        int choice;
        int searchOption;
        String searchKey;
        while(!finished1) {
            System.out.println("Manager Options: ");
            System.out.println("1: Search for an order");
            System.out.println("2: View order with highest priority");
            System.out.println("3: View all orders sorted by priority");
            System.out.println("4: Ship an order");
            System.out.println("5: Update products");
            System.out.println("6: Remove products");
            System.out.println("7: Quit and write to files");
            System.out.print("Please enter your option:  ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    searchForOrder();
                    break;
                case 2:
                    printHighestPriority();
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
                    updateProducts();
                    break;
                case 6:
                    System.out.print("Enter the model name of cpu to remove: ");
                    String cpuNameToRemove = input.next();
                    CPU cpuToRemove = searchForProduct(cpuNameToRemove);
                    break;
                case 7:
                    finished1 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
                    break;
            }
        }
    }

    private static void updateProducts() {
        System.out.print("1. Add a new cpu\n2. update an existing cpu\nEnter option here: ");
        int option = Integer.parseInt(input.next());
        System.out.print("\n");

        switch (option) {
            case 1:
                System.out.print("Enter the model name: ");
                String modelName = input.next();
                System.out.print("Enter the brand: ");
                String brandName = input.next();
                System.out.print("Enter the clockspeed: ");
                double clockSpeed = Double.parseDouble(input.next());
                System.out.print("Enter the number of cores: ");
                int numCores = Integer.parseInt(input.next());
                System.out.print("Enter the number of threads: ");
                int numThreads = Integer.parseInt(input.next());
                System.out.print("Enter the price: ");
                double price = Double.parseDouble(input.next());
                System.out.print("Enter the stock: ");
                int stock = Integer.parseInt(input.next());

                CPU cpuToAdd = new CPU(modelName, brandName, clockSpeed, numCores, numThreads, price, stock);
                addProduct(cpuToAdd);
                break;
            case 2:
                System.out.print("Enter the model name of the product you wish to modify: ");
                String nameOfCpuToModify = input.next();
                CPU cpuToModify = searchForProduct(nameOfCpuToModify);
                if (cpuToModify != null) {
                    System.out.print("Which attribute do you wish to change?\n1. Price\n2. Stock\nEnter choice here: ");
                    int attributeChoice = Integer.parseInt(input.next());

                    switch (attributeChoice) {
                        case 1:

                            System.out.print("Here is the current price: " + cpuToModify.getPrice() + "\nEnter new price: ");
                            double newPrice = Double.parseDouble(input.next());
                            updateProductPrice(cpuToModify, newPrice);
                            break;
                        case 2:
                        System.out.print("Here is the current stock: " + cpuToModify.getStockNum() + "\nEnter new stock number: ");
                        int newStockNum = Integer.parseInt(input.next());
                        updateProductStock(cpuToModify, newStockNum);
                            break;
                        default:
                            break;
                    }
                } 
                break;
            default:
                System.out.print("Invalide choice. Please try again.\n");
                break;
        }
    }

    /**
     * Runs interface for Employee Users
     */
    private static void employeeInterface() {
        System.out.println("Welcome to Microcenter's CPU store!");
        boolean finished1 = false;
        int choice;
        int orderId;

        while (!finished1) {
            System.out.println("Employee Options: ");
            System.out.println("1: Search for an order");
            System.out.println("2: View order with highest priority");
            System.out.println("3: View all orders sorted by priority");
            System.out.println("4: Ship an order");
            System.out.println("5: Quit and write to file");
            System.out.print("Please enter your option:  ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    searchForOrder();
                    break;
                case 2:
                try {
                    printHighestPriority();   
                } catch (Exception e) {
                    System.out.print("\nThere are no orders!\n\n");
                }
                    break;
                case 3:
                        viewSortedOrders();
                    break;
                case 4:
                    System.out.println("\nUnshipped Orders:");
                    displayUnshippedOrders();
                    System.out.print("Please enter the ID number of the order you are shipping: ");
                    orderId = input.nextInt();
                    if (!shipOrder(orderId)) {
                        System.out.println("Invalid order ID. Please try again.");
                    }
                    System.out.println("Order has been successfully shipped.\n");
                    break;
                case 5:
                    finished1 = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
                    break;
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
            System.out.println("5. Quit and write to files");
            System.out.print("\nPlease enter your option: ");
            choice = input.nextInt();
            input.nextLine();
            System.out.print("\n");
            switch (choice) {
                case 1:
                System.out.print("Enter the model name or price of the cpu you are looking for: ");
                String keyString = input.next();
                if (searchForProduct(keyString) != null) {
                    System.out.print("Product was found.\n\n");
                } else {
                    System.out.print("Sorry, we don't carry this product.\n\n");
                }
                break;
                case 2:
                        System.out.print("List by: 1. Name 2. Price\nEnter choice: ");
                        int listByChoice = Integer.parseInt(input.next());
                        switch (listByChoice) {
                            case 1:
                                    System.out.print(cpusByName.inOrderString());       
                                break;
                            case 2:
                                    System.out.print(cpusByPrice.inOrderString());
                                break;
                            default:
                                    System.out.print("Invalid choice. Please try again.");
                                break;
                        }
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
                            System.out.println(((Customer)user).printShippedOrders());
                        } else if (searchOption == 2) {
                            System.out.println(((Customer)user).printUnshippedOrders());
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                        break;
                case 5:
                        finished1 = true;
                        break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    /***EMPLOYEE METHODS***/

    /**
     * Ships an order (Remove from Heap. Insert Order to shipped
     * Linked List for the Customer + Remove from Unshipped List)
     * @param orderId the user's input of the order's id number
     * @return a boolean value that determines if the order id was a valid one
     */
    public static boolean shipOrder(int orderId) {
        for (int i = 1; i <= orders.getHeapSize(); i++) {
            Order order = orders.getElement(i);
            if (order.getOrderId() == orderId) {
                Customer customer = order.getCustomer();

                // Remove the order from the orders heap
                orders.remove(i);

                // Add the order to the customer's shipped orders list
                customer.addShippedOrder(order);

                // Remove the order from the customer's unshipped orders list
                LinkedList<Order> unshippedOrders = customer.getUnshippedOrders();
                unshippedOrders.positionIterator();
                while (!unshippedOrders.offEnd()) {
                    Order currentOrder = unshippedOrders.getIterator();
                    if (currentOrder.equals(order)) {
                        unshippedOrders.removeIterator();
                        break;
                    }
                    unshippedOrders.advanceIterator();
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Displays the list of unshipped orders sorted by shipping speed priority.
     * The unshipped orders are retrieved from the orders heap, stored in an ArrayList,
     * and then sorted based on their shipping speed priority (rush, overnight, standard).
     * The sorted orders are then displayed with their details.
     */
    private static void displayUnshippedOrders() {
        // Create a new ArrayList to store the unshipped orders
        ArrayList<Order> unshippedOrders = new ArrayList<>();

        // Add all unshipped orders to the ArrayList
        for (int i = 1; i <= orders.getHeapSize(); i++) {
            unshippedOrders.add(orders.getElement(i));
        }

        // Sort the unshipped orders based on shipping speed priority
        unshippedOrders.sort((order1, order2) -> {
            int priority1 = getShippingSpeedPriority(order1.getShippedSpeed());
            int priority2 = getShippingSpeedPriority(order2.getShippedSpeed());
            return Integer.compare(priority1, priority2);
        });

        // Display the sorted unshipped orders
        for (Order order : unshippedOrders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer: " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
            System.out.println("Order Date: " + order.getDate());
            System.out.println("Shipping Speed: " + order.getShippedSpeed());
            System.out.println("Order Contents: \n" + order.getOrderContents());
        }
    }

    /**
     * Determines the priority of a given shipping speed.
     * Rush orders have the highest priority (1), followed by overnight orders (2),
     * and then standard orders (3). Any other shipping speed is assigned the lowest priority (4).
     *
     * @param shippingSpeed the shipping speed to determine the priority for
     * @return the priority value of the shipping speed (1 for rush, 2 for overnight, 3 for standard, 4 for others)
     */
    private static int getShippingSpeedPriority(String shippingSpeed) {
        switch (shippingSpeed) {
            case "rush":
                return 1;
            case "overnight":
                return 2;
            case "standard":
                return 3;
            default:
                return 4;
        }
    }

    private static void searchForOrder() {
        System.out.print("Search options:\n\n1. Search by order id\n2. Search by customer first and last name\nEnter choice: ");
        int searchOption = Integer.parseInt(input.next());

        switch (searchOption) {
            case 1:
                System.out.print("Enter order ID: "); 
                int orderID = Integer.parseInt(input.next());
                for (int i = 0; i < SIZE; i++) {
                    if (customers.countBucket(i) > 0) {
                        LinkedList<Customer> customersInBucket = customers.getBucket(i);
                        customersInBucket.positionIterator();
                        for (int j = 0; j < customersInBucket.getLength(); j++) {
                            Customer customerToCheck = customersInBucket.getIterator();
                            LinkedList<Order> unShippedOrdersToCheck = customerToCheck.getUnshippedOrders();
                            LinkedList<Order> shippedOrdersToCheck = customerToCheck.getShippedOrders();
                            unShippedOrdersToCheck.positionIterator();
                            shippedOrdersToCheck.positionIterator();
                            while (!unShippedOrdersToCheck.offEnd()) {
                                Order unShippedOrder = unShippedOrdersToCheck.getIterator();
                                if (unShippedOrder.getOrderId() == orderID) {
                                    System.out.print("Order found! Order information:\n\n");
                                    System.out.print(unShippedOrder.toString());
                                    return;
                                }
                                unShippedOrdersToCheck.advanceIterator();
                            }
                            while (!shippedOrdersToCheck.offEnd()) {
                                Order shippedOrder = shippedOrdersToCheck.getIterator();
                                if (shippedOrder.getOrderId() == orderID) {
                                    System.out.print("Order found! Order information:\n\n");
                                    System.out.print(shippedOrder.toString());
                                    return;
                                }
                                shippedOrdersToCheck.advanceIterator();
                            }
                            customersInBucket.advanceIterator();
                        }
                    }
                }
                    
                break;
            case 2:
                System.out.print("Enter the customer's first name: ");
                String firstName = input.next();
                System.out.print("Enter the customer's last name: ");
                String lastName = input.next();
                Customer customerToSearchFor = new Customer(firstName, lastName, null, null);
                
                for (int i = 0; i < SIZE; i++) {
                    if (customers.countBucket(i) > 0) {
                        LinkedList<Customer> customersInBucket = customers.getBucket(i);
                        customersInBucket.positionIterator();
                        for (int j = 0; j < customersInBucket.getLength(); j++) {
                            Customer customerToCheck = customersInBucket.getIterator();
                            if (customerToCheck.getFirstName().compareTo(firstName) == 0 && customerToCheck.getLastName().compareTo(lastName) == 0) {
                                System.out.print("Unshipped orders:\n" + customerToCheck.getUnshippedOrders().toString() + "Shipped orders:\n" + customerToCheck.getShippedOrders());
                            }
                            customersInBucket.advanceIterator();
                        }
                    }
                }
                break;
            default:
                System.out.print("Invalid input. Please try again.");
                break;
        }

    }
    private static void printHighestPriority(){
        Order tempOrder = new Order();
        tempOrder = orders.getElement(1);
        System.out.print(tempOrder.toString());
    }
    /**
     * Prints out a list of all orders sorted by Priority
     */
    public static void viewSortedOrders() {
        Heap<Order> copyOfOrders = orders;
        Order tempOrder;
        if (copyOfOrders.getHeapSize() == 0) {
            System.out.print("\nThere are no orders!\n\n");
        } else {
            while(copyOfOrders.getHeapSize() != 0) {
                tempOrder = copyOfOrders.getElement(1);
                System.out.print(tempOrder.toString());
                copyOfOrders.remove(1);
            }
        }
    }

    /**
     * Updates the CPU BSTs to include a new product
     * @param newCPU the CPU to be added
     * @return if the product (CPU) was successfully added
     */
    public static void addProduct(CPU newCPU) {
        if (!((Employee)user).getIsManager()) {
            System.out.println("Invalid request: Restricted to manager");
            return;
        }
        cpusByName.insert(newCPU, cpuNameComparator);
        cpusByPrice.insert(newCPU, cpuPriceComparator);

        addCpuToFile(newCPU);
    }

    private static void addCpuToFile(CPU newCPU) {
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
    public static boolean updateProductStock(CPU updateCPU, int updateStock) {
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
    public static boolean updateProductPrice(CPU updateCPU, double updatePrice) {
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
           String shippingOption;
            while (true) {
                System.out.print("Please select a shipping option (standard, rush, overnight): ");
                shippingOption = input.next();
                if (shippingOption.equals("standard") || shippingOption.equals("rush") || shippingOption.equals("overnight")) {
                    break;
                }
                System.out.println("Not a valid shipping option!");
            }
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
    private static CPU searchForProduct(String key) {
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
                    writeToUserFile();
                	writeToCPUsFile();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
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

    private static void writeToCPUsFile() {
    	try {
    		FileWriter writer = new FileWriter(new File("cpus.txt"), false);

    		String cpusString = cpusByName.preOrderString();
    		String adjustedString = cpusString.replaceAll("(?m)^[ \t]*\r?\n", "");
    		String lines[] = adjustedString.split("\\r?\\n");

    		int count = 0;
    		for (int j = 0; j < cpusByName.getSize(); j++) {
    			String name = lines[0 + count].substring(lines[0 + count].lastIndexOf(" ") + 1);
    			System.out.println(lines[0 + count]);
    			String brand = lines[1 + count].substring(lines[1 + count].lastIndexOf(" ") + 1);
    			System.out.println(lines[1 + count]);
    			String speed = lines[2 + count].substring(lines[2 + count].lastIndexOf(" ") + 1);
    			System.out.println(lines[2 + count]);  			
    			String cores = lines[3 + count].substring(lines[3 + count].lastIndexOf(" ") + 1);
    			System.out.println(lines[3 + count]);
    			String threads = lines[4 + count].substring(lines[4 + count].lastIndexOf(" ") + 1);
    			System.out.println(lines[4 + count]);
    			String price = lines[5 + count].substring(lines[5 + count].lastIndexOf("$") + 1);
    			System.out.println(lines[5 + count]);
    			String stock = lines[6 + count].substring(lines[5 + count].lastIndexOf(" ") + 1);
    			System.out.println(lines[6 + count]);
    			count += 7;
    			
    			writer.write("\n" + name);
    			writer.write("\n" + brand);
    			writer.write("\n" + speed);
    			writer.write("\n" + cores);
    			writer.write("\n" + threads);
    			writer.write("\n" + price);
    			writer.write("\n" + stock);
   
    		}
    		writer.flush();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private static void writeToUserFile() {
    	try {
    		FileWriter writer = new FileWriter(new File("users.txt"), false);
    		for (int i = 0; i < SIZE; i++) {
    			if (customers.countBucket(i) > 0) {
    				LinkedList<Customer> customersInBucket = customers.getBucket(i);
    				customersInBucket.positionIterator();
    				for (int j = 0; j < customersInBucket.getLength(); j++) {
    					writer.write("\nC");
    					writer.write(customersInBucket.getIterator().toStringForFile());
    					customersInBucket.advanceIterator();
    				}
    			}
    		}

    		for (int i = 0; i < SIZE; i++) {
    			if (employees.countBucket(i) > 0) {
    				LinkedList<Employee> employeesInBucket = employees.getBucket(i);
    				employeesInBucket.positionIterator();
    				for (int j = 0; j < employeesInBucket.getLength(); j++) {
    	    			if (employeesInBucket.getIterator().getIsManager() == true) {
    	    				writer.write("\nM");
    	    			}
    	    			else {
    	    				writer.write("\nE");
    	    			}
    					writer.write(employeesInBucket.getIterator().toStringForFile());
    					employeesInBucket.advanceIterator();
    				}
    			}
    		}
    		writer.flush();
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Read from CPUs.txt and Users.txt
     * @postcondition all information from cpus.txt and users.txt is inputted
     */
    private static void inputInfo() {
        String firstName, lastName, username, password, address, city, state, zip, cpuName, brand;
        int cores, threads, stock, numOrders;
        double clockSpeed, price;
        LinkedList<Order> shippedOrders, unshippedOrders;
        try {
            File cpuFile = new File("cpus.txt");
            File userFile = new File("users.txt");
            Scanner cpuReader = new Scanner(cpuFile);
            Scanner userReader = new Scanner(userFile);
            cpuReader.nextLine();
            while (cpuReader.hasNextLine()) {
                cpuName = cpuReader.nextLine();
                brand = cpuReader.nextLine();
                clockSpeed = cpuReader.nextDouble();
                cpuReader.nextLine();
                cores = cpuReader.nextInt();
                cpuReader.nextLine();
                threads = cpuReader.nextInt();
                cpuReader.nextLine();
                price = cpuReader.nextDouble();
                cpuReader.nextLine();
                stock = cpuReader.nextInt();
                if (cpuReader.hasNextLine()) {
                	cpuReader.nextLine();
                }
                CPU cpu = new CPU(cpuName, brand, clockSpeed, cores, threads, price, stock);
                cpusByName.insert(cpu, cpuNameComparator);
                cpusByPrice.insert(cpu, cpuPriceComparator);
            }
            
            userReader.nextLine(); //clear first empty line
            while (userReader.hasNextLine()) {
                String userType = userReader.nextLine();
                firstName = userReader.nextLine();
                lastName = userReader.nextLine();
                username = userReader.nextLine();
                password = userReader.nextLine();

                if (userType.equals("C")) {
                	address = userReader.nextLine();
                	city = userReader.nextLine();
                	state = userReader.nextLine();
                	zip = userReader.nextLine();
                	shippedOrders = new LinkedList<Order>();
                	unshippedOrders = new LinkedList<Order>();
                    Customer customer = new Customer(firstName, lastName, username, password,
                    		address, city, state, zip, shippedOrders, unshippedOrders);
                    //customers.add(customer);
                    numOrders = Integer.parseInt(userReader.nextLine());
                	for (int i = 0; i < numOrders; i++) {
                		String shippingStatus = userReader.nextLine();
                		int foundOrderID = Integer.parseInt(userReader.nextLine());
                		int numOrderContents = Integer.parseInt(userReader.nextLine());
                		LinkedList<CPU> orderContents = new LinkedList<>();
                		for (int j = 0; j < numOrderContents; j++) {
                			String tempCPUName = userReader.nextLine();
                			CPU tempCPU = new CPU(tempCPUName, "", 0, 0, 0, 0, 0);
                			CPU foundCPU = cpusByName.search(tempCPU, cpuNameComparator);
                			if (foundCPU != null) {
                				orderContents.addLast(foundCPU);
                			}
                		}
                		String shippingSpeed = userReader.nextLine();
                		Order newOrder = new Order (foundOrderID, customer, orderContents, shippingSpeed);
                		if (shippingStatus.equalsIgnoreCase("shipped")) {
                			customer.addShippedOrder(newOrder);
                            orderID++;
                		}
                		else {
                			customer.addOrder(newOrder);
                			orders.insert(newOrder);
                            orderID++;
                		}
                	}
                	customers.add(customer);
                	//System.out.print(customers.toString());
                	
                } else {
                	boolean isManager = Boolean.parseBoolean(userReader.nextLine());
                    Employee employee = new Employee(firstName, lastName, username, password, isManager);
                    employees.add(employee);
                    //System.out.print(customers.toString());
                }
            }

            cpuReader.close();
            userReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
