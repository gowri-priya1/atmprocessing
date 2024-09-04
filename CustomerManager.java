import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    private Map<Integer, Customer> customers;

    public CustomerManager() {
        customers = new HashMap<>();
        // Sample customers
        customers.put(101, new Customer(101, "Suresh", 2343, 0));
        customers.put(102, new Customer(102, "Ganesh", 5432, 34123));
        customers.put(103, new Customer(103, "Magesh", 7854, 26100));
        customers.put(104, new Customer(104, "Naresh", 2345, 80000));
        customers.put(105, new Customer(105, "Harish", 1907, 103400));
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(int accountNumber) {
        return customers.get(accountNumber);
    }
}
