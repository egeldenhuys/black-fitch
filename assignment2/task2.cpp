#include "../catch/catch.hpp"

#include <iostream>
#include <math.h>

#include "RowsColumns.h"
#include "ZigZag.h"
#include "utils.h"

using namespace std;

TEST_CASE("RowColumns encode/decode")
{
<<<<<<< HEAD
=======
    /*
    Input and expected output has been provided by the University of Pretoria

    */
>>>>>>> ad886213648e0b7a88a7d3926fa0a2035acea80f
    string text="Hello, World!..";

    string longText="Emily Elizabeth Dickinson was an American poet. Dickinson was born in Amherst, Massachusetts. Although part of a prominent family with strong ties to its community, Dickinson lived much of her life highly introverted.";
    string encoded;

    RowsColumns rc;
    encoded = rc.encode(text);
    REQUIRE(encoded == "Hoo!e,r.l l.lWd ");
    REQUIRE(rc.decode(encoded) == "Hello, World!.. ");

<<<<<<< HEAD
    // Will need a function to catch cout from decode in order for less strict testing.
=======
>>>>>>> ad886213648e0b7a88a7d3926fa0a2035acea80f
    bool thrown;
    string msg;
    string decoded = "";

    try {
      decoded = rc.decode(text);
    } catch (const char * e) {
        thrown = true;
        msg = e;
    }

<<<<<<< HEAD
    CHECK(thrown == true);
    CHECK(msg == "Incompatible text length");
=======
    REQUIRE(thrown == true);
    REQUIRE(msg == "Incompatible text length");
>>>>>>> ad886213648e0b7a88a7d3926fa0a2035acea80f

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

<<<<<<< HEAD
    CHECK(thrown == true);
    CHECK(msg == "Incompatible text length");
=======
    REQUIRE(thrown == true);
    REQUIRE(msg == "Incompatible text length");
>>>>>>> ad886213648e0b7a88a7d3926fa0a2035acea80f

}

TEST_CASE("ZigZag encode/decode")
{
    string text="Hello, World!..";

    string longText="Emily Elizabeth Dickinson was an American poet. Dickinson was born in Amherst, Massachusetts. Although part of a prominent family with strong ties to its community, Dickinson lived much of her life highly introverted.";
    string encoded;


    ZigZag zz;
    encoded = zz.encode(text);
    REQUIRE(encoded == "Hoo!.r,el l. dWl");
    REQUIRE(zz.decode(encoded) == applyPadding(text));

    bool thrown = false;
    string msg = "";


    try {
      zz.decode(text);
    } catch (const char * e) {
      thrown = true;
      msg = e;
    }

<<<<<<< HEAD
    CHECK(thrown == true);
    CHECK(msg == "Incompatible text length");
=======
    REQUIRE(thrown == true);
    REQUIRE(msg == "Incompatible text length");
>>>>>>> ad886213648e0b7a88a7d3926fa0a2035acea80f

    encoded = zz.encode(longText);
    REQUIRE(encoded == "E atsstrnsiD fveemittttst .nDmii  b,.  rscu rthck ofo  oDAclykmirMAfancihiedg nogm lancei Enrk stai msoh. lfomtl hsiiisliocnnaopyiun y   h ne ruc sanza noAhgowsilei  nrit imhumn wbeap hs ittyv t  rle,ohnpeewosth eartae   dio ");
    REQUIRE(zz.decode(encoded) == applyPadding(longText));

    thrown = false;
    msg = "";

    try {
      zz.decode(longText);
    } catch (const char * e) {
        thrown = true;
        msg = e;
    }

<<<<<<< HEAD
    CHECK(thrown == true);
    CHECK(msg == "Incompatible text length");
=======
    REQUIRE(thrown == true);
    REQUIRE(msg == "Incompatible text length");
>>>>>>> ad886213648e0b7a88a7d3926fa0a2035acea80f
}
