#include "../catch/catch.hpp"

#include <iostream>

#include "lcm.cpp"

using namespace std;

SCENARIO("Testing the functionality of the LCM", "[task3]")
{
    WHEN("tested with a simple case")
    {
        int numbers[] = {1, 2, 3};
        int answer = lcm(numbers, 3);
        REQUIRE(answer == 6);
    }

    WHEN("tested with many numbers")
    {
        int numbers[] = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int answer = lcm(numbers, 11);
        REQUIRE(answer == 27720);
    }

    WHEN("tested with just two numbers")
    {
        int numbers[] = {10, 11};
        int answer = lcm(numbers, 2);
        REQUIRE(answer == 110);
    }

    WHEN("tested with just one number")
    {
        int numbers[] = {5};
        int answer = lcm(numbers, 1);
        REQUIRE(answer == 5);
    }

    WHEN("tested with one number a 0")
    {
        int numbers[] = {1, 2, 3, 4, 0};
        int answer = lcm(numbers, 5);
        REQUIRE(answer == 0);
    }

    WHEN("tested with no numbers")
    {
        int answer = lcm(0, 0);
        REQUIRE(answer == 0);
    }
}
