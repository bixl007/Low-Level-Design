#include <iostream>
using namespace std;

// Abstract class
class Payment {
public:
    virtual void pay(double amount) = 0; // pure virtual function

    void printReceipt() {
        cout << "Payment successful. Receipt generated." << endl;
    }
};

// Derived class
class CreditCardPayment : public Payment {
public:
    void pay(double amount) override {
        cout << "Paid ₹" << amount << " using Credit Card." << endl;
    }
};

int main() {
    Payment* payment = new CreditCardPayment(); // abstraction
    payment->pay(5000);
    payment->printReceipt();

    delete payment;
    return 0;
}