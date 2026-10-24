package wallet;

public class DigitalWallet {

    private String name;
    private double balance;

    public DigitalWallet(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid amount");

        balance += amount;
        System.out.println("Deposit successful!");
    }

    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid amount");

        if (amount > balance)
            throw new ArithmeticException("Insufficient balance");

        balance -= amount;
        System.out.println("Withdrawal successful!");
    }

    public void transfer(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid amount");

        if (amount > balance)
            throw new ArithmeticException("Insufficient balance");

        balance -= amount;
        System.out.println("Transfer successful!");
    }

    public void displayBalance() {
        System.out.println("Account Holder : " + name);
        System.out.println("Balance        : ₹" + balance);
    }
}