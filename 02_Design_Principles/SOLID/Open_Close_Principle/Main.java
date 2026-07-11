// GOOD EXAMPLE: Following Open/Closed Principle 
// Classes are OPEN for extension (can add new implementations)
// Classes are CLOSED for modification (don't need to change existing code)

interface TaxCalculator {
    double amountAfterTax(double amount);
}

class IndianTaxCalculator implements TaxCalculator {
    @Override
    public double amountAfterTax(double amount) {
        return amount * 1.18;
    }
}

class USATaxCalculator implements TaxCalculator {
    @Override
    public double amountAfterTax(double amount) {
        return amount * 1.07;
    }
}

class InvoiceService {
    private TaxCalculator taxCalculator;

    public InvoiceService(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void calculateInvoice(double amount) {
        double totalAmount = taxCalculator.amountAfterTax(amount);
        System.out.println("Total amount after tax: " + totalAmount);
    }
}

// BAD EXAMPLE: Violating Open/Closed Principle 
// Classes are CLOSED for extension (can't add new implementations without modifying)
// Classes are NOT closed for modification (must modify existing code to add new types)
// Uses if-else chains instead of polymorphism

class InvoiceServiceBad {
    public double calculateInvoice(double amount, String country) {
        // Every time a new country is added, we need to MODIFY this class
        // This is a VIOLATION of OCP - class is not closed for modification
        
        if (country.equals("USA")) {
            return amount * 1.07;
        } 
        else if (country.equals("INDIA")) {
            return amount * 1.18;
        }
        else {
            return amount;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // GOOD Example - Using Interface and Polymorphism
        TaxCalculator usTaxCalculator = new USATaxCalculator();
        InvoiceService invoiceService1 = new InvoiceService(usTaxCalculator);
        invoiceService1.calculateInvoice(1000.0);

        TaxCalculator indianTaxCalculator = new IndianTaxCalculator();
        InvoiceService invoiceService2 = new InvoiceService(indianTaxCalculator);
        invoiceService2.calculateInvoice(1000.0);

        // BAD Example - Using if-else chains
        InvoiceServiceBad invoiceServiceBad = new InvoiceServiceBad();
        System.out.println("USA Tax: $" + invoiceServiceBad.calculateInvoice(1000, "USA"));
        System.out.println("INDIA Tax: $" + invoiceServiceBad.calculateInvoice(1000, "INDIA"));
    }
}
