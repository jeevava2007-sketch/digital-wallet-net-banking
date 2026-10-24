import java.util.Scanner;

class DigitalWallet {

    private int accountId;
    private String name;
    private double balance;

    DigitalWallet(int accountId, String name, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit amount must be greater than zero."
            );
        }

        balance += amount;
        System.out.println(
            name + " deposited ₹" + amount
        );
    }

    public void transfer(DigitalWallet receiver, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Transfer amount must be greater than zero."
            );
        }

        /*
         * DEADLOCK PREVENTION:
         * Always acquire locks in account-ID order.
         * This prevents two threads from waiting for
         * each other indefinitely.
         */
        DigitalWallet first;
        DigitalWallet second;

        if (this.accountId < receiver.accountId) {
            first = this;
            second = receiver;
        } else {
            first = receiver;
            second = this;
        }

        synchronized (first) {
            synchronized (second) {

                if (this.balance < amount) {
                    throw new ArithmeticException(
                        "Insufficient balance in " + name
                    );
                }

                this.balance -= amount;
                receiver.balance += amount;

                System.out.println(
                    "₹" + amount + " transferred from "
                    + this.name + " to "
                    + receiver.name
                );
            }
        }
    }

    public synchronized void display() {
        System.out.println(
            "Account ID : " + accountId
        );
        System.out.println(
            "Name       : " + name
        );
        System.out.println(
            "Balance    : ₹" + balance
        );
    }
}

class TransferThread extends Thread {

    private DigitalWallet sender;
    private DigitalWallet receiver;
    private double amount;

    TransferThread(
        DigitalWallet sender,
        DigitalWallet receiver,
        double amount
    ) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public void run() {

        try {
            sender.transfer(receiver, amount);

        } catch (ArithmeticException e) {
            System.out.println(
                "Transaction failed: " + e.getMessage()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                "Invalid amount: " + e.getMessage()
            );
        }
    }
}

public class exp11 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println(
                "=========================================="
            );
            System.out.println(
                "       DIGITAL WALLET NETBANKING"
            );
            System.out.println(
                "       DEADLOCK PREVENTION"
            );
            System.out.println(
                "=========================================="
            );

            System.out.print(
                "Enter Account 1 Name: "
            );
            String name1 = sc.nextLine();

            System.out.print(
                "Enter Account 1 Balance: ₹"
            );
            double balance1 = sc.nextDouble();

            System.out.print(
                "Enter Account 2 Name: "
            );
            sc.nextLine();
            String name2 = sc.nextLine();

            System.out.print(
                "Enter Account 2 Balance: ₹"
            );
            double balance2 = sc.nextDouble();

            if (balance1 < 0 || balance2 < 0) {
                throw new IllegalArgumentException(
                    "Balance cannot be negative."
                );
            }

            DigitalWallet account1 =
                new DigitalWallet(1, name1, balance1);

            DigitalWallet account2 =
                new DigitalWallet(2, name2, balance2);

            System.out.println(
                "\n--- Initial Account Details ---"
            );

            account1.display();
            account2.display();

            System.out.print(
                "\nEnter transfer amount from "
                + name1 + " to " + name2 + ": ₹"
            );
            double amount1 = sc.nextDouble();

            System.out.print(
                "Enter transfer amount from "
                + name2 + " to " + name1 + ": ₹"
            );
            double amount2 = sc.nextDouble();

            if (amount1 <= 0 || amount2 <= 0) {
                throw new IllegalArgumentException(
                    "Transfer amount must be greater than zero."
                );
            }

            /*
             * Both threads may request opposite transfers.
             * The transfer() method always locks accounts
             * according to account ID, preventing deadlock.
             */
            TransferThread t1 =
                new TransferThread(
                    account1, account2, amount1
                );

            TransferThread t2 =
                new TransferThread(
                    account2, account1, amount2
                );

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(
                "\n--- Final Account Details ---"
            );

            account1.display();
            account2.display();

            System.out.println(
                "\nAll transactions completed safely."
            );
            System.out.println(
                "Deadlock prevented using synchronized blocks."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Input Error: " + e.getMessage()
            );

        } catch (InterruptedException e) {

            System.out.println(
                "Thread interrupted."
            );

        } catch (Exception e) {

            System.out.println(
                "Invalid input. Please enter valid values."
            );

        } finally {

            sc.close();
        }
    }
}