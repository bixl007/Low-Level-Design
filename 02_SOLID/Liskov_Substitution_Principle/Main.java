// Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.

// Base class that defines the contract for sending notifications
class Notification {
    // Sends a notification via email by default
    public void sendNotification() {
        System.out.println("Email Sent");
    }
}

// Subclass that sends notifications via text/SMS
// Can be substituted anywhere the parent Notification class is used
class TextNotification extends Notification {
    @Override
    public void sendNotification() {
        System.out.println("Text Notification sent");
    }
}

// Subclass that sends notifications via WhatsApp
// Can be substituted anywhere the parent Notification class is used
class WpNotification extends Notification {
    @Override
    public void sendNotification() {
        System.out.println("Whatsapp notification sent");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a Notification object and send email notification
        Notification notification = new Notification();
        notification.sendNotification();

        // Substitute with TextNotification subclass. Works without issues due to LSP
        Notification textNotification = new TextNotification();
        textNotification.sendNotification();

        // Substitute with WpNotification subclass. Works without issues due to LSP
        Notification wpNotification = new WpNotification();
        wpNotification.sendNotification();
    }
}
