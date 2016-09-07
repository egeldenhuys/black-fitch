#define CATCH_CONFIG_MAIN

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

        WHEN( "a spell is removed with -")
        {
            wiz - spells[0].getName();

            THEN( "all spells with same name is removed")
            {
                REQUIRE(wiz.getNumberOfSpells() == 14);

                for (int i = 0; i < MAX_SPELLS; i++)
                {
                    REQUIRE(wiz.getSpell(i).getName() != spells[0].getName());
                }

            }
        }

        WHEN( "a duplicate spell is removed with -")
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

            AND_WHEN("the spell name alread exists")
            {
                tmp.setSkillLevel(69);
                wiz + tmp;

                THEN( "The spell is set to the higher level")
                {
                    REQUIRE(wiz.getNumberOfSpells() == 1);
                    REQUIRE(wiz.getSpell(0).getSkillLevel() == 69);
                }
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

TEST_CASE( "hobbit set and get functions" , "[hobbit][task1]")
{
    Hobbit hob;

    hob.setName("HOBBIT_NAME");
    REQUIRE( hob.getName() == "HOBBIT_NAME" );

}

TEST_CASE( "spell map", "[!hide]")
{

    // REQUIRES
    int spellCount = 25;
    Wizard wiz;
    Spell spells[spellCount];

    for (int i = 0; i < spellCount; i++)
    {
        spells[i] = Spell("SPELL_" + to_string(i), i, i);

        wiz.addSpell(spells[i]);
    }

    std::cout << "SPELL MAP START" << std::endl;

    std::cout << "Local spells: " << std::endl;

    for (int i = 0; i < wiz.getMaxNumberOfSpells(); i++) {
        std::cout << i << ": " << spells[i].getName() << ", diff: " \
        << spells[i].getDifficultyLevel() << ", skill: " \
        << spells[i].getSkillLevel() << " [" << &spells[i] << "]" << std::endl;
    }

    std::cout << "Wizard spells: " << std::endl;

    for (int i = 0; i < wiz.getMaxNumberOfSpells(); i++) {
        std::cout << i << ": " << wiz.getSpell(i).getName() << ", diff: " \
        << wiz.getSpell(i).getDifficultyLevel() << ", skill: " \
        << wiz.getSpell(i).getSkillLevel() << " [" << &wiz.getSpell(i) << "]" << std::endl;
    }

    std::cout << "SPELL MAP END" << std::endl;
}

TEST_CASE( "wizard default constructor", "[wizard][task1]")
{
    Wizard wiz;

    SECTION( "default values are set" ) {
        REQUIRE( wiz.getNumberOfSpells() == 0 );
        REQUIRE( wiz.getMaxNumberOfSpells() == 10 );
        REQUIRE( wiz.getAge() == 20 );
        REQUIRE( wiz.getNumberOfLossedSpells() == 0 );
    }

    SECTION( "initializes all spells to empty names") {
        for (int i = 0; i < wiz.getMaxNumberOfSpells(); i++) {

            Spell tmpSpell = wiz.getSpell(i);
            REQUIRE( tmpSpell.getName() == "" );
        }
    }

}

TEST_CASE( "wizard copy constructor", "[wizard][task1]")
{
    Wizard wiz1;
    wiz1.setAge(69);
    wiz1.setMaxNumberOfSpells(55);

    int spellCount = 25;
    Spell spells[spellCount];

    for (int i = 0; i < spellCount; i++)
    {
        spells[i] = Spell("SPELL_" + to_string(i), i, i);

        wiz1.addSpell(spells[i]);
    }

    Wizard wiz2(wiz1);

    REQUIRE( wiz2.getAge() == 69 );
    REQUIRE( wiz2.getMaxNumberOfSpells() == 55 );
    REQUIRE( wiz2.getNumberOfSpells() == 25 );

    for (int i = 0; i < 25; i++)
    {
        REQUIRE( wiz2.getSpell(i).getName() == spells[i].getName() );
        REQUIRE( wiz2.getSpell(i).getDifficultyLevel() == spells[i].getDifficultyLevel() );
        REQUIRE( wiz2.getSpell(i).getSkillLevel() == spells[i].getSkillLevel() );
    }


}
/*
void setMaxNumberOfSpells(int m);
int getMaxNumberOfSpells() const;
void setAge(int a);
int getAge() const;
*/
TEST_CASE( "wizard set and get functions", "[wizard][task1]")
{
    Wizard wiz;

    wiz.setAge(69);
    wiz.setMaxNumberOfSpells(72);

    REQUIRE( wiz.getAge() == 69 );
    REQUIRE( wiz.getMaxNumberOfSpells() == 72 );

}

/*
Spell(string name="Unknown", int difficultyLevel=10, int skillLevel = 5);
*/
TEST_CASE( "spell default constructor and get/set functions", "[spell][task1]" )
{
    Spell spell;

    SECTION( "get default values set by construtor" ) {
        REQUIRE( spell.getName() == "Unknown" );
        REQUIRE( spell.getDifficultyLevel() == 10 );
        REQUIRE( spell.getSkillLevel() == 5 );
    }

    SECTION( "set and get values using set and get functions" ) {
        spell.setName("NAME");
        REQUIRE( spell.getName() == "NAME" );

        spell.setDifficultyLevel(69);
        REQUIRE( spell.getDifficultyLevel() == 69 );

        spell.setSkillLevel(77);
        REQUIRE( spell.getSkillLevel() == 77 );
    }

    /*
    When setting the skillLevel, remember to check the bounds, i.e. it must be greater or equal
    to zero.
    */
    SECTION( "skillLevel must be greater or equal to zero") {
            spell.setSkillLevel(-1);
            REQUIRE( spell.getSkillLevel() == 5);

            spell.setSkillLevel(0);
            REQUIRE( spell.getSkillLevel() == 0);
    }


}
/*
addSpell(const Spell& s): function to add a Spell to spells. If the numberOfSpells has not reached the
maxNumberOfSpells yet, the Spell should be added to the first slot in spells where the Spell’s name is an
empty string. If the maxNumberOfSpells has been reached, the array should be resized and the new Spell
should be added to the end of the newly created array. Remember to u
*/
TEST_CASE( "adding spells to wizard using addSpell()", "[wizard][task1]")
{
    // Wizard
    Wizard wiz;
    int spellCount = 69;

    // Spells
    Spell spells[spellCount];

    for (int i = 0; i < spellCount; i++)
    {
        spells[i].setName("SPELL_" + to_string(i));
    }

    SECTION( "adding single spell to new wizard" ) {
        wiz.addSpell(spells[0]);
        Spell tmp;
        tmp = wiz.getSpell(0);

        REQUIRE( tmp.getName() == "SPELL_0" );
        REQUIRE( wiz.getNumberOfSpells() == 1);
    }

    SECTION( "adding multiple (9) spells to new wizard" ) {
        int spellsToAdd = 10;

        for (int i = 0; i < spellsToAdd; i++) {
            INFO( "Index = " << i << ", Adding spell: " << spells[i].getName());
            wiz.addSpell(spells[i]);

            Spell tmp = wiz.getSpell(i);
            REQUIRE( tmp.getName() == spells[i].getName() );
            REQUIRE( wiz.getNumberOfSpells() == i + 1 );
            REQUIRE( wiz.getMaxNumberOfSpells() == 10 );

        }
    }

    SECTION( "spells array resized when max reached" ) {

        int spellsToAdd = 25;

        for (int i = 0; i < spellsToAdd; i++) {
            INFO( "Index = " << i << ", Adding spell: " << spells[i].getName());
            wiz.addSpell(spells[i]);

            Spell tmp = wiz.getSpell(i);
            REQUIRE( tmp.getName() == spells[i].getName() );
            REQUIRE( wiz.getNumberOfSpells() == i + 1 );

            if (i > 10)
                REQUIRE( wiz.getMaxNumberOfSpells() == i + 1);
        }
    }
}

/*
deleteSpell(string name): function to remove a Spell from the array of spells. Remember to update the
value of numberOfSpells and to set the values of the deleted value in the array to the values specified for the
default constructor. In addition, remember to update numberOfLossedSpells.
*/
TEST_CASE( "deleting spells from a wizard" , "[assume_delete_does_not_consolidate][wizard][task1]")
{
    // Wizard
    Wizard wiz;
    int spellCount = 10;

    // Spells
    Spell spells[spellCount];

    for (int i = 0; i < spellCount; i++)
    {
        spells[i].setName("SPELL_" + to_string(i));
        spells[i].setSkillLevel((i + 1) * 2 ); // even
        spells[i].setDifficultyLevel(i + 1); // odd

        wiz.addSpell(spells[i]);
    }

    SECTION( "when deleting a spell, the array is not consolidated") {
        wiz.deleteSpell(spells[5].getName());
        REQUIRE(wiz.getSpell(5).getName() == "");

    }

    SECTION( "when spell is deleted, the spell is no longer in wizard array" ) {

        // Delete all spells, one by one and verify
        for (int i = 0; i < spellCount; i++) {
            wiz.deleteSpell(spells[i].getName());

            REQUIRE( wiz.getNumberOfSpells() == spellCount - i - 1);
            REQUIRE( wiz.getMaxNumberOfSpells() == 10);
            REQUIRE( wiz.getNumberOfLossedSpells() == i + 1);

            // Check if spell is in wizard
            for (int j = 0; j < spellCount; j++) {
                Spell tmp = wiz.getSpell(j);

                REQUIRE( tmp.getName() != spells[i].getName() );

                if (tmp.getName() == "") {
                    REQUIRE( tmp.getSkillLevel() == 5 );
                    REQUIRE( tmp.getDifficultyLevel() == 10 );
                }
            }

        }
    }

    SECTION( "when adding a spell it is added to first empty name slot") {
        // Delete some spells in middle
        // 2, 4, 5, 8

        wiz.deleteSpell(spells[2].getName());
        wiz.deleteSpell(spells[4].getName());
        wiz.deleteSpell(spells[5].getName());
        wiz.deleteSpell(spells[8].getName());

        // add spell, should be in index 2

        Spell tmp("A", 1, 2);
        wiz.addSpell(tmp);

        REQUIRE( wiz.getSpell(2).getName() == "A" );
        REQUIRE( wiz.getSpell(2).getDifficultyLevel() == 1 );
        REQUIRE( wiz.getSpell(2).getSkillLevel() == 2 );

        tmp = Spell("B", 3, 4);
        wiz.addSpell(tmp);

        REQUIRE( wiz.getSpell(4).getName() == "B" );
        REQUIRE( wiz.getSpell(4).getDifficultyLevel() == 3 );
        REQUIRE( wiz.getSpell(4).getSkillLevel() == 4 );
    }

    SECTION( "delete first spell" ) {
        wiz.deleteSpell(spells[0].getName());

        REQUIRE( wiz.getSpell(0).getName() == "" );
        REQUIRE( wiz.getSpell(0).getSkillLevel() == 5 );
        REQUIRE( wiz.getSpell(0).getDifficultyLevel() == 10 );

        REQUIRE( wiz.getNumberOfSpells() == spellCount - 1);
        REQUIRE( wiz.getNumberOfLossedSpells() == 1);

    }

    SECTION ("delete first spell twice") {
        wiz.deleteSpell(spells[0].getName());
        wiz.deleteSpell(spells[0].getName());

        REQUIRE( wiz.getSpell(0).getName() == "" );
        REQUIRE( wiz.getSpell(0).getSkillLevel() == 5 );
        REQUIRE( wiz.getSpell(0).getDifficultyLevel() == 10 );

        REQUIRE( wiz.getSpell(1).getName() == spells[1].getName() );
        REQUIRE( wiz.getSpell(1).getSkillLevel() == spells[1].getSkillLevel() );
        REQUIRE( wiz.getSpell(1).getDifficultyLevel() == spells[1].getDifficultyLevel() );

        REQUIRE( wiz.getNumberOfSpells() == spellCount - 1);
        REQUIRE( wiz.getNumberOfLossedSpells() == 1);

    }

    SECTION ("delete last spell") {
        wiz.deleteSpell(spells[spellCount - 1].getName());

        REQUIRE( wiz.getSpell(spellCount - 1).getName() == "" );
        REQUIRE( wiz.getSpell(spellCount - 1).getSkillLevel() == 5 );
        REQUIRE( wiz.getSpell(spellCount - 1).getDifficultyLevel() == 10 );

        REQUIRE( wiz.getNumberOfSpells() == spellCount - 1);
        REQUIRE( wiz.getNumberOfLossedSpells() == 1);
    }
}

TEST_CASE( "wizard setMaxNumberOfSpells()", "[assume_resize_only_called_at_init][wizard][task1]")
{
    // Wizard
    Wizard wiz;
    int spellCount = 50;

    // Spells
    Spell spells[spellCount];

    // Only add 25
    for (int i = 0; i < 25; i++)
    {
        spells[i] = Spell("SPELL_" + to_string(i), (i + 1) * 2, i + 1);

        wiz.addSpell(spells[i]);
    }

    SECTION( "when new > old, preserve old spells and increase size") {
        wiz.setMaxNumberOfSpells(50);

        REQUIRE( wiz.getMaxNumberOfSpells() == 50 );

        // Check old spells preserved
        for (int i = 0; i < 25; i++) {
            CAPTURE( i );
            CAPTURE( wiz.getSpell(i).getName());

            REQUIRE( wiz.getSpell(i).getName() == spells[i].getName() );
            REQUIRE( wiz.getSpell(i).getDifficultyLevel() == spells[i].getDifficultyLevel() );
            REQUIRE( wiz.getSpell(i).getSkillLevel() == spells[i].getSkillLevel() );
        }

        // Check new spells init
        for (int i = 25; i < 50; i++) {
            CAPTURE( i );
            CAPTURE( wiz.getSpell(i).getName());

            //REQUIRE( wiz.getSpell(i).getName() == "" );
            REQUIRE( wiz.getSpell(i).getDifficultyLevel() == 10 );
            REQUIRE( wiz.getSpell(i).getSkillLevel() == 5 );
        }
    }

    SECTION( "when new < old, spell array is resized") {
        wiz.setMaxNumberOfSpells(5);
        REQUIRE( wiz.getMaxNumberOfSpells() == 5);

    }
}
