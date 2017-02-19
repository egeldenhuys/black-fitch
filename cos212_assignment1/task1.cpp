#include "../catch/catch.hpp"
#include "CircularList.h"

/*
Functions:
friend ostream& operator<<(ostream& os,CircularList& c);
	- Returns [5,4,3,2]

CircularList& operator=(const CircularList& other);

CircularList();

~CircularList();

void addToFront(int elem);
	- Only takes positive integers

int deleteFromBack();
	- remove and return the element at the back of the list
	- If invalid return -1

*/

SCENARIO("testing CircularList addToFront() and ostream Operator") {

	GIVEN("a new CircularList object") {
		CircularList list = new CircularList;

		WHEN("adding a element") {
			// code

			THEN("element is added to front") {
				REQUIRE("Receieved" == "Expected");
			}
		}
	}
}
