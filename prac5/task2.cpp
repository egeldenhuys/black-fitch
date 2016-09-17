#define CATCH_CONFIG_MAIN
#include "../catch.hpp"

#include <iostream>
#include <string>
#include <cstring>

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
        REQUIRE(msg == "No character ’z’ found!");

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
        REQUIRE(msg == "No character ’z’ found!");

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


    SECTION("invalid index")
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
}

TEST_CASE("CharString operator: <<")
{
    char strB[7] = "World!";
    CharString cStringB(strB, 7);

    ostringstream output;
    output << cStringB;

    REQUIRE(output.str() == "World!");
}
