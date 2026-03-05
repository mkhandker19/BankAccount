import java.math.*;

public class BankAccount {
    private int accountNumber;
    private BigDecimal balance;
    private String firstName;
    private String lastName;

    public BankAccount(int accountNumber, BigDecimal balance, String firstName, String lastName) {
        this.accountNumber = accountNumber;
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            this.balance = BigDecimal.ZERO;
        } else {
            this.balance = balance;
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        balance = balance.add(amount);
        return true;
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (balance.compareTo(amount) < 0) {
            return false;
        }
        balance = balance.subtract(amount);
        return true;
    }

    @Override
    public String toString() {
        BigDecimal scaled = balance.setScale(2, RoundingMode.HALF_UP);
        return accountNumber + "," + firstName + "," + lastName + "," + scaled.toPlainString();
    }
}
