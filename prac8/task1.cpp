#include "../catch/catch.hpp"
#include "./LinkedList.h"

#include <string>
#include <sstream>

/** This does not capture printf **/
string captureDisplay(LinkedList *ll) {
    // http://stackoverflow.com/questions/4810516/c-redirecting-stdout

    // Redirect cout to our output stream
    stringstream output("");

    streambuf *oldCoutBuffer = cout.rdbuf();
    cout.rdbuf(output.rdbuf());
    cout << flush;

    // Capture output
    ll->display();

    // Reset cout
    cout.rdbuf(oldCoutBuffer);
    return output.str();
}

SCENARIO("Testing adding two linked lists and sorting", "[task1]")
{
    GIVEN("a LinkedList populated with data")
    {
        string array1[] = {"z", "e", "g"};
        LinkedList* one = new LinkedList(3, array1);

        GIVEN("an unique LinkedList")
        {
            string array2[] = {"c", "b", "a"};
            LinkedList* two = new LinkedList(3, array2);

            THEN("combining the two lists should sort them correctly")
            {
                LinkedList* answer;
                answer = one->combine(two);

                REQUIRE(captureDisplay(answer) == "a b c e g z \n");
            }
        }

        GIVEN("a longer linked list")
        {
            string array2[] = {"c", "b", "a", "c", "b", "a"};
            LinkedList* two = new LinkedList(6, array2);

            THEN("combining the two lists should sort them correctly")
            {
                LinkedList* answer;
                answer = one->combine(two);

                REQUIRE(captureDisplay(answer) == "a a b b c c e g z \n");
            }
        }

        GIVEN("shorter linked list")
        {
            string array2[] = {"c"};
            LinkedList* two = new LinkedList(1, array2);

            THEN("combining the two lists should sort them correctly")
            {
                LinkedList* answer;
                answer = one->combine(two);

                REQUIRE(captureDisplay(answer) == "c e g z \n");
            }
        }

        GIVEN("an empty linked list")
        {
            LinkedList* two = new LinkedList();

            THEN("combining the two lists should return the first list sorted")
            {
                LinkedList* answer;
                answer = one->combine(two);

                REQUIRE(captureDisplay(answer) == "e g z \n");
            }
        }
    }

    GIVEN("two similar linked lists")
    {
        string array1[] = {"b", "b", "b"};
        LinkedList* one = new LinkedList(3, array1);

        string array2[] = {"b", "b", "a"};
        LinkedList* two = new LinkedList(3, array2);

        THEN("combining the two lists should sort them correctly")
        {
            LinkedList* answer;
            answer = one->combine(two);

            REQUIRE(captureDisplay(answer) == "a b b b b b \n");
        }
    }

    GIVEN("two empty linked lists")
    {
        LinkedList* one = new LinkedList();

        LinkedList* two = new LinkedList();

        THEN("combining the two lists should return NULL")
        {
            LinkedList* answer;
            answer = one->combine(two);

            REQUIRE(answer == NULL);
        }
    }
}
