import java.util.Scanner;

class DigitalWallet extends Thread {

    private String name;
    private double balance;

    DigitalWallet(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }

        balance += amount;
        System.out.println("₹" + amount + " deposited successfully.");
    }

    synchronized void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }

        if (amount > balance) {
            throw new ArithmeticException(
                    "Insufficient balance!");
        }

        balance -= amount;
        System.out.println("₹" + amount + " withdrawn successfully.");
    }

    synchronized void transfer(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }

        if (amount > balance) {
            throw new ArithmeticException(
                    "Insufficient balance for transfer!");
        }

        balance -= amount;
        System.out.println("₹" + amount + " transferred successfully.");
    }

    synchronized void displayBalance() {
        System.out.println("Account Holder : " + name);
        System.out.println("Current Balance: ₹" + balance);
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("\n--- Digital Wallet Transaction ---");
            displayBalance();

            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            switch (choice) {
                case 1:
                    deposit(amount);
                    break;

                case 2:
                    withdraw(amount);
                    break;

                case 3:
                    transfer(amount);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Invalid choice!");
            }

            System.out.println("\nTransaction Successful!");
            displayBalance();

        } catch (ArithmeticException e) {
            System.out.println("Transaction Failed: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Input: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Error: Please enter valid data.");
        }
    }
}

public class exp8 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("=================================");
            System.out.println("     DIGITAL WALLET SYSTEM");
            System.out.println("=================================");

            System.out.print("Enter Account Holder Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ₹");
            double balance = sc.nextDouble();

            if (balance < 0) {
                throw new IllegalArgumentException(
                        "Initial balance cannot be negative!");
            }

            DigitalWallet wallet =
                    new DigitalWallet(name, balance);

            wallet.start();
            wallet.join();

            System.out.println("\nThank you for using Digital Wallet!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");

        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");

        } finally {
            sc.close();
        }
    }
}