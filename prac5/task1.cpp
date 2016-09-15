/*
Task 1: Exception Class [5]

REQUIREMENTS
============

Class: Exception
- A member variable storing the reason for the exception as a string.
- A public function, what() , that returns the reason for the exception.
- A public constructor that takes in a string describing the reason for the exception and
initialises the member variable.

Example usage:
try {
 throw Exception ("Test Exception");
} catch (Exception e) {
cout << e.what() << endl;
}
Output:
Test Exception
There should be no output statement in your code, and no newline characters in the strings
returned by what().
*/

#include "Exception.h"

TEST_CASE("exception constructor and what() function")
{
    Exception ex("MY MESSAGE");
    REQUIRE(ex.what() == "MY MESSAGE");
}
