import java.util.Scanner;

public class ATM {
    private static CustomerManager customerManager = new CustomerManager();
    private static CashDispenser cashDispenser = new CashDispenser(customerManager);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Load Cash to ATM");
            System.out.println("2. Show Customer Details");
            System.out.println("3. Show ATM Operations");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    cashDispenser.loadCash();
                    break;
                case 2:
                    showCustomerDetails();
                    break;
                case 3:
                    showATMOperations();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }

    private static void showCustomerDetails() {
        System.out.println("Customer Details:");
        System.out.println("Acc No\tAccount Holder\tPin Number\tAccount Balance");
        for (Customer customer : customerManager.getCustomers().values()) {
            System.out.println(customer.getAccountNumber() + "\t" + customer.getAccountHolder() + "\t" +
                    customer.getPinNumber() + "\t" + customer.getBalance());
        }
    }

    private static void showATMOperations() {
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

        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check ATM Balance");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Your balance is: " + customer.getBalance() + " ₹");
                    break;
                case 2:
                    cashDispenser.withdrawMoney(customer);
                    break;
                case 3:
                    // Implement transfer money functionality
                    System.out.println("Transfer Money feature not implemented yet.");
                    break;
                case 4:
                    cashDispenser.showATMBalance();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }
}
