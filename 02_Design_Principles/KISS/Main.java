// *********** Bad Practice of code that violates the KISS principle ***********
interface Operation {
    double calculate(double a, double b);
}

class Addition implements Operation {
    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}

class Substraction implements Operation {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}

class Multiplication implements Operation {
    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}

class Division implements Operation {
    @Override
    public double calculate(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }
}

class Calculator {
    public double performOperation(Operation operation, double a, double b) {
        return operation.calculate(a, b);
    }
}

// ******** Good Practice of code that follows the KISS principle ***********
class SimpleCalculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }
}

public class Main {
    public static void main(String[] args) {
        // Bad practice of code that violates the KISS principle
        Calculator calculator = new Calculator();
        double a = 10;
        double b = 5;

        System.out.println(calculator.performOperation(new Addition(), a, b));
        System.out.println(calculator.performOperation(new Substraction(), a, b));
        System.out.println(calculator.performOperation(new Multiplication(), a, b));
        System.out.println(calculator.performOperation(new Division(), a, b));

        // Implementation of code that follows the KISS principle
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        System.out.println(simpleCalculator.add(a, b));
        System.out.println(simpleCalculator.subtract(a, b));
        System.out.println(simpleCalculator.multiply(a, b));
        System.out.println(simpleCalculator.divide(a, b));

    }

}
