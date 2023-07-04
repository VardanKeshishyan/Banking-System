import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
            return true;
        }
        return false;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class BankingSystem {
    private Map<String, User> users;
    private Map<String, BankAccount> accounts;

    public BankingSystem() {
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        User newUser = new User(username, password);
        users.put(username, newUser);
    }

    public boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null) {
            return user.authenticate(password);
        }
        return false;
    }

    public BankAccount createAccount(String accountNumber) {
        BankAccount newAccount = new BankAccount(accountNumber);
        accounts.put(accountNumber, newAccount);
        return newAccount;
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

public class Bank {
    public static void main(String[] args) {
        
        BankingSystem bankingSystem = new BankingSystem();

        bankingSystem.registerUser("john123", "password123");

        boolean isAuthenticated = bankingSystem.authenticateUser("john123", "password123");
        if (isAuthenticated) {
            System.out.println("Authentication successful.");
        } else {
            System.out.println("Authentication failed.");
            return;
        }

        BankAccount account = bankingSystem.createAccount("ACC123");

        account.deposit(1000);
        account.withdraw(500);

        System.out.println("Account Balance: $" + account.getBalance());
        account.printTransactionHistory();
    }
}

