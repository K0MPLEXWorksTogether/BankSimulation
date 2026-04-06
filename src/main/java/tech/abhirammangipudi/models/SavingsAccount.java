package tech.abhirammangipudi.models;

import java.time.LocalDateTime;
import java.util.UUID;

import tech.abhirammangipudi.errors.InsufficientFundsException;
import tech.abhirammangipudi.errors.InvalidAmountException;
import tech.abhirammangipudi.errors.MinimumBalanceException;
import tech.abhirammangipudi.interfaces.Withdraw;
import tech.abhirammangipudi.models.Transaction.TransactionAgent;
import tech.abhirammangipudi.models.Transaction.TransactionType;

public class SavingsAccount extends Account implements Withdraw {
    private final double intrestRate;

    public SavingsAccount(String accountNumber, User accountHolder, double balance, LocalDateTime dateOpened,
            double minimumBalance, double intrestRate) {
        super(accountHolder, balance, dateOpened, minimumBalance);
        this.intrestRate = intrestRate;
    }

    public SavingsAccount(String accountNumber, User accountHolder, double balance, double minimumBalance,
            double intrestRate) {
        super(accountHolder, balance, minimumBalance);
        this.intrestRate = intrestRate;
    }

    public double getIntrestRate() {
        return intrestRate;
    }

    public void calculateIntrest() throws InvalidAmountException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastInterestDate = getDateOpened();

        for (Transaction transaction : getTransactions()) {
            if (transaction.getTransactionAgent() == TransactionAgent.BANK
                    && transaction.getType() == TransactionType.DEPOSIT
                    && transaction.getTimestamp().isAfter(lastInterestDate)) {
                lastInterestDate = transaction.getTimestamp();
            }
        }

        if (lastInterestDate.plusYears(1).isBefore(now) || lastInterestDate.plusYears(1).isEqual(now)) {
            double interest = getBalance() * (intrestRate / 100);
            this.deposit(interest);
        }
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientFundsException, InvalidAmountException, MinimumBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive", amount);
        }

        if (!canWithdraw(amount)) {
            throw new InsufficientFundsException("Withdrawal of funds greater than the balance", getBalance());
        }

        if (getBalance() - amount < getMinimumBalance()) {
            throw new MinimumBalanceException(
                    "Withdrawal of funds leaves the account with lower funds than the minimum balance",
                    getMinimumBalance());
        }

        setBalance(getBalance() - amount);
        Transaction transaction = new Transaction(UUID.randomUUID(), TransactionType.WITHDRAWAL, amount,
                LocalDateTime.now(), getBalance(), TransactionAgent.USER);
        addTransaction(transaction);
    }
}
