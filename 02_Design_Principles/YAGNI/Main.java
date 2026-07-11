// The task is to Store a user's name.
// ****** Bad Practice of code that violates the YAGNI principle ******
class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    public User(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

// ******** Good Practice of code that follows the YAGNI principle ***********
class SimpleUser {
    private String name;

    public SimpleUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        // Implementation of Bad Practice
        User user = new User("John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        System.out.println("User Name: " + user.getName());

        // Implementation of Good Practice
        SimpleUser simpleUser = new SimpleUser("John Doe");
        System.out.println("Simple User Name: " + simpleUser.getName());
    }
}
