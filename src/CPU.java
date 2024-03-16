/**
 * CPU.java
 * @author Avin Rai
 * Team 1 Final Project
 */
public class CPU {
    String name, brand; // respective primary and secondary key
    int cores, threads, stock;
    double clockSpeed, price;

    /****CONSTRUCTORS****/

    /***
     * Default constructor for CPU
     * @postcondition a new CPU object with default fields
     */
    public CPU(){
        this.name = "";
        this.brand = "";
        this.clockSpeed = 0;
        this.cores = 0;
        this.threads = 0;
        this.price = 0;
        this.stock = 0;
    }

    /***
     * Parameterized constructor for CPU
     * @param name of the CPU (ex. i7-12700K), primary key
     * @param brand of the CPU (ex. Intel), secondary key
     * @param cores of the CPU (ex. 12)
     * @param threads of the CPU (ex. 20)
     * @param clockSpeed of the CPU in Ghz (ex. 5.00)
     * @param price in dollars (ex. 450)
     * @postcondition a new CPU object with assigned values
     */
    public CPU(String name, String brand, double clockSpeed, int cores, int threads, double price, int stock) {
        this.name = name;
        this.brand = brand;
        this.clockSpeed = clockSpeed;
        this.cores = cores;
        this.threads = threads;
        this.price = price;
        this.stock = stock;
    }

    /****ADDITIONAL OPERATIONS****/

    /**
     * Returns the data in the CPU as a String
     * separated by a new line character
     * and another new line character at the end
     * @return a String of CPU values
     */
    @Override
    public String toString() {
        String result = "Name: " + name +
                "\nBrand: " + brand +
                "\nClock Speed: " + clockSpeed +
                "\nCores: " + cores +
                "\nThreads: " + threads +
                "\nPrice: $" + String.format("%.2f", price) +
                "\nStock: " + stock;
        return result + "\n";
    }

    /**
     * Determines if two CPUs are equal
     * @returns if two CPU objects are equal
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        } else if (!(obj instanceof CPU)) {
            return false;
        } else {
            return this.name.equals(((CPU) obj).name);
        }
    }

    /**
     * Returns the hash code of the CPU
     * @return hash code
     */
    @Override
    public int hashCode(){
        String key = name + brand;
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum;
    }
}
