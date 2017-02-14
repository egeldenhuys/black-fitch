#include "../catch.hpp"

#include <iostream>
#include <sstream>
#include <fstream>

#include "Caesar.h"
#include "OneTimePad.h"

using namespace std;

void verifyCaesarEncoding(string plainText, string cipherText, int shift)
{
    REQUIRE(plainText.size() == cipherText.size());
    char plain, cipher;

    for (uint i = 0; i < plainText.size(); i++)
    {
        cout << i << ": " << plainText[i] << " (" << int(plainText[i]) << ")" << " -> " \
        << cipherText[i] << " (" << int(cipherText[i]) << ")" << " [" << int(cipherText[i] - plainText[i]) << "]" \
        << endl;
    }
}

string captureCaesarEncode(Caesar &caesarObject, string plainText, ostringstream &out, string input = "")
{
    out.str("");

    // prepare the input if requested
    istringstream inputStream(input);

    // Redirect cin, so we can feed in a new number when requested
    streambuf *origIn = cin.rdbuf();
    cin.rdbuf(inputStream.rdbuf());

    // Redirect cout
    streambuf *origOut = cout.rdbuf();
    cout.rdbuf(out.rdbuf());

    // Set shift and capture output and give input
    string encode = caesarObject.encode(plainText);

    // Restore buffers
    cout.rdbuf(origOut);
    cin.rdbuf(origIn);

    return encode;
}

void captureCaesarSetShift(Caesar &caesarObject, int shift, ostringstream &out, string shiftIfError = "69")
{
    out.str("");

    // prepare the input if requested
    istringstream input(shiftIfError);

    // Redirect cin, so we can feed in a new number when requested
    streambuf *origIn = cin.rdbuf();
    cin.rdbuf(input.rdbuf());

    // Redirect cout
    streambuf *origOut = cout.rdbuf();
    cout.rdbuf(out.rdbuf());

    // Set shift and capture output and give input
    caesarObject.setShift(shift);

    // Restore buffers
    cout.rdbuf(origOut);
    cin.rdbuf(origIn);

}

SCENARIO("Caesar Encryption Class", "[task1][caesar]")
{
    GIVEN("a Ceaser encryption object")
    {
        Caesar caesarObject;

        WHEN("setting the shift amount")
        {

            ostringstream output;

            AND_WHEN("the the shift amount < 0")
            {
                captureCaesarSetShift(caesarObject, -1, output);

                THEN("message is printed")
                {
                    REQUIRE(output.str() == "Please provide a positive shift value\n");
                }
            }

            AND_WHEN("the the shift amount = 0")
            {
                captureCaesarSetShift(caesarObject, 0, output);

                THEN("message is printed")
                {
                    REQUIRE(output.str() == "Please provide a positive shift value\n");
                }
            }

            AND_WHEN("the the shift amount < 94")
            {
                captureCaesarSetShift(caesarObject, 95, output);

                THEN("message is printed")
                {
                    REQUIRE(output.str() == "Maximum shift is 94 for ASCII, please try again\n");
                }
            }

        }

    }
}

TEST_CASE("Caesar encoding/decoding", "[task1][caesar]")
{
    string newText = "Why, Hello.. There!";
    string newLongText = string("Robert Lee Frost (March 26, 1874 - ") + \
    "January 29, 1963) was an American poet. His work was initially published" + \
    "in England before it was published in America. He is highly regarded for" + \
    "his realistic depictions of rural life and his command of American" + \
    "colloquial speech.[2] His work frequently employed settings from rural" + \
    "life in New England in the early twentieth century, using them to examine" + \
    "complex social and philosophical themes.";


    string encoded;
    string decoded;

    Caesar caes;

    ostringstream output;
    encoded = captureCaesarEncode(caes, newText, output, "0 98 -3 3");

    REQUIRE(output.str() ==
    string("Please provide a positive shift value\n") + \
    "Please provide a positive shift value\n" + \
    "Maximum shift is 94 for ASCII, please try again\n" + \
    "Please provide a positive shift value\n");

    REQUIRE(encoded == "Zk|/#Khoor11#Wkhuh$");
    decoded = caes.decode(encoded);
    REQUIRE(decoded == newText);

    caes.setShift(15);

    encoded = caes.encode(newText);
    REQUIRE(encoded == "fw);/Wt{{~==/cwt\"t0");
    decoded = caes.decode(encoded);
    REQUIRE(decoded == newText);

    caes.setShift(66);

    encoded = caes.encode(newText);
    REQUIRE(encoded == ":K\\nb+HOORppb7KHUHc");
    decoded = caes.decode(encoded);
    REQUIRE(decoded == newText);

    caes.setShift(94);
    encoded = caes.encode(newText);
    REQUIRE(encoded == "Vgx+~Gdkkn--~Sgdqd ");
    decoded = caes.decode(encoded);
    REQUIRE(decoded == newText);

    encoded = caes.encode(newLongText);
    string expected = string("Qnadqs~Kdd~Eqnrs~'L`qbg~15+~0763~,~I`mt`qx~18+~0852(~v`r~`m~@l") + \
        "dqhb`m~onds-~Ghr~vnqj~v`r~hmhsh`kkx~otakhrgdchm~Dmfk`mc~ade" + \
        "nqd~hs~v`r~otakhrgdc~hm~@ldqhb`-~Gd~hr~ghfgkx~qdf`qcdc~enqg" + \
        "hr~qd`khrshb~cdohbshnmr~ne~qtq`k~khed~`mc~ghr~bnll`mc~ne~@l" + \
        "dqhb`mbnkknpth`k~roddbg-Z1\\~Ghr~vnqj~eqdptdmskx~dloknxdc~rd" + \
        "sshmfr~eqnl~qtq`kkhed~hm~Mdv~Dmfk`mc~hm~sgd~d`qkx~svdmshdsg~" + \
        "bdmstqx+~trhmf~sgdl~sn~dw`lhmdbnlokdw~rnbh`k~`mc~oghknrnoghb`k~sgdldr-";

    REQUIRE(encoded == expected);
    decoded = caes.decode(encoded);
    REQUIRE(decoded == newLongText);

}

TEST_CASE("oneTimePad encode/decode", "[task1][otp]")
{

    string newText = "Why, Hello.. There!";

    string newLongText = string("Robert Lee Frost (March 26, 1874 - ") + \
    "January 29, 1963) was an American poet. His work was initially published" + \
    "in England before it was published in America. He is highly regarded for" + \
    "his realistic depictions of rural life and his command of American" + \
    "colloquial speech.[2] His work frequently employed settings from rural" + \
    "life in New England in the early twentieth century, using them to examine" + \
    "complex social and philosophical themes.";

    string encoded;

    OneTimePad pad(1);
    encoded = pad.encode(newText);
    REQUIRE(encoded == "Nm\\D23v@Y\\9@MA;z*K4");
    REQUIRE(pad.decode(encoded) == newText);

    pad.setSeed(2);
    encoded = pad.encode(newText);

    REQUIRE(encoded == "l%kVZ0frI$G+\"?Au2wS");
    REQUIRE(pad.decode(encoded) == newText);

    pad.setSeed(9999);
    encoded = pad.encode(newText);

    REQUIRE(encoded == "!x>Fz3la.!5%kwU\\Y8G");
    REQUIRE(pad.decode(encoded) == newText);

    encoded = pad.encode(newLongText);

    string expected = string("{ ' m_'A'v'=^3`kfZsFe5%3C$X^=R{hc7mCp}c22)GWj") + \
    "Z=c:xd8Nd#RuiG\"|+U2J[yy'1JECJ%cS5(}.L k>6Y<E-A,^)\\]9Bk;DBn>t*c^m(?-H5" + \
    "&0`DZ=b?{r2aqP2t%6sECz\\S&XfR5PyK,\\A LF-q9viro?$v<&bIJ\\0jX>9BrNL;R7d=4(" + \
    ";!3C<QaF$L!i!bqJqb!U@;%D0p`<G MbR*R$e$zm^L`-:gy<nD.Z3}MLMIyd{])e+4 52</3z0O" +
    "R8kcSXP'<9ZZ<6cLll(L5NtPuU~NpK)c<4L)zM1c86ROen/kcJ_+F|Z=y\" |u;8B9uy!JUV4t\\." + \
    "?F{8Flxh9}n,B5Lq+w8ktak/Ti(.Fyu6b~P8{VQn.4'(W`<m-@v,U,OGQ0wz>(L2s~mC>>@G+ZnVa1#y,;OetXXjsI3Oy,";

    REQUIRE(encoded == expected);
    REQUIRE(pad.decode(encoded) == newLongText);

}

// Pointed out by @Paulo-W (https://github.com/Paulo-W)

TEST_CASE("OneTimePad inheritance test")
{
    OneTimePad pad(5);

    SubstitutionCipher *sub;
    Cipher *cipher;

    sub = &pad;
    cipher = &pad;

    sub->encode("");
    cipher->encode("");
}

TEST_CASE("Caesar inheritance test")
{
    Caesar caes;
    caes.setShift(69);

    SubstitutionCipher *sub;
    Cipher *cipher;

    sub = &caes;
    cipher = &caes;

    sub->encode("");
    cipher->encode("");

}
