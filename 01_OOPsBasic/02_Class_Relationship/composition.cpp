#include <bits/stdc++.h>
using namespace std;

class Room {
    public:
        string name;

        Room() {
            this -> name = "";
        }

        Room(string name) {
            this -> name = name;
        }
};

class House {
    private:
        // Composition: House OWNS these object
        Room livingRoom;
        Room kitchen;

    public:
        House() {
            livingRoom = Room("Living Room");
            kitchen = Room("Kitchen");
        }

        void showHouse() {
            cout << "Rooms: " << livingRoom.name << " and " << kitchen.name << endl;
        }
};

int main() {
    House house;
    house.showHouse();

    return 0;
}