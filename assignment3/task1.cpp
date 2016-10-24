#include "../catch.hpp"
#include "linkedList.h"
#include <algorithm>
#include <string>

template <class T>
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

  ll->remove(0);

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
