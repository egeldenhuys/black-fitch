#include "../catch.hpp"

#include "CharString.h"

#include "EmptyException.h"
#include "EmptyResultException.h"
#include "OutOfBoundsException.h"
#include "UnequalSizeException.h"
#include "CharacterNotFoundException.h"

TEST_CASE("CharacterNotFoundException special")
{
    CharacterNotFoundException e;

    REQUIRE(e.what() == "No character '<character>' found!");
}

TEST_CASE("[task3] CharString operators: -, -=")
{

    char strA[7] = "Hello ";
    char strB[7] = "World!";
    char strC[6] = "AAAAA";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringC(strC, 6);

    CharString cStringEmpty;
    CharString cStringEmpty2;


    SECTION("char not found: -")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringA - 'z';;
        }
        catch (CharacterNotFoundException e)
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
        catch (CharacterNotFoundException e)
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
        catch (EmptyException e)
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
        catch (EmptyException e)
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
        catch (EmptyResultException e)
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
        catch (EmptyResultException e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The result is an empty string!");
    }
}


TEST_CASE("[task3] CharString operators: +,  +=")
{
    char strA[7] = "Hello ";
    char strB[7] = "World!";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringEmpty;
    CharString cStringEmpty2;


    SECTION("rhs empty: +")
    {
        bool thrown = false;
        string msg = "";

        try {
            cStringEmpty = cStringA + cStringEmpty2;
        } catch (EmptyException e) {
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
        } catch (EmptyException e) {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The string is empty!");
    }

}


TEST_CASE("[task3] CharString operators: *, *=")
{
    char strA[7] = "Hello ";
    char strB[7] = "World!";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringEmpty;
    CharString cStringEmpty2;


    SECTION("strings are not the same length: *=")
    {

        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringA * cStringEmpty2;
        }
        catch (UnequalSizeException e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "Strings are not the same size!");

    }

    SECTION("strings are not the same length: *=")
    {

        bool thrown = false;
        string msg;

        try
        {
            cStringA *= cStringEmpty;
        }
        catch (UnequalSizeException e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "Strings are not the same size!");

    }

}

TEST_CASE("[task3] CharString operators: /, /=")
{
    char strA[7] = "Hello ";
    char strB[7] = "World!";
    char strD[4] = "zmg";

    CharString cStringA(strA, 7);
    CharString cStringB(strB, 7);
    CharString cStringD(strD, 4);

    CharString cStringEmpty;


    SECTION("no characters from rhs found in lhs: /")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringA / cStringD;
        }
        catch (CharacterNotFoundException e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "No character '<character>' found!");
    }

    SECTION("no characters from rhs found in lhs: /=")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringA /= cStringD;
        }
        catch (CharacterNotFoundException e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "No character '<character>' found!");
    }


    SECTION("lhs string is empty")
    {
        bool thrown = false;
        string msg;

        try
        {
            cStringEmpty = cStringEmpty / cStringD;
        }
        catch (EmptyException e)
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
        catch (EmptyException e)
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
        catch (EmptyResultException e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "The result is an empty string!");

    }
}


TEST_CASE("[task3] CharString operator: [ ]")
{
    char strB[7] = "World!";
    CharString cStringB(strB, 7);

    SECTION("invalid index. large")
    {
        bool thrown = false;
        string msg;

        try
        {
            char result = cStringB[69];
        }
        catch (OutOfBoundsException e)
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
        catch (OutOfBoundsException e)
        {
            msg = e.what();
            thrown = true;
        }

        REQUIRE(thrown == true);
        REQUIRE(msg == "Index out of bounds!");
    }

}
