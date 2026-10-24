import java.util.Scanner;
import wallet.DigitalWallet;

public class exp7 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("===== DIGITAL WALLET NETBANKING =====");

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ₹");
            double balance = sc.nextDouble();

            if (balance < 0)
                throw new IllegalArgumentException(
                    "Balance cannot be negative"
                );

            DigitalWallet wallet =
                new DigitalWallet(name, balance);

            int choice;

            do {
                System.out.println("\n----- MENU -----");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Check Balance");
                System.out.println("5. Exit");

                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                try {
                    switch (choice) {

                        case 1:
                            System.out.print("Enter amount: ₹");
                            wallet.deposit(sc.nextDouble());
                            break;

                        case 2:
                            System.out.print("Enter amount: ₹");
                            wallet.withdraw(sc.nextDouble());
                            break;

                        case 3:
                            System.out.print("Enter amount: ₹");
                            wallet.transfer(sc.nextDouble());
                            break;

                        case 4:
                            wallet.displayBalance();
                            break;

                        case 5:
                            System.out.println("Thank you!");
                            break;

                        default:
                            System.out.println("Invalid choice!");
                    }

                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());

                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } while (choice != 5);

        } catch (Exception e) {
            System.out.println("Invalid input!");

        } finally {
            sc.close();
        }
    }
}