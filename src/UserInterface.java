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
        BST<CPU> cpusByPrice = new BST<>();
        System.out.println("Welcome to the CPU Store!");
        inputInfo(customers, employees, cpusByName, cpusByPrice);
        User user = logIn(input);
        System.out.println(user);
        if (user instanceof Customer) {
            customerInterface(cpusByName, cpusByPrice, user, input);
        } else if (user instanceof Employee) {
            employeeInterface(cpusByName, cpusByPrice, user, input);
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
    private static User logIn(Scanner input) {

        return new Customer("abc", "abc");
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
                cores = cpuReader.nextInt();
                threads = cpuReader.nextInt();
                stock = cpuReader.nextInt();
                clockSpeed = cpuReader.nextDouble();
                price = cpuReader.nextDouble();
                CPU toAdd = new CPU(cpuName, brand, cores, threads, stock, clockSpeed, price);
                cpus.insert(toAdd, nameCMP);
            }
            // add another bst call
            // INSERT USERS
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Searches for a product depending on the key passed
     * @param <T>
     * @param cpusByName the cpus available
     * @param key the key provided. either the model name or the price
     * @return the cpu searched for if it exists
     */
    private static CPU searchForproduct(BST<CPU> cpusByName, CPU key, CpuNameComparator cpuNameComparator, CpuPriceComparator cpuPriceComparator) {
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

}
