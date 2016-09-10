#include <sstream>

#include "../catch.hpp"

#include "Wizard.h"
#include "Spell.h"
#include "Hobbit.h"

using namespace std;

SCENARIO( "wizard minus operator (-)", "[wizard][task2]")
{
    GIVEN( "a wizard with spells")
    {
        const int MAX_SPELLS = 25;

        Wizard wiz;
        wiz.setMaxNumberOfSpells(MAX_SPELLS);

        Spell cloneSpells[10];
        for (int i = 0; i < 10; i++)
        {
            cloneSpells[i] = Spell("CLONE", 12, 10);
            wiz.addSpell(cloneSpells[i]);
        }

        Spell spells[5];
        for (int i = 0; i < 5; i++)
        {
            spells[i] = Spell("SPELL_" + to_string(i), 12, 10);
            wiz.addSpell(spells[i]);
        }

        WHEN( "a spell is removed with - that does not exist")
        {
            wiz - "does not exist";

            THEN( "nothing happens")
            {
                REQUIRE(wiz.getNumberOfSpells() == 15);
                REQUIRE(wiz.getSpell(0).getName() == "CLONE");

                for (int i = 0; i < 5; i++)
                {
                    REQUIRE(wiz.getSpell(i).getName() != spells[i].getName());
                }

            }
        }

        WHEN( "a spell is removed with -")
        {
            wiz - cloneSpells[0].getName();

            THEN( "all spells with same name is removed")
            {
                REQUIRE(wiz.getNumberOfSpells() == 5);

                for (int i = 0; i < MAX_SPELLS; i++)
                {
                    REQUIRE(wiz.getSpell(i).getName() != cloneSpells[0].getName());
                }

            }
        }
    }
}

SCENARIO( "wizard plus operator (+)", "[wizard][task2]")
{
    GIVEN( "a wizard")
    {
        Wizard wiz;

        WHEN( "a spell is added using +")
        {
            Spell tmp("SPELL", 10, 5);

            wiz + tmp;

            THEN ("it is added to spell array")
            {
                REQUIRE(wiz.getSpell(0).getName() == "SPELL");
                REQUIRE(wiz.getNumberOfSpells() == 1);
            }
        }

    }
}

SCENARIO("wizard comparison operators (< >)", "[wizard][task2]")
{
    GIVEN("two wizards with same max size")
    {
        int MAX_SPELLS = 20;

        Wizard wiz1;
        wiz1.setMaxNumberOfSpells(MAX_SPELLS);

        Wizard wiz2;
        wiz2.setMaxNumberOfSpells(MAX_SPELLS);

        WHEN("wizards have full spell array, and lhs has more higher skills than rhs")
        {
            Spell lhsSpells[MAX_SPELLS];
            Spell rhsSpells[MAX_SPELLS];

            for (int i = 0; i < MAX_SPELLS; i++) {
                lhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 10);
                wiz1.addSpell(lhsSpells[i]);

                rhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 5);
                wiz2.addSpell(rhsSpells[i]);
            }

            THEN( "lhs > rhs returns 1") {
                REQUIRE( (wiz1 > wiz2) == 1);

                AND_THEN ("rhs > lhs returns 0") {
                    REQUIRE( (wiz2 > wiz1) == 0);
                }
            }

            THEN( "lhs < rhs returns 0") {
                REQUIRE( (wiz1 < wiz2) == 0);

                AND_THEN ("rhs < lhs returns 1") {
                    REQUIRE( (wiz2 < wiz1) == 1);
                }
            }
        }

        WHEN("wizards have full spell array, and rhs has more higher skills than lhs")
        {
            Spell lhsSpells[MAX_SPELLS];
            Spell rhsSpells[MAX_SPELLS];

            for (int i = 0; i < MAX_SPELLS; i++) {
                lhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 4);
                wiz1.addSpell(lhsSpells[i]);

                rhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 5);
                wiz2.addSpell(rhsSpells[i]);
            }

            THEN( "lhs > rhs returns 0") {
                REQUIRE( (wiz1 > wiz2) == 0);

                AND_THEN ("rhs > lhs returns 1") {
                    REQUIRE( (wiz2 > wiz1) == 1);
                }
            }

            THEN( "lhs < rhs returns 1") {
                REQUIRE( (wiz1 < wiz2) == 1);

                AND_THEN ("rhs < lhs returns 0") {
                    REQUIRE( (wiz2 < wiz1) == 0);
                }
            }

        }

        WHEN( "lhs has more spells, and lhs has more higher skills" ) {
            Spell lhsSpells[20];
            Spell rhsSpells[10];

            for (int i = 0; i < 20; i++) {
                lhsSpells[i] = Spell("SPELL_" + to_string(i), 10, i + 1);
                wiz1.addSpell(lhsSpells[i]);
            }

            for (int i = 0; i < 10; i++) {
                rhsSpells[i] = Spell("SPELL_" + to_string(i), 10, i);
                wiz2.addSpell(rhsSpells[i]);
            }

            THEN( "lhs > rhs returns 1") {
                REQUIRE( (wiz1 > wiz2) == 1);

                AND_THEN ("rhs > lhs returns 0") {
                    REQUIRE( (wiz2 > wiz1) == 0);
                }
            }

            THEN( "lhs < rhs returns 0") {
                REQUIRE( (wiz1 < wiz2) == 0);

                AND_THEN ("rhs < lhs returns 1") {
                    REQUIRE( (wiz2 < wiz1) == 1);
                }
            }

        }

        WHEN( "lhs has less spells, and lhs has more higher skills" ) {
            Spell lhsSpells[3];
            Spell rhsSpells[15];

            for (int i = 0; i < 3; i++) {
                lhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 11);
                wiz1.addSpell(lhsSpells[i]);
            }

            for (int i = 0; i < 15; i++) {
                rhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 2);
                wiz2.addSpell(rhsSpells[i]);
            }

            THEN( "lhs > rhs returns 1") {
                REQUIRE( (wiz1 > wiz2) == 1);

                AND_THEN ("rhs > lhs returns 0") {
                    REQUIRE( (wiz2 > wiz1) == 0);
                }
            }

            THEN( "lhs < rhs returns 0") {
                REQUIRE( (wiz1 < wiz2) == 0);

                AND_THEN ("rhs < lhs returns 1") {
                    REQUIRE( (wiz2 < wiz1) == 1);
                }
            }
        }

        WHEN( "rhs has less spells, and lhs has more higher skills" ) {
            Spell lhsSpells[15];
            Spell rhsSpells[3];

            for (int i = 0; i < 15; i++) {
                lhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 4);
                wiz1.addSpell(lhsSpells[i]);
            }

            for (int i = 0; i < 3; i++) {
                rhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 3);
                wiz2.addSpell(rhsSpells[i]);
            }

            THEN( "lhs > rhs returns 1") {
                REQUIRE( (wiz1 > wiz2) == 1);

                AND_THEN ("rhs > lhs returns 0") {
                    REQUIRE( (wiz2 > wiz1) == 0);
                }
            }

            THEN( "lhs < rhs returns 0") {
                REQUIRE( (wiz1 < wiz2) == 0);

                AND_THEN ("rhs < lhs returns 1") {
                    REQUIRE( (wiz2 < wiz1) == 1);
                }
            }
        }
    }

    GIVEN( "two wizards with different inventory sizes")
    {
        Wizard wiz1;
        wiz1.setMaxNumberOfSpells(15);

        Wizard wiz2;
        wiz2.setMaxNumberOfSpells(30);

        WHEN( "lhs has more higher skills than rhs")
        {
            Spell lhsSpells[15];
            Spell rhsSpells[3];

            for (int i = 0; i < 15; i++) {
                lhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 4);
                wiz1.addSpell(lhsSpells[i]);
            }

            for (int i = 0; i < 3; i++) {
                rhsSpells[i] = Spell("SPELL_" + to_string(i), 10, 3);
                wiz2.addSpell(rhsSpells[i]);
            }

            THEN( "lhs > rhs returns 1" )
            {
                REQUIRE( (wiz1 > wiz2) == 1);

                AND_THEN ("rhs > lhs returns 0") {
                    REQUIRE( (wiz2 > wiz1) == 0);
                }
            }

            THEN( "lhs < rhs returns 0") {
                REQUIRE( (wiz1 < wiz2) == 0);

                AND_THEN ("rhs > lhs returns 1") {
                    REQUIRE( (wiz2 < wiz1) == 1);
                }
            }
        }
    }
}

TEST_CASE("Compare with only 1 matching spell")
{
    Wizard wizA;

    Spell spellsA[69];

    for (int i = 0; i < 69; i++)
    {
        spellsA[i] = Spell("SPELL_A_" + to_string(i), 10, i);
        wizA.addSpell(spellsA[i]);
    }

    Wizard wizB;

    Spell spellsB[69];

    for (int i = 0; i < 69; i++)
    {
        spellsB[i] = Spell("SPELL_B_" + to_string(i), 10, i * 2);
        wizB.addSpell(spellsB[i]);
    }

    Spell matchA = Spell("MATCH", 10, 15);
    Spell matchB = Spell("MATCH", 10, 10);

    wizA.addSpell(matchA);
    wizB.addSpell(matchB);

    REQUIRE((wizA > wizB) == 1);
    REQUIRE((wizA < wizB) == 0);

    REQUIRE((wizB > wizA) == 0);
    REQUIRE((wizB < wizA) == 1);

}
