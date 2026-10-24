// EXP9A.java
// Thread using extends Thread

import java.util.Scanner;

class DigitalWallet extends Thread {

    private String userName;
    private double balance;

    DigitalWallet(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
    }

    synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero."
            );
        }

        balance += amount;
        System.out.println(
            userName + " deposited ₹" + amount
        );
    }

    synchronized void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new ArithmeticException(
                "Insufficient balance."
            );
        }

        balance -= amount;
        System.out.println(
            userName + " withdrew ₹" + amount
        );
    }

    synchronized void displayBalance() {
        System.out.println(
            "Account Holder : " + userName
        );
        System.out.println(
            "Balance        : ₹" + balance
        );
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println(
                "\n--- Digital Wallet Transaction ---"
            );

            displayBalance();

            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            System.out.print("Enter amount: ₹");
            double amount = sc.nextDouble();

            switch (choice) {
                case 1:
                    deposit(amount);
                    break;

                case 2:
                    withdraw(amount);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println("\nTransaction Completed.");
            displayBalance();

        } catch (ArithmeticException e) {
            System.out.println(
                "Transaction Failed: " + e.getMessage()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Invalid Input: " + e.getMessage()
            );

        } catch (Exception e) {
            System.out.println(
                "Please enter valid input."
            );
        }
    }
}

public class exp9a {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println(
                "================================="
            );
            System.out.println(
                "     DIGITAL WALLET SYSTEM"
            );
            System.out.println(
                "     EXPERIMENT 9A"
            );
            System.out.println(
                "================================="
            );

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ₹");
            double balance = sc.nextDouble();

            if (balance < 0) {
                throw new IllegalArgumentException(
                    "Balance cannot be negative."
                );
            }

            DigitalWallet wallet =
                new DigitalWallet(name, balance);

            wallet.start();
            wallet.join();

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Error: " + e.getMessage()
            );

        } catch (InterruptedException e) {
            System.out.println(
                "Thread interrupted."
            );

        } catch (Exception e) {
            System.out.println(
                "Invalid input."
            );

        } finally {
            sc.close();
        }
    }
}