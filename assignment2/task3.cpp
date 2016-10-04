#include "../catch/catch.hpp"

#include <iostream>
#include <math.h>

#include "utils.h"

#include "Cipher.h"
#include "CipherPipeline.h"
#include "RowsColumns.h"
#include "ZigZag.h"
#include "Caesar.h"
#include "OneTimePad.h"

using namespace std;

TEST_CASE("CipherPipeline encode/decode")
{
    Caesar caes;
    caes.setShift(94);

    OneTimePad pad(9999);
    ZigZag zz;
    RowsColumns rc;

    string text="Hello, World!..";

    string longText="Emily Elizabeth Dickinson was an American poet. Dickinson was born in Amherst, Massachusetts. Although part of a prominent family with strong ties to its community, Dickinson lived much of her life highly introverted.";
    string encoded;

    CipherPipeline pipe;
    pipe += &caes;
    pipe += &pad;

    encoded = pipe.encode(text);
    REQUIRE(encoded == "pt0&iu&K0#rZkPy");
    REQUIRE(pipe.decode(encoded) == text);

    encoded = pipe.encode(longText);
    REQUIRE(encoded == "m|-&siK`*+gXP7Tu*;)O[?/\"~lC?~9EBbJZ]!wP1-.746BJQDK4y7Z4Mc'ObN<Q.Re7st?zL6!v.]L'R~L'L)<Z/@2K*\"F0\\3*i:H=$:l6rsmmx~_860h-e<iC(rrde<32(~v7_!fQ,`hQ?J^|=j=5QXqpS Vup-$!MHtr3L5kd>CJ-:NAD2y6)w=>5?GD\\T#S$^{}dIh[xNO=rE,lt49'H_y");
    REQUIRE(pipe.decode(encoded) == longText);

    pipe += rc + zz;
    encoded = pipe.encode(text);
    REQUIRE(encoded == "pt0&K&ui0#rZ yPk");
    REQUIRE(pipe.decode(encoded) == "Hello, World!..*");

    pipe = zz + pad;
    encoded = pipe.encode(text);
    REQUIRE(encoded == "q 4;)]3Z.1s%k(Dc");
    REQUIRE(pipe.decode(encoded) == applyPadding(text));

}
