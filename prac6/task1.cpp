#include "../catch.hpp"

#include "cuboid.h"

bool double_equals(double a, double b, double epsilon = 0.001)
{
        return std::abs(a - b) < epsilon;
}

TEST_CASE("testing cuboid<int> volume function", "[task1]")
{
        Cuboid<int>* intc = new Cuboid<int>(2,4,5);
        REQUIRE(intc->getVolume() == 40);
}

TEST_CASE("testing cuboid<int> surface area function", "[task1]")
{
        Cuboid<int>* intc = new Cuboid<int>(2,4,5);
        REQUIRE(intc->getSurfaceArea() == 76);

}


TEST_CASE("testing cuboid<double> volume function", "[task1]")
{
        Cuboid<double>* doublec = new Cuboid<double>(2.2,4.5,5.2);
        // Floating point errors
        //REQUIRE(doublec->getVolume() == 51.48);
        REQUIRE(double_equals(doublec->getVolume(),51.48));
}


TEST_CASE("testing cuboid<double> surface area function", "[task1]")
{
        Cuboid<double>* doublec = new Cuboid<double>(2.2,4.5,5.2);
        REQUIRE(double_equals(doublec->getSurfaceArea(),89.48));

}
