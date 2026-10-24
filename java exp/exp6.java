import java.util.Scanner;

// Parent class
class Bank {

    public void displayDetails() {
        System.out.println("Welcome to Digital Wallet Bank");
    }
}

// Child class
class DigitalWallet extends Bank {

    String name;
    int walletId;
    double balance;
    String mobile;
    String email;
    String accountType;

    // Constructor
    DigitalWallet(String name, int walletId, double balance,
                  String mobile, String email, String accountType) {

        this.name = name;
        this.walletId = walletId;
        this.balance = balance;
        this.mobile = mobile;
        this.email = email;
        this.accountType = accountType;
    }

    // Method Overriding
    @Override
    public void displayDetails() {

        System.out.println("\n===== DIGITAL WALLET DETAILS =====");
        System.out.println("Customer Name: " + name);
        System.out.println("Wallet ID: " + walletId);
        System.out.println("Balance: Rs. " + balance);
        System.out.println("Mobile Number: " + mobile);
        System.out.println("Email ID: " + email);
        System.out.println("Account Type: " + accountType);

        if (balance >= 1000) {
            System.out.println("Wallet Status: Active");
        } else {
            System.out.println("Wallet Status: Low Balance");
        }
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("DIGITAL WALLET BANK");

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Wallet ID: ");
        int walletId = sc.nextInt();

        System.out.print("Enter Wallet Balance: ");
        double balance = sc.nextDouble();

        sc.nextLine(); // Consume newline

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        System.out.print("Enter Email ID: ");
        String email = sc.nextLine();

        System.out.print("Enter Account Type: ");
        String accountType = sc.nextLine();

        // Parent reference and child object
        Bank bank = new DigitalWallet(
            name, walletId, balance,
            mobile, email, accountType
        );

        // Calls overridden method
        bank.displayDetails();

        sc.close();
    }
}