import java.util.Scanner;

class DigitalWallet {

    private double balance;

    DigitalWallet(double balance) {
        this.balance = balance;
    }

    synchronized void withdraw(double amount) {

        try {
            while (balance < amount) {
                System.out.println(
                    "Insufficient balance. Withdrawal thread is waiting..."
                );

                wait();   // Wait until money is deposited
            }

            balance -= amount;

            System.out.println(
                "Withdrawal successful: ₹" + amount
            );
            System.out.println(
                "Remaining Balance: ₹" + balance
            );

        } catch (InterruptedException e) {
            System.out.println("Withdrawal interrupted.");
        }
    }

    synchronized void deposit(double amount) {

        balance += amount;

        System.out.println(
            "Amount deposited: ₹" + amount
        );
        System.out.println(
            "Current Balance: ₹" + balance
        );

        notify();       // Wake one waiting thread
        notifyAll();    // Wake all waiting threads
    }

    synchronized void displayBalance() {
        System.out.println(
            "Current Wallet Balance: ₹" + balance
        );
    }
}

class WithdrawalThread extends Thread {

    private DigitalWallet wallet;
    private double amount;

    WithdrawalThread(DigitalWallet wallet, double amount) {
        this.wallet = wallet;
        this.amount = amount;
    }

    @Override
    public void run() {
        wallet.withdraw(amount);
    }
}

class DepositThread extends Thread {

    private DigitalWallet wallet;
    private double amount;

    DepositThread(DigitalWallet wallet, double amount) {
        this.wallet = wallet;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Deposit interrupted.");
        }

        wallet.deposit(amount);
    }
}

public class exp10 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("====================================");
            System.out.println("     DIGITAL WALLET NETBANKING");
            System.out.println("     INTER-THREAD COMMUNICATION");
            System.out.println("====================================");

            System.out.print("Enter initial wallet balance: ₹");
            double balance = sc.nextDouble();

            if (balance < 0) {
                throw new IllegalArgumentException(
                    "Balance cannot be negative."
                );
            }

            System.out.print("Enter withdrawal amount: ₹");
            double withdrawAmount = sc.nextDouble();

            if (withdrawAmount <= 0) {
                throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero."
                );
            }

            System.out.print("Enter deposit amount: ₹");
            double depositAmount = sc.nextDouble();

            if (depositAmount <= 0) {
                throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero."
                );
            }

            DigitalWallet wallet =
                new DigitalWallet(balance);

            System.out.println("\n--- Initial Balance ---");
            wallet.displayBalance();

            WithdrawalThread withdrawal =
                new WithdrawalThread(
                    wallet, withdrawAmount
                );

            DepositThread deposit =
                new DepositThread(
                    wallet, depositAmount
                );

            System.out.println(
                "\nStarting Withdrawal Thread..."
            );
            withdrawal.start();

            System.out.println(
                "Starting Deposit Thread..."
            );
            deposit.start();

            withdrawal.join();
            deposit.join();

            System.out.println(
                "\n--- Final Balance ---"
            );
            wallet.displayBalance();

            System.out.println(
                "\nTransaction completed successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Input Error: " + e.getMessage()
            );

        } catch (InterruptedException e) {

            System.out.println(
                "Main thread interrupted."
            );

        } catch (Exception e) {

            System.out.println(
                "Invalid input. Please enter numeric values."
            );

        } finally {

            sc.close();
        }
    }
}