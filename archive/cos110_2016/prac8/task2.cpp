#include "../catch/catch.hpp"
#include "./animals.h"

#include <sstream>
#include <iostream>

using namespace std;

/** This does not capture printf **/
string captureDisplay(Animal *animal) {
    // http://stackoverflow.com/questions/4810516/c-redirecting-stdout

    // Redirect cout to our output stream
    stringstream output("");

    streambuf *oldCoutBuffer = cout.rdbuf();
    cout.rdbuf(output.rdbuf());
    cout << flush;

    // Capture output
    animal->detectHuman();

    // Reset cout
    cout.rdbuf(oldCoutBuffer);
    return output.str();
}

string getAction(Animal *animal)
{
    stringstream output("");
    output << animal->action();
    return output.str();
}

SCENARIO("testing Animal class", "[task2]")
{
    GIVEN("instances of different animal types")
    {
        Animal* bird = new Bird("Bird");
        Animal* dog  = new Dog ("Dog");
        Animal* cat  = new Cat ("Cat");

        THEN("every Animal's 'detectHuman' should return the correct output") 
        {
            REQUIRE(captureDisplay(bird) == "Bird detected a human and is flying away\n");
            REQUIRE(captureDisplay(dog) == "Dog is looking for cuddles while wagging its tail\n");
            REQUIRE(captureDisplay(cat) == "Cat is looking for cuddles while purring loudly\n");
        }

        THEN("every Animal's 'action' should return the correct output") 
        {
            REQUIRE(getAction(bird) == "flying away");
            REQUIRE(getAction(dog)  == "wagging its tail");
            REQUIRE(getAction(cat)  == "purring loudly");
        }
    }
}

