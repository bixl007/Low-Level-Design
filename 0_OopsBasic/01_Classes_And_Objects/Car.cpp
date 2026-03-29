#include <iostream>
using namespace std;

class Car {
    public:
        // Attributes
        string manufacturer;
        string model;
        int year;

        // Constructor
        Car(string manufacturer, string model, int year) {
            this -> manufacturer = manufacturer;
            this -> model = model;
            this -> year = year;
        }

        // Methods
        void startEngine() {
            cout << "The " << year << " " << manufacturer << " " << model << " 's engine has started." << endl;
        }

        void displayInfo() {
            cout << "Car INFO: " << manufacturer << " " << model << " " << "(" << year << ")" << endl;
        }
};

int main() {
    Car car("Toyota", "Corolla", 2021);

    car.startEngine();
    car.displayInfo();

    return 0;
}