import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {
    private UUID id;
    private BigDecimal balance;

    public BankAccount(double amount) {
        id = UUID.randomUUID();
        this.balance = BigDecimal.valueOf(amount).setScale(2);
    }

    synchronized void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }
    synchronized void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if(balance.compareTo(amount)<0) {
            throw new IllegalArgumentException("No enough money on balance");
        }
        balance = balance.subtract(amount);
    }
    private static void validateAmount(BigDecimal amount) {
        if (amount.doubleValue()<0) throw new IllegalArgumentException("Amount is negative");
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public UUID getId() {
        return id;
    }
}
