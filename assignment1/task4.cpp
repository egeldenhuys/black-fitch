#include "../catch.hpp"

#include "Wizard.h"
#include "Hobbit.h"

void spellMap(Wizard &wiz, Spell spells[], int spellCount)
{
    std::cout << "SPELL MAP START" << std::endl;

    std::cout << "Local spells: " << std::endl;

    for (int i = 0; i < spellCount; i++) {
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
/*
This task implements the additional functions for the Hobbit class.
Implement the following functions:
*/

/*
• bool hasBeenSummoned(Wizard& w): sets whether a wizard has been summoned to a meeting or not. If a wizard
has lost the ability of casting 5 spells, a hobbit will summon the wizard to a meeting and will set the wizard’s
variable hasBeenSummoned to true. The function returns the value of the wizard’s variable hasBeenSummoned
variable.
*/
SCENARIO("hobbit::hasBeenSummoned()")
{
    GIVEN("a hobbit and wizard with 10 spells")
    {
        Hobbit hob;
        Wizard wiz;

        Spell spells[10];

        for (int i = 0; i < 10; i++)
        {
            spells[i] = Spell("SPELL_" + to_string(i), 10, 15);

            wiz.addSpell(spells[i]);
        }

        WHEN("wizard lossedSpells = 5")
        {

            for (int i = 0; i < 5; i++)
            {
                wiz.deleteSpell(spells[i].getName());
            }

            THEN("the wizard is summoned through the hobbit")
            {
                REQUIRE(wiz.getNumberOfLossedSpells() == 5);

                REQUIRE(wiz.getNumberOfSpells() == 5);
                REQUIRE(wiz.getHasBeenSummoned() == false);

                REQUIRE(hob.hasBeenSummoned(wiz) == true);
                REQUIRE(wiz.getHasBeenSummoned() == true);
            }
        }

        WHEN("wizard lossedSpells = 0")
        {
            THEN("nothing happens")
            {
                REQUIRE(wiz.getNumberOfLossedSpells() == 0);

                REQUIRE(wiz.getNumberOfSpells() == 10);
                REQUIRE(wiz.getHasBeenSummoned() == false);

                REQUIRE(hob.hasBeenSummoned(wiz) == false);
                REQUIRE(wiz.getHasBeenSummoned() == false);
            }
        }
    }
}

/*
• bool hasCompletedTraining(Wizard& w): sets whether a wizard has completed new training (changes a wiz-
ard’s variable hasCompletedTraining). When the wizard has completed the training (i.e. when this function
is called), the hobbit resets the wizard’s numberOfLossedSpells back to zero, the wizard’s hasBeenSummoned
to false and the wizard’s hasCompletedTraining to true. The function returns the value of the wizard’s
hasCompletedTraining variable.
*/

SCENARIO("Hobbit::hasCompletedTraining(Wizard& w)")
{
    GIVEN("a hobbit and wizard with lossedSpells = 5")
    {
        Hobbit hob;
        Wizard wiz;

        Spell spells[10];

        for (int i = 0; i < 10; i++)
        {
            spells[i] = Spell("SPELL_" + to_string(i), 10, 15);
            wiz.addSpell(spells[i]);
        }

        for (int i = 0; i < 5; i++)
        {
            wiz.deleteSpell(spells[i].getName());
        }

        WHEN("called")
        {
            bool returnVal = hob.hasCompletedTraining(wiz);

            THEN("values are updated")
            {
                REQUIRE(wiz.getNumberOfSpells() == 5);
                REQUIRE(wiz.getHasBeenSummoned() == false);
                REQUIRE(wiz.getHasCompletedTraining() == true);
                REQUIRE(returnVal == true);
            }
        }
    }
}

/*
• void dropWizardSpells(Wizard& w): the hobbit investigates whether a wizard should drop or remove any
spells. The hobbit goes through the wizard’s spells and if there is a spell with skillLevel of zero, it removes
that spell from the Wizard’s list of spells by calling the deleteSpell function of Wizard. After checking all the
spells, the hobbit calls its hasBeenSummoned function.
*/

SCENARIO("Hobbit::dropWizardSpells(Wizard& w)")
{
    GIVEN("a cranky hobbit and a wizard with some spells with skillLevel 0")
    {
        Hobbit hob;
        Wizard wiz;

        Spell spells[10];

        for (int i = 0; i < 10; i++)
        {
            spells[i] = Spell("SPELL_" + to_string(i), 10, (i % 2) * i);
            wiz.addSpell(spells[i]);
        }

        WHEN("called")
        {
            hob.dropWizardSpells(wiz);

            THEN("sets values and drop spells with skillLevel 0")
            {
                AND_THEN("calls hasBeenSummoned(wiz)")
                {
                    REQUIRE(wiz.getHasBeenSummoned() == true);
                }

                REQUIRE(wiz.getNumberOfLossedSpells() == 5);
                REQUIRE(wiz.getNumberOfSpells() == 5);

                // Check our spells against the wizard
                for (int i = 0; i < 10; i++)
                {
                    if (spells[i].getSkillLevel() == 0)
                    {
                        for (int j = 0; j < wiz.getMaxNumberOfSpells(); j++)
                        {
                            REQUIRE(spells[i].getName() != wiz.getSpell(i).getName());
                        }
                    }
                }
            }
        }
    }
}
