import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CashDispenser {
    private Map<Integer, Integer> denominations;
    private CustomerManager customerManager;

    public CashDispenser(CustomerManager customerManager) {
        this.customerManager = customerManager;
        denominations = new HashMap<>();
    }

    public void loadCash() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter Pin Number: ");
        int pinNumber = scanner.nextInt();

        Customer customer = customerManager.getCustomer(accountNumber);

        if (customer == null || customer.getPinNumber() != pinNumber) {
            System.out.println("Invalid account number or pin.");
            return;
        }

        System.out.print("Enter denomination (e.g., 2000, 500, 100): ");
        int denomination = scanner.nextInt();
        System.out.print("Enter number of notes: ");
        int numberOfNotes = scanner.nextInt();

        denominations.put(denomination, denominations.getOrDefault(denomination, 0) + numberOfNotes);

        // Update customer balance
        int totalLoaded = denomination * numberOfNotes;
        customer.setBalance(customer.getBalance() + totalLoaded);

        showATMBalance();
    }

    public void showATMBalance() {
        int total = 0;
        System.out.println("ATM Balance:");
        System.out.println("Denomination\tNumber\tValue");
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int value = entry.getKey() * entry.getValue();
            total += value;
            System.out.println(entry.getKey() + "\t\t" + entry.getValue() + "\t" + value);
        }
        System.out.println("Total Amount available in the ATM = " + total);
    }

    public void withdrawMoney(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter amount to withdraw: ");
        int amount = scanner.nextInt();

        if (amount > 10000 || amount < 100) {
            System.out.println("Invalid amount. Withdrawal amount must be between 100 and 10,000 ₹.");
            return;
        }

        if (customer.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        // Validate ATM denominations for withdrawal
        int remainingAmount = amount;
        Map<Integer, Integer> tempDenominations = new HashMap<>(denominations);

        int num2000 = Math.min(tempDenominations.getOrDefault(2000, 0), remainingAmount / 2000);
        remainingAmount -= num2000 * 2000;

        int num500 = Math.min(tempDenominations.getOrDefault(500, 0), remainingAmount / 500);
        remainingAmount -= num500 * 500;

        int num100 = Math.min(tempDenominations.getOrDefault(100, 0), remainingAmount / 100);
        remainingAmount -= num100 * 100;

        if (remainingAmount > 0) {
            System.out.println("ATM does not have enough denominations to dispense the amount.");
            return;
        }

        // Update ATM balance
        denominations.put(2000, denominations.getOrDefault(2000, 0) - num2000);
        denominations.put(500, denominations.getOrDefault(500, 0) - num500);
        denominations.put(100, denominations.getOrDefault(100, 0) - num100);

        // Update customer balance
        customer.setBalance(customer.getBalance() - amount);

        System.out.println("Amount withdrawn successfully.");
        showATMBalance();
    }
}
