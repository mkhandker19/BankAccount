public class BankAccount {
    private int accountNumber;
    private double balance;
    private String firstName;
    private String lastName;

    public BankAccount(int accountNumber, double balance, String firstName, String lastName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void deposit(double amount) {
        if  (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if  (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return accountNumber + "," + firstName + "," + lastName + "," + String.format("%.2f", balance);
    }
}
