/**
 * CustomerInterface.java
 * @author DSA Team 1
 * Team 1 Final Project
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        final int SIZE = 100;
        Scanner input = new Scanner(System.in);
        HashTable<Customer> customers = new HashTable<>(SIZE);
        HashTable<Employee> employees = new HashTable<>(SIZE);
        CustomerUsernameComparator customerUsernameComparator = new CustomerUsernameComparator();
        PriorityComparator priorityComparator = new PriorityComparator();
        OrderIdComparator orderIdComparator = new OrderIdComparator();
        CpuNameComparator cpuNameComparator = new CpuNameComparator();
        CpuPriceComparator cpuPriceComparator = new CpuPriceComparator();
        BST<CPU> cpusByName = new BST<>();
        BST<CPU> cpusByBrand = new BST<>();
        System.out.println("Welcome to the CPU Store!");
        inputInfo(customers, employees, cpusByName, cpusByBrand);
        User user = logIn(input, customers, employees);
        System.out.println(user);
        if (user instanceof Customer) {
            customerInterface(cpusByName, cpusByBrand, user, input);
        } else if (user instanceof Employee) {
            employeeInterface(cpusByName, cpusByBrand, user, input);
        }
    }

    /**
     * Interface for Employee Users
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByBrand BST of cpus sorted by brand
     * @param user of current user
     * @param input to read user input
     */
    private static void employeeInterface(BST<CPU> cpusByName, BST<CPU> cpusByBrand, User user, Scanner input) {
    }

    /**
     * Interface for Customer Users
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByBrand BST of cpus sorted by brand
     * @param user of current user
     * @param input to read user input
     */
    private static void customerInterface(BST<CPU> cpusByName, BST<CPU> cpusByBrand, User user, Scanner input) {
    }

    /**
     * Prompt the user to login
     * @param input to read user input
     */
    private static User logIn(Scanner input, HashTable<Customer> customers, HashTable<Employee> employees) {
        System.out.println("Login options: ");
        System.out.println("1. Login as a Customer");
        System.out.println("2. Create a new Customer account");
        System.out.println("3. Login as a Guest");
        System.out.println("4. Login as an Employee or Manager");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter username: ");
                String username = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();

                Customer customer = customers.get(new Customer(null, null, username, password));
                if (customer != null) {
                    System.out.println("Login successful!");
                    return customer;
                } else {
                    System.out.println("Invalid username or password.");
                    return null;
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
                System.out.println("Account created successfully!");
                return newCustomer;

            case 3:
                System.out.println("Logged in as a Guest.");
                return null;

            case 4:
                System.out.println("Enter username: ");
                String employeeUsername = input.nextLine();
                System.out.print("Enter password: ");
                String employeePassword = input.nextLine();

                Employee employee = employees.get(new Employee(null, null, employeeUsername, employeePassword));
                if (employee != null) {
                    System.out.println("Login successful!");
                    return employee;
                } else {
                    System.out.println("Invalid username or password.");
                    return null;
                }

            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    /**
     * Read from CPUs.txt and Users.txt
     * @param customers hash table of customers
     * @param employees hash table of employees
     * @param cpusByName BST of cpus sorted by name
     * @param cpusByBrand BST of cpus sorted by brand
     */
    private static void inputInfo(HashTable<Customer> customers, HashTable<Employee> employees,
                                  BST<CPU> cpusByName, BST<CPU> cpusByBrand) {
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
                CPU toAdd = new CPU(cpuName, brand, clockSpeed, cores, threads, price, stock);
                // cpus.insert(toAdd, nameCMP);
            }
            // add another bst call
            // INSERT USERS
            while (userReader.hasNextLine()) {
                firstName = userReader.next();
                lastName = userReader.next();
                username = userReader.next();
                password = userReader.next();
                String userType = userReader.next();

                if (userType.equals("customer")) {
                    Customer customer = new Customer(firstName, lastName, username, password);
                    customers.add(customer);
                } else if (userType.equals("employee")) {
                    boolean isManager = userReader.nextBoolean();
                    Employee employee = new Employee(firstName, lastName, username, password);
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