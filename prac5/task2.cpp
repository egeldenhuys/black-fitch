#define CATCH_CONFIG_MAIN
#include "../catch.hpp"

#include <iostream>
#include <string>
#include "Exception.h"
#include "CharString.h"

using namespace std;

string getString(const CharString &cs)
{
    string output = "";

    for (int i = 0; i < cs.length(); i++)
    {
        output.append(to_string(cs[i]));
    }

    return output;
}

TEST_CASE("CharString cstring constructor")
{
    // Create cString

    // Construct new CharString with the given cString
}


/*
The - operator :
This operator should return a CharString where every instance of the character on the
right hand side is removed from the string on the left hand side.
2If there are no instances of the character in the string, then an exception should be
thrown with the text "No character ’<character>’ found!" , where <character> is the char-
acter on the right hand side.
If the left hand string is empty, an exception should be thrown with the text "The string
is empty!".
If the result is an empty string, an exception should be thrown with the text "The result
is an empty string!".
Example:
"banana" - "a" should return "bnn"
*/

TEST_CASE("CharString operator -")
{

}

SCENARIO("CharString operations")
{
    GIVEN("some CharString objects")
    {
        char strA[6] = "Hello ";
        char strB[6] = "World!";
        char strEmpty[5] = "AAAAA";

        CharString cStringA(strA, 6);
        CharString cStringB(strB, 6);
        CharString cStringC();
        CharString cStringException();
        CharString cStringEmpty(strEmpty, 5);

        char lhs[5] = "ABCDE";
        char wat[5] = "abcde";
        char unEq[2] = "FF";

        CharString stringA(lhs, 5);
        CharString stringB(lhs, 5);
        CharString stringC(wat, 5);
        CharString stringD(unEq, 2);

        /*
        TESTS:
         - Add two CharStrings -> rhs is appended to lhs and returned as CharString
         - Add empty CharString -> exception thrown
        */
        WHEN("CharStringA + CharStringB, and +=")
        {
            AND_WHEN("CharStringA += CharStringB")
            {

            }
            AND_WHEN("rhs is not empty")
            {
                cStringC = cStringA + cStringB;
                cStringA += cStringB;

                THEN("rhs is appended to lhs")
                {
                    string contents = getString(cStringC);
                    string contents2 = getString(cStringA);

                    REQUIRE(contents == "Hello World!");
                    REQUIRE(contents2 == "Hello World!");

                }
            }

            AND_WHEN("rhs is empty")
            {
                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringA + cStringC;
                    cStringA += cStringC;
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "The string is empty!");
                }
            }
        } // End +

        WHEN("CharStringA - char, and -=")
        {

            AND_WHEN("char is found")
            {
                cStringC = cStringA - 'l';
                cStringA -= 'l';

                THEN("all occurences of character is removed from CharString")
                {
                    string contents = getString(cStringC);
                    REQUIRE(contents == "Heo ");

                    string contents2 = getString(cStringA);
                    REQUIRE(contents2 == "Heo ");
                }
            }

            AND_WHEN("char is not found")
            {

                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringA - 'z';
                    cString -= 'z';
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "No character ’z’ found!");
                }

            }

            AND_WHEN("lhs string is empty")
            {
                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringC - 'e';
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "The string is empty!");
                }

            }

            AND_WHEN("result is empty string")
            {
                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringEmpty - 'A';
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "The result is an empty string!");
                }
            }
        } // End -

        WHEN("CharStringA * CharStringB")
        {
            AND_WHEN("string are the same length")
            {
                cStringC = cStringA * cStringB;

                THEN("the result is the two strings interleaved")
                {

                    string output = getString(cStringC);
                    REQUIRE(output == "HWeolrlled !");
                }
            }

            AND_WHEN("strings are not the same length")
            {

                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringA * cStringEmpty;
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "No character ’z’ found!");
                }
            }
        } // End *

        WHEN("CharStringA / CharStringB")
        {

            AND_WHEN("characters from rhs exist in lhs")
            {
                cStringC = cStringB / cStringA;

                THEN("every instance from the rhs is removed in the lhs")
                {

                    string output = getString(cStringC);
                    REQUIRE(output == "Wrd!");
                }
            }

            AND_WHEN("no instanced from rhs found in lhs")
            {
                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringA / cStringEmpty;
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "No character found!");
                }
            }

            AND_WHEN("lhs string is empty")
            {
                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringC / cStringA;
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "The string is empty!");
                }
            }

            AND_WHEN("rhs string is empty")
            {
                bool thrown = false;
                string msg;

                try
                {
                    cStringException = cStringA / cStringC;
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "The string is empty!");
                }
            }


            AND_WHEN("result is empty")
            {
                bool thrown = false;
                string msg;
                char a[1] = 'A';
                CharString aString(a, 1);

                try
                {
                    cStringException = cStringA / aString;
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "The result is an empty string!");
                }
            }
        }

        WHEN("CharStringA == CharStringB")
        {

            AND_WHEN("strings are equal")
            {
                bool result = (stringA == stringB);

                THEN("return true")
                {
                    REQUIRE(result == true);
                }
            }

            AND_WHEN("strings are not equal")
            {
                bool result = (stringA == stringC);

                THEN("return false")
                {
                    REQUIRE(result == false);
                }
            }

            AND_WHEN("strings are not same length")
            {
                bool result = (stringA == stringD);

                THEN("return false")
                {
                    REQUIRE(result == false);
                }

            }
        } // End ==

        WHEN("CharString[]")
        {
            AND_WHEN("index valid")
            {
                // Hello .
                char result = cStringA[1];

                THEN("return char at index")
                {
                    REQUIRE(result == 'e');
                }
            }

            AND_WHEN("index not valid")
            {
                bool thrown = false;
                string msg;

                try
                {
                    char result = cStringA[69];
                }
                catch (Exception e)
                {
                    msg = e.what();
                    thrown = true;
                }

                THEN("exception is thrown")
                {
                    REQUIRE(thrown == true);
                    REQUIRE(msg == "Index out of bounds!");
                }
            }
        } // end []

        WHEN(" << CharString")
        {
            // stringC = abcde

            ostringstream output;
            output << stringC;

            THEN("result is the string representation of the CharString")
            {
                REQUIRE(output.str() == "abcde");
            }
        }

    }

}
