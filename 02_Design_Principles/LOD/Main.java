// Law of Demeter (LoD) states that a method M of an object O should only call the methods of the following kinds of objects:
// 1. O itself
// 2. M's parameters
// 3. Any objects created/instantiated within M

// ********* Bad Example *********
class City {
    private String name;
    City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Address {
    private City city;

    Address(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }
}

class Customer {
    private Address address;

    Customer(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}

class OrderService {
    public void placeOrder(Customer customer) {
        // Violates LoD: Accessing city name through multiple levels of objects
        String cityName = customer.getAddress().getCity().getName();
        System.out.println("Placing order for customer in city: " + cityName);
    }
}

// ********* Good Example *********
class NewCustomer {
    private String cityName;

    NewCustomer(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}

class NewOrderService {
    public void placeOrder(NewCustomer customer) {
        // Follows LoD: Directly accessing city name from NewCustomer
        String cityName = customer.getCityName();
        System.out.println("Placing order for customer in city: " + cityName);
    }
}

public class Main {
    public static void main(String[] args) {
        // Bad example implementation
        City city = new City("New York");
        Address address = new Address(city);
        Customer customer = new Customer(address);
        OrderService orderService = new OrderService();
        orderService.placeOrder(customer);

        // Good example implementation
        NewCustomer newCustomer = new NewCustomer("New York");
        NewOrderService newOrderService = new NewOrderService();
        newOrderService.placeOrder(newCustomer);
    }
}