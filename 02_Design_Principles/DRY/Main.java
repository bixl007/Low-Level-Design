// *****Bad Example of code that violates the DRY principle******
class AuthService {
    public boolean isValidEmail(String email) {
        if(email == null || email.isEmpty()) {
            return false;
        }
        // Check if email contains '@' and '.'
        if(!email.contains("@") || !email.contains(".")) {
            return false;
        }

        return true;
    }

    public void authenticate(String email) {
        if(isValidEmail(email)) {
            System.out.println("Successfully authenticated user: " + email);
        } else {
            System.out.println("Invalid email provided.");
        }
    }
}

class PaymentService {
    public boolean isValidEmail(String email) {
        if(email == null || email.isEmpty()) {
            return false;
        }
        // Check if email contains '@' and '.'
        if(!email.contains("@") || !email.contains(".")) {
            return false;
        }

        return true;
    }

    public void processPayment(String email) {
        if(isValidEmail(email)) {
            System.out.println("Processing payment for user: " + email);
        } else {
            System.out.println("Invalid email provided.");
        }
    }
}

class MessageService {
    public boolean isValidEmail(String email) {
        if(email == null || email.isEmpty()) {
            return false;
        }
        // Check if email contains '@' and '.'
        if(!email.contains("@") || !email.contains(".")) {
            return false;
        }

        return true;
    }

    public void sendMessage(String email) {
        if(isValidEmail(email)) {
            System.out.println("Sending message to user: " + email);
        } else {
            System.out.println("Invalid email provided.");
        }
    }
}

// ****Good Example of code that follows the DRY principle******
class EmailValidator {
    public static boolean isValidEmail(String email) {
        if(email == null || email.isEmpty()) {
            return false;
        }
        // Check if email contains '@' and '.'
        if(!email.contains("@") || !email.contains(".")) {
            return false;
        }

        return true;
    }
}

class AuthServiceGood {
    public void authenticate(String email) {
        if(EmailValidator.isValidEmail(email)) {
            System.out.println("Successfully authenticated user: " + email);
        } else {
            System.out.println("Invalid email provided.");
        }
    }
}

class PaymentServiceGood {
    public void processPayment(String email) {
        if(EmailValidator.isValidEmail(email)) {
            System.out.println("Processing payment for user: " + email);
        } else {
            System.out.println("Invalid email provided.");
        }
    }
}

class MessageServiceGood {
    public void sendMessage(String email) {
        if(EmailValidator.isValidEmail(email)) {
            System.out.println("Sending message to user: " + email);
        } else {
            System.out.println("Invalid email provided.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Implementation of the services(Bad Example)
        AuthService authService = new AuthService();
        authService.authenticate("user@example.com");
        PaymentService paymentService = new PaymentService();
        paymentService.processPayment("user@example.com");
        MessageService messageService = new MessageService();
        messageService.sendMessage("user@example.com");

        // Implementation of the services(Good Example)
        AuthServiceGood authServiceGood = new AuthServiceGood();
        authServiceGood.authenticate("user@example.com");
        PaymentServiceGood paymentServiceGood = new PaymentServiceGood();
        paymentServiceGood.processPayment("user@example.com");
        MessageServiceGood messageServiceGood = new MessageServiceGood();
        messageServiceGood.sendMessage("user@example.com");
        
    }
}