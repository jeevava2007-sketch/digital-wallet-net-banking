import java.util.Scanner;

class Wallet {

    // Private data members
    private String name;
    private int accountNumber;
    private int balance;

    // Constructor
    public Wallet(String name, int accountNumber, int balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Public method
    public void display() {
        System.out.println("\n----- Wallet Details -----");
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    // Static method
    public static void bankName() {
        System.out.println("Bank Name: Digital Wallet Bank");
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Calling static method
        Wallet.bankName();

        // Getting input from user
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        System.out.print("Enter Balance: ");
        int balance = sc.nextInt();

        // Creating object
        Wallet user = new Wallet(name, accountNumber, balance);

        // Displaying details
        user.display();

        sc.close();
    }
}