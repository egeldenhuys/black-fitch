TEST_CASE( "wizard default constructor", "[wizard]")
{
    Wizard wiz;

    SECTION( "sets default values") {
        REQUIRE( wiz.getNumberOfSpells() == 0);
        REQUIRE( wiz.getMaxNumberOfSpells() == 10);
        REQUIRE( wiz.getAge() == 20);
        REQUIRE( wiz.getNumberOfLossedSpells() == 0);
    }

    SECTION( "initializes all spells to empty names") {
        for (int i = 0; i < wiz.getMaxNumberOfSpells(); i++)
    }

}
