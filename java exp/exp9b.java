// EXP9B.java
// Thread using implements Runnable

import java.util.Scanner;

class NetBanking implements Runnable {

    private String accountHolder;
    private double balance;

    NetBanking(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
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
            accountHolder + " deposited ₹" + amount
        );
    }

    synchronized void transfer(double amount) {
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
            accountHolder + " transferred ₹" + amount
        );
    }

    synchronized void displayBalance() {
        System.out.println(
            "Account Holder : " + accountHolder
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
                "\n--- NetBanking Transaction ---"
            );

            displayBalance();

            System.out.println("1. Deposit");
            System.out.println("2. Transfer");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            System.out.print("Enter amount: ₹");
            double amount = sc.nextDouble();

            switch (choice) {

                case 1:
                    deposit(amount);
                    break;

                case 2:
                    transfer(amount);
                    break;

                default:
                    System.out.println(
                        "Invalid choice."
                    );
            }

            System.out.println(
                "\nTransaction Completed."
            );

            displayBalance();

        } catch (ArithmeticException e) {

            System.out.println(
                "Transaction Failed: "
                + e.getMessage()
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Invalid Input: "
                + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                "Please enter valid input."
            );
        }
    }
}

public class exp9b {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println(
                "================================="
            );
            System.out.println(
                "       NETBANKING SYSTEM"
            );
            System.out.println(
                "       EXPERIMENT 9B"
            );
            System.out.println(
                "================================="
            );

            System.out.print(
                "Enter Account Holder Name: "
            );
            String name = sc.nextLine();

            System.out.print(
                "Enter Initial Balance: ₹"
            );
            double balance = sc.nextDouble();

            if (balance < 0) {
                throw new IllegalArgumentException(
                    "Balance cannot be negative."
                );
            }

            NetBanking account =
                new NetBanking(name, balance);

            Thread transactionThread =
                new Thread(account);

            transactionThread.start();
            transactionThread.join();

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