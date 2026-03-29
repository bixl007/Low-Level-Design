#include <bits/stdc++.h>
using namespace std;

class Printer {
    public:
        void print(string message) {
            cout << "Printing: " << message << endl;
        }
};

class  Document {
    public:
        string content;

        Document(string content) {
            this -> content = content;
        }

        void printDoc(Printer& printer) {
            printer.print(content);
        }
};

int main() {
    Document doc("Hello, World!");
    Printer printer;

    doc.printDoc(printer);

    return 0;
}