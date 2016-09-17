#include "../catch.hpp"

#include "Exception.h"

TEST_CASE("exception constructor and what() function", "[task1]")
{
    Exception ex("MY MESSAGE");
    REQUIRE(ex.what() == "MY MESSAGE");
}
