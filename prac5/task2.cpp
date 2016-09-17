#include "../catch.hpp"

#include <iostream>
#include <string>
#include <cstring>
#include <sstream>

#include "Exception.h"
#include "CharString.h"

using namespace std;

string getString(const CharString &cs)
{
    string output = "";

    //cout << "Extracting string: ";

    for (int i = 0; i < cs.length(); i++)
    {
        //cout << cs[i];
        output += cs[i];
    }

    //cout << endl;
    //cout << "also: " << output << endl;

    return output;
}

TEST_CASE("CharString constructor")
{
    char strB[7] = "World!";

    CharString cStringB(strB, 7);

    for (int i = 0; i < 6; i++)
    {
        REQUIRE(cStringB[i] == strB[i]);
    }
}

TEST_CASE("CharString operators: =, [ ]")
{
    char strB[7] = "World!";

    CharString cStringB(strB, 7);
    CharString cStringEmpty;

    cStringEmpty = cStringB;

    for (int i = 0; i < cStringEmpty.length(); i++)
    {
        REQUIRE(cStringEmpty[i] == cStringB[i]);
        REQUIRE(cStringEmpty[i] == strB[i]);
    }


}

TEST_CASE("CharString operators: -, -=")
{

    char strA[7] = "Hello ";
    char strB[7] = "World!";
    char strC[6] = "AAAAA";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringC(strC, 6);

    CharString cStringEmpty;
    CharString cStringEmpty2;

    SECTION("normal usage: -")
    {
        //cStringEmpty = (cStringA - 'l');
        cStringEmpty = cStringA - 'l';

        string result = getString(cStringEmpty);
        REQUIRE(result == "Heo ");
    }

    SECTION("normal usage: -=")
    {
        cStringA -= 'l';

        string result = getString(cStringA);
        REQUIRE(result == "Heo ");

    }

    SECTION("char not found: -")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringA - 'z';
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);

        // _’_ OR _'_ OR _`_ ?
        // Take keyboard '' (used for characters)
        REQUIRE(msg == "No character 'z' found!");

    }

    SECTION("char not found: -=")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringA -= 'z';
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "No character 'z' found!");

    }

    SECTION("lhs string is empty: -")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty2 = cStringEmpty - 'e';
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The string is empty!");
    }

    SECTION("lhs string is empty: -=")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty -= 'e';
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The string is empty!");
    }

    SECTION("result is empty: -")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringC - 'A';
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The result is an empty string!");
    }

    SECTION("result is empty: -=")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringC -= 'A';
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The result is an empty string!");
    }
}


TEST_CASE("CharString operators: +,  +=")
{
    char strA[7] = "Hello ";
    char strB[7] = "World!";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringEmpty;
    CharString cStringEmpty2;

    SECTION("normal usage: +")
    {
        cStringEmpty = cStringA + cStringB;

        string result = getString(cStringEmpty);
        REQUIRE(result == "Hello World!");
    }

    SECTION("normal usage: +=")
    {
        cStringA += cStringB;

        string result = getString(cStringA);
        REQUIRE(result == "Hello World!");
    }

    SECTION("rhs empty: +")
    {
        bool thrown = false;
        string msg = "";

        try {
            cStringEmpty = cStringA + cStringEmpty2;
        } catch (Exception e) {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The string is empty!");
    }

    SECTION("rhs empty: +=")
    {
        bool thrown = false;
        string msg = "";

        try {
            cStringA += cStringEmpty;
        } catch (Exception e) {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The string is empty!");
    }

}


TEST_CASE("CharString operators: *, *=")
{
    char strA[7] = "Hello ";
    char strB[7] = "World!";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringEmpty;
    CharString cStringEmpty2;

    SECTION("string are the same length: *")
    {
        cStringEmpty = cStringA * cStringB;
        string output = getString(cStringEmpty);
        REQUIRE(output == "HWeolrllod !");

    }

    SECTION("string are the same length: *=")
    {
        cStringA *= cStringB;
        string output = getString(cStringA);
        REQUIRE(output == "HWeolrllod !");
    }

    SECTION("strings are not the same length: *=")
    {

        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringA * cStringEmpty2;
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "Strings are unequal lengths!");

    }

    SECTION("strings are not the same length: *=")
    {

        bool thrown = false;
        string msg;

        try
        {
            cStringA *= cStringEmpty;
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "Strings are unequal lengths!");

    }

}

TEST_CASE("CharString operators: /, /=")
{
    char strA[7] = "Hello ";
    char strB[7] = "World!";
    char strD[4] = "zmg";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringD(strD, 4);

    CharString cStringEmpty;

    SECTION("Normal usage: /")
    {
        cStringEmpty = cStringB / cStringA;
        string output = getString(cStringEmpty);
        REQUIRE(output == "Wrd!");
    }

    SECTION("Normal usage: /=")
    {
        cStringB /= cStringA;
        string output = getString(cStringB);
        REQUIRE(output == "Wrd!");
    }

    SECTION("no characters from rhs found in lhs: /")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringA / cStringD;
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "No character found!");
    }

    SECTION("no characters from rhs found in lhs: /=")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringA /= cStringD;
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "No character found!");
    }


    SECTION("lhs string is empty")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringEmpty / cStringD;
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }


        REQUIRE(thrown == true);
        REQUIRE(msg == "The string is empty!");

    }

    SECTION("rhs string is empty")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringA / cStringEmpty;
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The string is empty!");

    }


    SECTION("result is empty")
    {
        bool thrown = false;
        string msg;

        char strAll[7] = "World!";
        CharString cStringAll(strAll, 7);

        try
        {
            cStringEmpty = cStringB / cStringAll;
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The result is an empty string!");

    }
}




TEST_CASE("CharString operator: ==")
{
    char strA[7] = "Hello ";
    char strB[7] = "World!";
    char strD[4] = "zmg";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringD(strD, 4);
    CharString cStringE(strA, 7);

    REQUIRE_FALSE(cStringA == cStringB);
    REQUIRE_FALSE(cStringA == cStringD);
    REQUIRE(cStringA == cStringE);

}

TEST_CASE("CharString operator: [ ]")
{
    char strB[7] = "World!";
    CharString cStringB(strB, 7);

    REQUIRE_THROWS(cStringB[69]);


    SECTION("invalid index. large")
    {
        bool thrown = false;
        string msg;

        try
        {
            char result = cStringB[69];
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "Index out of bounds!");
    }

    SECTION("invalid index. small")
    {
        bool thrown = false;
        string msg;

        try
        {
            char result = cStringB[-69];
        }
        catch (Exception e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "Index out of bounds!");
    }

}

TEST_CASE("CharString operator: <<")
{
    char strB[7] = "World!";
    CharString cStringB(strB, 7);

    ostringstream output;
    output << cStringB;

    REQUIRE(output.str() == "World!");
}

TEST_CASE("CharString operator chaining")
{
    char strA[7] = "Hello ";
    char strB[8] = "World!H";
    char strC[8] = " Lolwat";
    char strD[8] = "1234567";
    char strSplice[15] = "ABCDEFQWERTYUI";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 8);
    CharString cStringC(strC, 8);
    CharString cStringD(strD, 8);
    CharString cStringSplice(strSplice, 15);

    CharString cStringEmpty;

    SECTION("+ operator")
    {
        cStringEmpty = cStringA + cStringB + cStringC;
        REQUIRE(getString(cStringEmpty) == "Hello World!H Lolwat");
    }

    SECTION("/ operator")
    {
        // B = World!H / _Lolwat
        // = Wrd!H

        // A = Hello_ / Wrd!H
        // = ello_

        // OH NO!
        // "/" has order from left to right!

        cStringEmpty = cStringA / (cStringB / cStringC);
        REQUIRE(getString(cStringEmpty) == "ello ");
    }

    SECTION("* operator")
    {
        /*

        char strA[7] = "Hello ";
        char strB[8] = "World!H";
        char strC[8] = " Lolwat";
        char strD[8] = "1234567";
        char strSplice[15] = "ABCDEFQWERTYUI";
        */


        // C -> " Lolwat" * "1234567"
        //    = " 1L2o3l4w5a6t7"
        // splice -> "ABCDEFQWERTYUI" /
        //           " 1L2o3l4w5a6t7"
        //         = "A B1CLD2EoF3QlW4EwR5TaY6UtI7"

        cStringEmpty = cStringSplice * (cStringC * cStringD);
        REQUIRE(getString(cStringEmpty) == "A B1CLD2EoF3QlW4EwR5TaY6UtI7");
    }

}

/*
Try to break everything
*/
TEST_CASE("CharString integration tests")
{
    // Includes null terminator
    char myChars[51] = "This is a simple string. With a brown fox. Lolwat.";

    // Dont give a hoot how CharString handles the null. It asks for
    // Length of given array. Length is 51.

    CharString cString(myChars, 51);
    CHECK(cString.length() == 50); // We gave it 50 chars for all we care

    // Lets pop all e from our string
    cString -= 'e';

    // Extract the string
    ostringstream result;
    result << cString;

    REQUIRE(result.str() == "This is a simpl string. With a brown fox. Lolwat.");

    // Lets pop all "fox" chars

    char popper[4] = "fox";
    CharString cPopper(popper, 4);

    cString /= cPopper;

    result.str("");
    result << cString;

    REQUIRE(result.str() == "This is a simpl string. With a brwn . Llwat.");

    // append
    cString += cPopper;

    result.str("");
    result << cString;

    REQUIRE(result.str() == "This is a simpl string. With a brwn . Llwat.fox");

    bool thrown = false;
    try
    {
        cString -= 'y';
    }
    catch (Exception e)
    {
        REQUIRE(e.what() == "No character 'y' found!");
        thrown = true;
    }

    REQUIRE(thrown == true);



}
