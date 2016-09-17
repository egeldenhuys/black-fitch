#include "../catch.hpp"

#include "Exception.h"


/*
Task 1: Exception Class [5]

REQUIREMENTS
============

Class: Exception
- A member variable storing the reason for the exception as a string.
- A public function, what() , that returns the reason for the exception.
- A public constructor that takes in a string describing the reason for the exception and
initialises the member variable.

*/

TEST_CASE("exception constructor and what() function")
{
    Exception ex("MY MESSAGE");
    REQUIRE(ex.what() == "MY MESSAGE");
}
