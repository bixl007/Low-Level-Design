

import java.util.*;

// BAD EXAMPLE: Violating SRP 
// A single class has multiple responsibilities
class UserBad {
    private String name;
    private String email;
    private String password;

    public UserBad(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Responsibility 1: User management
    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
        System.out.println("Profile updated for: " + name);
    }

    // Responsibility 2: Email notifications
    public void sendWelcomeEmail() {
        System.out.println("Sending welcome email to: " + email);
    }

    // Responsibility 3: Password management
    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password changed successfully");
    }

    // Responsibility 4: Data persistence
    public void saveToDatabase() {
        System.out.println("Saving user to database");
    }
}

// GOOD EXAMPLE: Following SRP 
// Each class has a single, well-defined responsibility

// 1. User class - Only manages user data
class User {
    private String userId;
    private String name;
    private String email;
    private String password;

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// 2. Email service - Only handles email operations
class EmailService {
    public void sendWelcomeEmail(User user) {
        System.out.println("Sending welcome email to: " + user.getEmail());
    }

    public void sendPasswordResetEmail(User user) {
        System.out.println("Sending password reset email to: " + user.getEmail());
    }

    public void sendNotificationEmail(User user, String message) {
        System.out.println("Sending notification to " + user.getEmail() + ": " + message);
    }
}

// 3. Password validator - Only handles password validation
class PasswordValidator {
    public boolean isValidPassword(String password) {
        return password.length() >= 8 && 
               password.matches(".*[A-Z].*") && 
               password.matches(".*[0-9].*");
    }

    public String getPasswordStrength(String password) {
        if (password.length() < 8) {
            return "WEAK";
        } else if (password.length() >= 12 && 
                   password.matches(".*[!@#$%^&*()].*")) {
            return "STRONG";
        }
        return "MEDIUM";
    }
}

// 4. User repository - Only handles data persistence
class UserRepository {
    private Map<String, User> database = new HashMap<>();

    public void save(User user) {
        database.put(user.getUserId(), user);
        System.out.println("User " + user.getName() + " saved to database");
    }

    public User findById(String userId) {
        return database.get(userId);
    }

    public void update(User user) {
        if (database.containsKey(user.getUserId())) {
            database.put(user.getUserId(), user);
            System.out.println("User " + user.getName() + " updated in database");
        }
    }

    public void delete(String userId) {
        database.remove(userId);
        System.out.println("User " + userId + " deleted from database");
    }
}

// 5. User service - Coordinates user operations
class UserService {
    private UserRepository userRepository;
    private EmailService emailService;
    private PasswordValidator passwordValidator;

    public UserService(UserRepository userRepository, 
                      EmailService emailService, 
                      PasswordValidator passwordValidator) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordValidator = passwordValidator;
    }

    public void registerUser(String userId, String name, String email, String password) {
        // Validate password
        if (!passwordValidator.isValidPassword(password)) {
            System.out.println("Password does not meet requirements!");
            return;
        }

        // Create user
        User user = new User(userId, name, email, password);

        // Save user
        userRepository.save(user);

        // Send welcome email
        emailService.sendWelcomeEmail(user);

        System.out.println("User registered successfully!");
    }

    public void changePassword(String userId, String newPassword) {
        User user = userRepository.findById(userId);
        
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (!passwordValidator.isValidPassword(newPassword)) {
            System.out.println("Password does not meet requirements!");
            return;
        }

        user.setPassword(newPassword);
        userRepository.update(user);
        emailService.sendPasswordResetEmail(user);
        System.out.println("Password changed successfully!");
    }
}

// ============= MAIN METHOD: Demonstration =============
public class Main {
    public static void main(String[] args) {
        System.out.println("========== Single Responsibility Principle Example ==========\n");

        // Initialize dependencies
        UserRepository userRepository = new UserRepository();
        EmailService emailService = new EmailService();
        PasswordValidator passwordValidator = new PasswordValidator();

        // Create user service
        UserService userService = new UserService(userRepository, emailService, passwordValidator);

        // Register a new user
        System.out.println("--- Registering a new user ---");
        userService.registerUser("USR001", "John Doe", "john@example.com", "SecurePass123");

        System.out.println("\n--- Attempting to register with weak password ---");
        userService.registerUser("USR002", "Jane Smith", "jane@example.com", "weak");

        System.out.println("\n--- Changing password ---");
        userService.changePassword("USR001", "NewSecurePass456");

        System.out.println("\n========== Key Benefits of SRP ==========");
        System.out.println("1. Each class has a single reason to change");
        System.out.println("2. Easier to test (can test each service independently)");
        System.out.println("3. Better code reusability");
        System.out.println("4. Easier to maintain and extend");
        System.out.println("5. Classes are more focused and cohesive");
    }
}