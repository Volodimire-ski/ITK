import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {
    private Map<UUID, BigDecimal> accounts;

    public BankAccount createAccount(double amount) {
        BankAccount newAccount = new BankAccount(amount);
        if(accounts==null) {
            synchronized (this) {
                if(accounts==null) {
                    accounts = new ConcurrentHashMap<>();
                }
            }
        }
        accounts.put(newAccount.getId(),newAccount.getBalance());
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
        for(Map.Entry<UUID,BigDecimal> m : accounts.entrySet()) {
            totalBalance = totalBalance.add(m.getValue());
        }
        return totalBalance;
    }
}
