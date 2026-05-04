#include <iostream>
using namespace std;

class BankAccount {
private:
    double balance;   // hidden data - encapsulation => hiding the data from outside world

public:
    BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    void withdraw(double amount) {
        if (amount <= balance)
            balance -= amount;
        else
            cout << "Insufficient balance!" << endl;
    }

    double getBalance() const {
        return balance;
    }
};

int main() {
    BankAccount account(1000);

    account.deposit(500);
    account.withdraw(300);

    cout << "Current Balance: ₹" << account.getBalance() << endl;

    return 0;
}