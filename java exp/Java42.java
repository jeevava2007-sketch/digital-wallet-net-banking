import java.util.Scanner;

// Parent class
class Bank {

    void bankDetails() {
        System.out.println("\nDIGITAL WALLET BANK");
        System.out.println("Branch: Chennai Main Branch");
        System.out.println("IFSC Code: DWBK0001234");
        System.out.println("Customer Care: 1800-123-4567");
    }
}

// Child class 1
class SavingsAccount extends Bank {

    void accountType() {
        System.out.println("Account Type: Savings");
    }
}

// Child class 2
class Customer extends Bank {

    private String name;
    private int accountNumber;
    private int balance;

    // Constructor
    public Customer(String name, int accountNumber, int balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Display customer details
    void display() {
        System.out.println("\n------ CUSTOMER DETAILS ------");
        System.out.println("Customer Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

// Main class
public class Java42 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        System.out.print("Enter Balance: ");
        int balance = sc.nextInt();

        // Objects of child classes
        SavingsAccount account = new SavingsAccount();
        Customer customer = new Customer(name, accountNumber, balance);

        // Calling methods
        account.bankDetails();
        account.accountType();

        customer.display();

        sc.close();
    }
}