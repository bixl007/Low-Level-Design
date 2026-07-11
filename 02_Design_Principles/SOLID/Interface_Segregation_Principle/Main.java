// Interface Segregation Principle (ISP) states that clients should not be forced to depend on interfaces they do not use. In other words, it's better to have multiple specific interfaces rather than a single general-purpose interface. This principle helps to reduce the impact of changes and promotes a more modular and maintainable codebase.

// Bad Example
interface Uber {
    void bookRide();
    void acceptRide();
    void drive();
    void endRide();
    void payRide();
}

// Good example
interface RiderInterface {
    void bookRide();
    void payRide();
}

interface DriverInterface {
    void acceptRide();
    void drive();
    void endRide();
}

class Rider implements RiderInterface {
    @Override
    public void bookRide() {
        System.out.println("Ride booked");
    }

    @Override
    public void payRide() {
        System.out.println("Ride paid");
    }
}

class Driver implements DriverInterface {
    @Override
    public void acceptRide() {
        System.out.println("Ride accepted");
    }

    @Override
    public void drive() {
        System.out.println("Driving");
    }

    @Override
    public void endRide() {
        System.out.println("Ride ended");
    }
}

class Main {
    public static void main(String[] args) {
        Rider rider = new Rider();
        rider.bookRide();
        rider.payRide();

        Driver driver = new Driver();
        driver.acceptRide();
        driver.drive();
        driver.endRide();
    }
}

