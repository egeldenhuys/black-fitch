#include "../catch/catch.hpp"

#include <iostream>
#include <math.h>

#include "RowsColumns.h"

using namespace std;

string applyPadding(string s)
{
    int matrixSize = pow(ceil(sqrt(double(s.size()))), 2);
    
    int diff = matrixSize - s.size();
    string result = s;

    for (int i = 0; i < diff; i++)
    {
        result += " ";
    }

    return result;
}

TEST_CASE("RowColumns encode/decode")
{
    string text="Hello, World!..";

    string longText="Emily Elizabeth Dickinson was an American poet. Dickinson was born in Amherst, Massachusetts. Although part of a prominent family with strong ties to its community, Dickinson lived much of her life highly introverted.";
    string encoded;

    RowsColumns rc;
    encoded = rc.encode(text);
    REQUIRE(encoded == "Hoo!e,r.l l.lWd ");
    REQUIRE(rc.decode(encoded) == "Hello, World!.. ");

    bool thrown;
    string msg;
    string decoded = "";

    try {
      decoded = rc.decode(text);
    } catch (const char * e) {
        thrown = true;
        msg = e;
    }

    REQUIRE(thrown == true);
    REQUIRE(msg == "Incompatible text length");

    encoded = rc.encode(longText);
    REQUIRE(encoded == string("E atsstrnsiD fvmDn. tsttttimeeii  b,.  ") + \
    "rscu rlcADo  ofo kchtykmirMAfancihie iecnal mgon gdEnrk stai " + \
    "msoh.lsiiish ltmofl iocnnaopyiun y znas cur en h  a noAhgowsilei " + \
    "bw nmuhmi tirn eap hs ittyv t tsoweepnho,elr h eartae   dio ");
    REQUIRE(rc.decode(encoded) == applyPadding(longText));

    thrown = false;
    msg = "";
    decoded = "";

    try {
      decoded = rc.decode(longText);
    } catch (const char * e) {
      thrown = true;
      msg = e;
    }

    REQUIRE(thrown == true);
    REQUIRE(msg == "Incompatible text length");

}
