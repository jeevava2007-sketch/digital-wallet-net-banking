
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

    // Method overriding
    @Override
    public void displayDetails() {

        System.out.println("\n===== DIGITAL WALLET DETAILS =====");
        System.out.println("Customer Name: " + name);
        System.out.println("Wallet ID: " + walletId);
        System.out.printf("Balance: Rs. %.2f%n", balance);
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
public class exp5 {

    private static String readLine(Scanner sc, String prompt) {
        System.out.print(prompt);

        if (!sc.hasNextLine()) {
            return "";
        }

        return sc.nextLine();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== DIGITAL WALLET BANK =====");

        String name = readLine(sc, "Enter Customer Name: ");

        int walletId = 0;
        double balance = 0.0;

        if (sc.hasNextInt()) {
            walletId = sc.nextInt();
        } else {
            System.out.println("Invalid Wallet ID.");
            sc.close();
            return;
        }

        if (sc.hasNextDouble()) {
            balance = sc.nextDouble();
        } else {
            System.out.println("Invalid Wallet Balance.");
            sc.close();
            return;
        }

        sc.nextLine(); // Consume newline

        String mobile = readLine(sc, "Enter Mobile Number: ");
        String email = readLine(sc, "Enter Email ID: ");
        String accountType = readLine(sc, "Enter Account Type: ");

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
