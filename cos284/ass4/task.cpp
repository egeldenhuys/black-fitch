#include "catch.hpp"

#include <sstream>
#include <iostream>
#include <inttypes.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

// https://stackoverflow.com/questions/955962/how-to-buffer-stdout-in-memory-and-write-it-from-a-dedicated-thread
// By Nate Kohl (https://stackoverflow.com/users/98654/nate-kohl)
#define MAX_LEN 1024
#define INIT_CAPTURE \
    char buffer[MAX_LEN+1] = {0};\
    int out_pipe[2];\
    int saved_stdout;\
    std::string recieved = "";

#define START_CAPTURE \
    memset(buffer,0,strlen(buffer));\
    buffer[MAX_LEN+1] = {0};\
    saved_stdout = dup(STDOUT_FILENO);\
    if( pipe(out_pipe) != 0 ) {\
      exit(1);\
    }\
    dup2(out_pipe[1], STDOUT_FILENO);\
    close(out_pipe[1]);

#define END_CAPTURE \
    fflush(stdout);\
    read(out_pipe[0], buffer, MAX_LEN);\
    dup2(saved_stdout, STDOUT_FILENO);\
    recieved = std::string(buffer);

extern "C" void strPrinter(char * formatZstr, ...);

using namespace std;

SCENARIO("Testing Single token operations") {
    cout << "Testing single tokens..." << endl;

    WHEN("## is encountered") {
        INIT_CAPTURE;

        START_CAPTURE;
        strPrinter("##");
        END_CAPTURE;

        THEN("It is replaced with #") {
            string expected = "#";
            REQUIRE(expected == recieved);
        }

    }

    WHEN("#i is encountered") {
        INIT_CAPTURE;

        START_CAPTURE;
        strPrinter("#i", 703);
        END_CAPTURE;

        THEN("It is replaced with a number string") {
            string expected = "703";
            REQUIRE(expected == recieved);
        }
    }

    WHEN("#s is encountered") {
        INIT_CAPTURE;

        START_CAPTURE;
        strPrinter("#s", "Mah String");
        END_CAPTURE;

        THEN("It is replaced with a string") {
            string expected = "Mah String";
            REQUIRE(expected == recieved);
        }
    }

    WHEN("#? is encountered") {
        INIT_CAPTURE;

        START_CAPTURE;
        strPrinter("#h");
        END_CAPTURE;

        THEN("It is replaced with _invalid_specifier_") {
            string expected = "_invalid_specifier_";
            REQUIRE(expected == recieved);
        }
    }
}

SCENARIO("Testing Multiple token operations") {

    WHEN("5 #s tokens are passed") {
        cout << "Testing 5 #i tokens..." << endl;
        INIT_CAPTURE;
        START_CAPTURE;
        strPrinter("#s #s #s #s #s", "1", "2", "3", "4", "5");
        END_CAPTURE;

        THEN("No crash") {
            string expected = "1 2 3 4 5";
            REQUIRE(expected == recieved);
        }
    }

    WHEN("6 #s tokens are passed") {
        cout << "Testing 5 #s tokens..." << endl;
        INIT_CAPTURE;
        START_CAPTURE;
        strPrinter("#s #s #s #s #s #s", "1", "2", "3", "4", "5", "6");
        END_CAPTURE;

        THEN("No crash") {
            string expected = "1 2 3 4 5 6";
            REQUIRE(expected == recieved);
        }
    }

    WHEN("5 #i tokens are passed") {
        cout << "Testing 5 #i tokens..." << endl;
        INIT_CAPTURE;
        START_CAPTURE;
        strPrinter("#i #i #i #i #i", 1, 2, 3, 4, 5);
        END_CAPTURE;

        THEN("No crash") {
            string expected = "1 2 3 4 5";
            REQUIRE(expected == recieved);
        }
    }

    WHEN("6 #i tokens are passed") {
        cout << "Testing 6 tokens..." << endl;

        INIT_CAPTURE;
        START_CAPTURE;
        strPrinter("#i #i #i #i #i #i", 1, 2, 3, 4, 5, 6);
        END_CAPTURE;

        THEN("No crash") {
            string expected = "1 2 3 4 5 6";
            REQUIRE(expected == recieved);
        }
    }

    WHEN("A combination of tokens are passed, THEN no crash") {
        INIT_CAPTURE;

        START_CAPTURE;
        strPrinter("##This #s mah #awe#some #i string #i yes more #i#s #i#i#i#i#i#i#i#i #s ## #?", "is", "_lolcat_", 7643, 555, 66567, "heuheuhe", 1, 2, 3, 4, 5, 6, 7, 8, "Still alive?");
        END_CAPTURE;

        THEN("No crash") {
            string expected = "#This is mah _invalid_specifier_we_lolcat_ome 7643 string 555 yes more 66567heuheuhe 12345678 Still alive? # _invalid_specifier_";
            REQUIRE(expected == recieved);
        }
    }
}
