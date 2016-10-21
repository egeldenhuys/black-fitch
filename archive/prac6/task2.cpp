#include "../catch.hpp"

#include "xAxis.h"
#include <iostream>
#include <sstream>

void captureDisplay(xAxis x, ostringstream &output)
{
        // http://stackoverflow.com/questions/4810516/c-redirecting-stdout

        // Redirect cout to our output stream
        output.str("");

        streambuf *oldCoutBuffer = cout.rdbuf();
        cout.rdbuf(output.rdbuf());
        cout << flush;

        // Capture output
        x.display();

        // Reset cout
        cout.rdbuf(oldCoutBuffer);

}

TEST_CASE("testing parameter constructor", "[task2]")
{
        string* labels = new string[3];
        labels[0] = "one";
        labels[1] = "two";
        labels[2] = "three";

        double* values = new double[3];
        values[0] = 2.3;
        values[1] = 1.2;
        values[2] = 2.2;

        xAxis x(3, labels, values);

        ostringstream result;
        result << "one\t2.3\ntwo\t1.2\nthree\t2.2\n";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}

TEST_CASE("testing change label function", "[task2]")
{
        string* labels = new string[3];
        labels[0] = "one";
        labels[1] = "two";
        labels[2] = "three";

        double* values = new double[3];
        values[0] = 2.3;
        values[1] = 1.2;
        values[2] = 2.2;

        xAxis x(3, labels, values);

        x.changeLabel("one", "oneChanged");
        x.changeLabel("two", "twoChanged");
        x.changeLabel("three", "threeChanged");

        ostringstream result;
        result << "oneChanged\t2.3\ntwoChanged\t1.2\nthreeChanged\t2.2\n";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}

TEST_CASE("testing increaseValueBy function", "[task2]")
{
        string* labels = new string[1];
        labels[0] = "one";

        double* values = new double[1];
        values[0] = 2.3;

        xAxis x(1, labels, values);

        x.increaseValueBy("one", 0.7);

        ostringstream result;
        result << "one\t3\n";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}

TEST_CASE("testing decreaseValueBy function", "[task2]")
{
        string* labels = new string[1];
        labels[0] = "one";

        double* values = new double[1];
        values[0] = 2.3;

        xAxis x(1, labels, values);

        x.decreaseValueBy("one", 0.3);

        ostringstream result;
        result << "one\t2\n";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}

TEST_CASE("testing extend function", "[task2]")
{
        string* labels = new string[1];
        labels[0] = "one";

        double* values = new double[1];
        values[0] = 2.3;

        xAxis x(1, labels, values);

        x.extend(9.9, "two");

        ostringstream result;
        result << "one\t2.3\ntwo\t9.9\n";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}

TEST_CASE("testing delete function", "[task2]")
{
        string* labels = new string[1];
        labels[0] = "one";

        double* values = new double[1];
        values[0] = 2.3;

        xAxis x(1, labels, values);

        x.deleteNode("one");

        ostringstream result;
        result << "";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}

TEST_CASE("testing delete function when deleting nothing", "[task2]")
{
        string* labels = new string[1];
        labels[0] = "one";

        double* values = new double[1];
        values[0] = 2.3;

        xAxis x(1, labels, values);

        x.deleteNode("two");

        ostringstream result;
        result << "one\t2.3\n";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}

TEST_CASE("testing shorten fucntion", "[task2]")
{
        string* labels = new string[1];
        labels[0] = "one";

        double* values = new double[1];
        values[0] = 2.3;

        xAxis x(1, labels, values);

        x.shorten();

        ostringstream result;
        result << "";

        ostringstream output;
        captureDisplay(x, output);

        REQUIRE(output.str() == result.str());
}
