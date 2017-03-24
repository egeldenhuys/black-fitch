/*
MIT License

Copyright (c) 2017 Evert Geldenhuys

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

#include <iostream>
#include <sstream>

#include "../catch/catch.hpp"
#include "CircularList.h"
#include "utils.h"

SCENARIO("testing CircularList::addToFront() and << Operator") {

	GIVEN("a CircularList object") {
		CircularList list;

		WHEN("printing empty list") {
			THEN("no crash") {
				REQUIRE(getOutput(list) == "[]");
			}
		}

		WHEN("adding positive elements (Including 0)") {

			list.addToFront(0);

			AND_WHEN("printing list with one element") {
				THEN("it works") {
					REQUIRE(getOutput(list) == "[0]");
				}
			}

			list.addToFront(1);
			list.addToFront(2);
			list.addToFront(3);

			THEN("elements are added to front") {
				REQUIRE(getOutput(list) == "[3,2,1,0]");
			}

			AND_WHEN("adding negative elements") {
				list.addToFront(-1);

				THEN("nothing happens") {
					REQUIRE(getOutput(list) == "[3,2,1,0]");
				}
			}
		}
	}
}

SCENARIO("testing CircularList::deleteFromBack()") {
	GIVEN("a CircularList object with many elements") {
		CircularList list;
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3);
		list.addToFront(4); // 4 3 2 1

		WHEN("deleteFromBack() is called") {
			int result = list.deleteFromBack(); // 4 3 2

			THEN("the last element is removed and its data returned") {
				REQUIRE(result == 1);
				REQUIRE(getOutput(list) == "[4,3,2]");

			}
		}

		WHEN("calling deleteFromBack() until empty") {
			THEN("elements are returned from the back in order") {
				REQUIRE(list.deleteFromBack() == 1);
				REQUIRE(list.deleteFromBack() == 2);
				REQUIRE(list.deleteFromBack() == 3);
				REQUIRE(list.deleteFromBack() == 4);

			}
		}
	}

	GIVEN("a CircularList object with one element") {
		CircularList list;
		list.addToFront(1);

		WHEN("deleteFromBack() is called") {
			int result = list.deleteFromBack(); // 4 3 2

			THEN("the last element is removed and its data returned") {
				REQUIRE(result == 1);
				REQUIRE(getOutput(list) == "[]");

			}
		}
	}

	GIVEN("a CircularList object with no elements") {
		CircularList list;

		WHEN("deleteFromBack() is called") {
			int result = list.deleteFromBack(); // 4 3 2

			THEN("-1 is returned") {
				REQUIRE(result == -1);
				REQUIRE(getOutput(list) == "[]");
			}
		}
	}
}

SCENARIO("deleting a CircularList object on the heap") {
	GIVEN("a CircularList object with some elements") {
		CircularList *list = new CircularList();
		list->addToFront(1);
		list->addToFront(2);
		list->addToFront(3);
		list->addToFront(4); // 4 3 2 1

		WHEN("deleting the list") {
			delete list;
			list = NULL;

			THEN("all memory should be freed and no crash") {
				// Place holder, not actually testing anything here.
				// Use valgrind

				REQUIRE(list == NULL);
			}
		}

	}
}

SCENARIO("testing CircularList assignment operator") {
	GIVEN("a CircularList list with some elements and an empty list") {
		CircularList listA;
		CircularList listB;

		listA.addToFront(1);
		listA.addToFront(2);
		listA.addToFront(3);
		listA.addToFront(4); // 4 3 2 1

		WHEN("assigning list with some elements to empty list") {
			listB = listA;

			THEN("both lists contain the same elements and are not empty") {
				REQUIRE(getOutput(listA) == "[4,3,2,1]");
				REQUIRE(getOutput(listB) == "[4,3,2,1]");
			}

			AND_WHEN("one list is modified") {
				listA.deleteFromBack();

				THEN("the other is not") {
					REQUIRE(getOutput(listA) == "[4,3,2]");
					REQUIRE(getOutput(listB) == "[4,3,2,1]");
				}
			}
		}

		WHEN("chain linking assignment operator") {
			CircularList listC;

			listC = listB = listA;

			THEN("all lists have the same data") {
				REQUIRE(getOutput(listA) == "[4,3,2,1]");
				REQUIRE(getOutput(listB) == "[4,3,2,1]");
				REQUIRE(getOutput(listC) == "[4,3,2,1]");
			}
		}

		WHEN("assigning an empty list to a non empty list") {
			listA = listB;

			THEN("both lists become empty") {
				REQUIRE(getOutput(listA) == "[]");
				REQUIRE(getOutput(listB) == "[]");
			}
		}

		WHEN("assigning an empty list to an empty list") {
			CircularList listC;

			listC = listB;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listB) == "[]");
				REQUIRE(getOutput(listC) == "[]");
			}
		}

		WHEN("assigning a list to itself") {
			CircularList listC;

			listA = listA;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listA) == "[4,3,2,1]");
			}
		}
	}

	// NOTE: Possibly redandant test
	GIVEN("two CircularList objects with some elements") {
		CircularList listA;
		CircularList listB;

		listA.addToFront(1);
		listA.addToFront(2);
		listA.addToFront(3);
		listA.addToFront(4); // 4 3 2 1

		listB.addToFront(10);
		listB.addToFront(20);
		listB.addToFront(30);
		listB.addToFront(40);
		listB.addToFront(50); // [50,40,30,20,10]

		WHEN("assigning one list to the other") {
			listA = listB;

			listA.deleteFromBack();
			listA.addToFront(69);

			THEN("the lists contain the same data and are not linked") {
				REQUIRE(getOutput(listA) == "[69,50,40,30,20]");
				REQUIRE(getOutput(listB) == "[50,40,30,20,10]");
			}
		}
	}
}

SCENARIO("testing CircularList::~CircularList") {
	GIVEN("a CircularList with no elements") {
		CircularList *list = new CircularList();

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a CircularList with some elements") {
		CircularList *list = new CircularList();

		list->addToFront(1);
		list->addToFront(2);
		list->addToFront(3);
		list->addToFront(4);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a CircularList with one element") {
		CircularList *list = new CircularList();

		list->addToFront(1);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}
}

TEST_CASE("testing complex operations on CircularList object") {
	CircularList list;

	list.addToFront(-5);
	REQUIRE(getOutput(list) == "[]");

	list.addToFront(1);
	REQUIRE(getOutput(list) == "[1]");

	list.addToFront(-69);
	REQUIRE(getOutput(list) == "[1]");

	list.addToFront(2);
	REQUIRE(getOutput(list) == "[2,1]");

	list.addToFront(3);
	REQUIRE(getOutput(list) == "[3,2,1]");

	list.addToFront(4);
	REQUIRE(getOutput(list) == "[4,3,2,1]");

	CircularList copyList;
	copyList = list;

	REQUIRE(list.deleteFromBack() == 1);
	REQUIRE(getOutput(list) == "[4,3,2]");

	REQUIRE(list.deleteFromBack() == 2);
	REQUIRE(getOutput(list) == "[4,3]");
	REQUIRE(list.deleteFromBack() == 3);
	REQUIRE(getOutput(list) == "[4]");

	REQUIRE(list.deleteFromBack() == 4);
	REQUIRE(getOutput(list) == "[]");

	REQUIRE(getOutput(copyList) == "[4,3,2,1]");
}
