#include "../catch.hpp"
#include "linkedList.h"
#include <algorithm>
#include <string>

template <class T>

/** This does not capture printf **/
void captureDisplay(LinkedList<T> *ll, ostringstream &output) {
  // http://stackoverflow.com/questions/4810516/c-redirecting-stdout

  // Redirect cout to our output stream
  output.str("");

  streambuf *oldCoutBuffer = cout.rdbuf();
  cout.rdbuf(output.rdbuf());
  cout << flush;

  // Capture output
  cout << *ll;

  // Reset cout
  cout.rdbuf(oldCoutBuffer);
}

TEST_CASE("testing linkedList<int> insert", "[task1]") {
  LinkedList<int> *ll = new LinkedList<int>();
  ll->insert(0, 1);
  ll->insert(0, 2);
  ll->insert(0, 3);

  ostringstream result;
  result << "[3,2,1]";

  ostringstream output;
  captureDisplay(ll, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> remove index 0", "[task1]") {
  LinkedList<int> *ll = new LinkedList<int>();
  ll->insert(0, 1);
  ll->insert(0, 2);
  ll->insert(0, 3);

  int data = ll->remove(0);

  REQUIRE(data == 3);

  ostringstream result;
  result << "[2,1]";

  ostringstream output;
  captureDisplay(ll, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> remove all", "[task1]") {
  LinkedList<int> *ll = new LinkedList<int>();
  ll->insert(0, 1);
  ll->insert(0, 2);
  ll->insert(0, 3);

  ll->remove(0);
  ll->remove(0);
  ll->remove(0);

  ostringstream result;
  result << "[]";

  ostringstream output;
  captureDisplay(ll, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> remove invalid index from non empty list",
          "[task1]") {
  LinkedList<int> *ll = new LinkedList<int>();
  ll->insert(0, 1);
  ll->insert(0, 2);
  ll->insert(0, 3);

  string result = "invalid";
  string thrownResult = "";
  bool thrown = false;
  bool found = false;

  try {
    ll->remove(5);

  } catch (const char *s) {
    thrownResult = string(s);
    std::transform(thrownResult.begin(), thrownResult.end(),
                   thrownResult.begin(), ::tolower);
    thrown = true;
  }

  REQUIRE(thrown == true);

  if (thrownResult.find(result) != std::string::npos) {
    found = true;
  }

  REQUIRE(found == true);
}

TEST_CASE("testing linkedList<int> remove invalid index from empty list",
          "[task1]") {
  LinkedList<int> *ll = new LinkedList<int>();

  string result = "empty";
  string thrownResult = "";
  bool thrown = false;
  bool found = false;

  try {
    ll->remove(5);

  } catch (const char *s) {
    thrown = true;
    thrownResult = string(s);
    std::transform(thrownResult.begin(), thrownResult.end(),
                   thrownResult.begin(), ::tolower);
  }

  REQUIRE(thrown == true);

  if (thrownResult.find(result) != std::string::npos) {
    found = true;
  }

  REQUIRE(found == true);
}

TEST_CASE("testing linkedList<int> append two lists", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  LinkedList<int> *ll2 = new LinkedList<int>();

  ll2->insert(0, 4);
  ll2->insert(1, 5);
  ll2->insert(2, 6);

  LinkedList<int> ll3 = (*ll1 + *ll2);

  ostringstream result;
  result << "[1,2,3,4,5,6]";

  ostringstream output;
  captureDisplay(&ll3, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> assignment operator", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  LinkedList<int> *ll2 = new LinkedList<int>();
  ll2->insert(0, 9);

  ll2 = ll1;

  ostringstream result;
  result << "[1,2,3]";

  ostringstream output;
  captureDisplay(ll2, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> get leader", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  int data = ll1->getLeader()->data;

  REQUIRE(data == 1);
}

TEST_CASE("testing linkedList<int> clear function", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  ll1->clear();

  ostringstream result;
  result << "[]";

  ostringstream output;
  captureDisplay(ll1, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> [] operator", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  int data = (*ll1)[1];

  REQUIRE(data == 2);
}

TEST_CASE("testing linkedList<int> inbetween insert", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  ostringstream result;
  result << "[1,2,3]";

  ostringstream output;
  captureDisplay(ll1, output);

  REQUIRE(output.str() == result.str());

  ll1->insert(1, 99);

  result.str("");
  result << "[1,99,2,3]";

  output.str("");
  captureDisplay(ll1, output);

  REQUIRE(output.str() == result.str());

  ll1->insert(2, 88);

  result.str("");
  result << "[1,99,88,2,3]";

  output.str("");
  captureDisplay(ll1, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> copy constructor", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  LinkedList<int> *ll2 = new LinkedList<int>(*ll1);

  ostringstream result;
  result << "[1,2,3]";

  ostringstream output;
  captureDisplay(ll2, output);

  REQUIRE(output.str() == result.str());
}

TEST_CASE("testing linkedList<int> clone function", "[task1]") {
  LinkedList<int> *ll1 = new LinkedList<int>();

  ll1->insert(0, 1);
  ll1->insert(1, 2);
  ll1->insert(2, 3);

  LinkedList<int> *ll2 = ll1->clone();

  ostringstream result;
  result << "[1,2,3]";

  ostringstream output;
  captureDisplay(ll2, output);

  REQUIRE(output.str() == result.str());
}
