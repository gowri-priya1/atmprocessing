public class Customer {
    private int accountNumber;
    private String accountHolder;
    private int pinNumber;
    private int balance;

    public Customer(int accountNumber, String accountHolder, int pinNumber, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.pinNumber = pinNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
