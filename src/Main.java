import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();
        int nextAccountNumber = 1;
        int choice;

        do {
            System.out.println("\n1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Check balance");
            System.out.println("4. Create new account");
            System.out.println("0. Quit");
            System.out.print("Your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter account number: ");
                    int depAccNum = scanner.nextInt();
                    BankAccount depAccount = findAccount(accounts, depAccNum);

                    if (depAccount != null) {
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        depAccount.deposit(amount);
                        System.out.println("New balance: $" + depAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    int withAccNum = scanner.nextInt();
                    BankAccount withAccount = findAccount(accounts, withAccNum);

                    if (withAccount != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        if (withAccount.withdraw(amount)) {
                            System.out.println("New balance: $" + withAccount.getBalance());
                        } else {
                            System.out.println("Insufficient funds or invalid amount.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    int balAccNum = scanner.nextInt();
                    BankAccount balAccount = findAccount(accounts, balAccNum);

                    if (balAccount != null) {
                        System.out.println("Account Number: " + balAccount.getAccountNumber());
                        System.out.println("Balance: $" + balAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    scanner.nextLine();

                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();

                    if (firstName.length() >= 3 && lastName.length() >= 3) {
                        BankAccount newAccount = new BankAccount(
                                nextAccountNumber++,
                                initialBalance,
                                firstName,
                                lastName);

                        accounts.add(newAccount);
                        System.out.println("Account created successfully!");
                    } else {
                        System.out.println("First and last name must be at least 3 characters.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option selected.");
            }

        } while (choice != 0);

        if (!accounts.isEmpty()) {
            BankAccount highest = accounts.get(0);
            BankAccount lowest = accounts.get(0);

            for (BankAccount acc : accounts) {
                if (acc.getBalance() > highest.getBalance()) {
                    highest = acc;
                }
                if (acc.getBalance() < lowest.getBalance()) {
                    lowest = acc;
                }
            }

            System.out.println("\nHighest balance: Account " +
                    highest.getAccountNumber() + " Amount $" + highest.getBalance());

            System.out.println("Lowest balance: Account " +
                    lowest.getAccountNumber() + " Amount $" + lowest.getBalance());
        }

        try (FileWriter writer = new FileWriter("MyBank.txt")) {
            for (BankAccount acc : accounts) {
                writer.write(acc.toString() + "\n");
            }
            System.out.println("Accounts saved to MyBank.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

        scanner.close();
    }

    private static BankAccount findAccount(ArrayList<BankAccount> accounts, int accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }
}