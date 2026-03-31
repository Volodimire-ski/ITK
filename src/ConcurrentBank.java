import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {
    private final Map<UUID, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(double amount) {
        BankAccount newAccount = new BankAccount(amount);
        accounts.put(newAccount.getId(),newAccount);
        return newAccount;
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        BigDecimal amountOfOperation = BigDecimal.valueOf(amount).setScale(2);
        if (from.getId().compareTo(to.getId())>0) {
            synchronized (from) {
                synchronized (to) {
                    from.withdraw(amountOfOperation);
                    to.deposit(amountOfOperation);
                }
            }
        } else {
            synchronized (to) {
                synchronized (from) {
                    from.withdraw(amountOfOperation);
                    to.deposit(amountOfOperation);
                }
            }
        }
    }
    public BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.valueOf(0);
        for(Map.Entry<UUID,BankAccount> m : accounts.entrySet()) {
            totalBalance = totalBalance.add(m.getValue().getBalance());
        }
        return totalBalance;
    }
}
