#include <bits/stdc++.h>
using namespace std;

class Car {
    public:
        string model;

        Car(string model) {
            this -> model = model;
        }

        void drive() {
            cout << "Driving a " << model << endl;
        }
};

class Person {
    public:
        string name;
        // Association: Person has a car
        Car* car;

        Person(string name, Car* car) {
            this -> name = name;
            this -> car = car;
        }

        void goForDrive() {
            cout << name << " is going for a drive." << endl;
            car -> drive();
        }
};

int main() {
    Car car("Tesla");
    Person person("Alice", &car);

    person.goForDrive();


    return 0;
}