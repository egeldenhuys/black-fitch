#include <iostream>
#include <sstream>
#include <iomanip>

#include "../catch.hpp"
#include "Spell.h"
using namespace std;

/*
Seems to be the code for task 3... however it has been posted under Task2
*/
TEST_CASE( "Example code")
{
	streambuf *tmpBuf = cout.rdbuf();
	ostringstream ss;
	cout.rdbuf(ss.rdbuf());

	Spell s1("dazzle", 5, 2);
	Spell s2("super dazzle", 6, 3);
	cout << (s1++);
	s2=(++s1);
	cout << s2;

	cout.rdbuf(tmpBuf);
}

TEST_CASE("Spell operator chaining")
{

}

SCENARIO("spell overloaded operators")
{
    GIVEN("some spells with different values")
    {
        Spell spells[3];

        for (int i = 0; i < 3; i++)
        {
            spells[i] = Spell("SPELL_" + to_string(i), i, i + 10);
        }

        WHEN("spellA = spellB = spellC")
        {
            spells[0] = spells[1] = spells[2];

            THEN("spellA, spellB and spellC, have the same values as spellC")
            {
                for (int i = 0; i < 3; i++)
                {
                    REQUIRE(spells[i].getName() == spells[2].getName());
                    REQUIRE(spells[i].getDifficultyLevel() == spells[2].getDifficultyLevel());
                    REQUIRE(spells[i].getSkillLevel() == spells[2].getSkillLevel());
                }

            }
        }

        WHEN("spell = spell")
        {
            spells[0] = spells[0];

            THEN("nothing happens, and no seg faults")
            {
                REQUIRE(spells[0].getName() == spells[0].getName());
                REQUIRE(spells[0].getDifficultyLevel() == spells[0].getDifficultyLevel());
                REQUIRE(spells[0].getSkillLevel() == spells[0].getSkillLevel());
            }
        }
    }
}

SCENARIO("spell skill increment operators")
{
    GIVEN("a spell with modified values")
    {
        Spell spell("SPELL", 10, 20);

        WHEN("++spell")
        {
            ++spell;
            THEN("increments the skill level of the specific spell")
            {
                REQUIRE(spell.getSkillLevel() == 21);
            }
        }

        WHEN("spell++")
        {
            spell++;
            THEN("increments the skillevel of the specific spell")
            {
                REQUIRE(spell.getSkillLevel() == 21);
            }
        }

        WHEN("--spell")
        {
            --spell;
            THEN("decrements the skill level of the specific spell")
            {
                REQUIRE(spell.getSkillLevel() == 19);
            }
        }

        WHEN("spell--")
        {
            spell--;
            THEN("decrements the skill level of the specific spell")
            {
                REQUIRE(spell.getSkillLevel() == 19);
            }
        }

        WHEN("spellA = ++spell")
        {
            Spell spellA("SPELL_A", 64, 5);
            spellA = ++spell;

            THEN("++spell returns the spell with the newly incremented skill level")
            {
                REQUIRE(spellA.getName() == "SPELL");
                REQUIRE(spellA.getDifficultyLevel() == 10);
                REQUIRE(spellA.getSkillLevel() == 21);
            }
        }

        WHEN("spellA = spell++")
        {
            Spell spellA("SPELL_A", 64, 5);
            spellA = spell++;

            THEN("spell++ returns the spell before incrementing the skill level")
            {
                REQUIRE(spellA.getName() == "SPELL");
                REQUIRE(spellA.getDifficultyLevel() == 10);
                REQUIRE(spellA.getSkillLevel() == 20);
            }
        }

        WHEN("spellA = --spell")
        {
            Spell spellA("SPELL_A", 64, 5);
            spellA = --spell;

            THEN("--spell returns the spell with the newly decremented skill level")
            {
                REQUIRE(spellA.getName() == "SPELL");
                REQUIRE(spellA.getDifficultyLevel() == 10);
                REQUIRE(spellA.getSkillLevel() == 19);
            }
        }

        WHEN("spellA = spell--")
        {
            Spell spellA("SPELL_A", 64, 5);
            spellA = spell--;

            THEN("spell-- returns the spell before decrementing the skill level")
            {
                REQUIRE(spellA.getName() == "SPELL");
                REQUIRE(spellA.getDifficultyLevel() == 10);
                REQUIRE(spellA.getSkillLevel() == 20);
            }
        }

        WHEN("spell -= x")
        {
            spell -= 5;
            THEN("the skill level of specific spell is reduced by x")
            {
                REQUIRE(spell.getSkillLevel() == 15);
            }
        }

        WHEN("spellA = spell -= x")
        {
            Spell spellA("SPELL_A", 64, 5);

            spellA = spell -= 5;

            THEN("spellA is the same as spell with skillLevel - x")
            {
                REQUIRE(spellA.getName() == "SPELL");
                REQUIRE(spellA.getDifficultyLevel() == 10);
                REQUIRE(spellA.getSkillLevel() == 15);
            }
        }

        WHEN("cout << spell")
        {
            ostringstream received;
            received << spell;

            THEN("outputs according to specs")
            {
                // Expected
                ostringstream expected;
                expected << right << setw(30) << "SPELL";
                expected << right << setw(5) << "10";
                expected << right << setw(5) << "20";

                REQUIRE(received.str() == expected.str());
            }
        }
    }
}

TEST_CASE("example functions, test for crash")
{

    streambuf *tmp = cout.rdbuf();
    ostringstream tmp2;
    cout.rdbuf(tmp2.rdbuf());

    Spell spell1 , spell2 ;

    spell2 = spell1 ;
    cout << spell2-- << endl;
    cout << ++spell2 << endl;
    spell1 -= 3;
    cout << spell2 << endl;

    cout.rdbuf(tmp);
}
