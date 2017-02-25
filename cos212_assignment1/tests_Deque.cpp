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
#include "utils.h"
#include "Deque.h"

SCENARIO("testing Deque::enqueueFront() and Deque::operator<<()") {

	GIVEN("an empty Deque object") {
		Deque list;

		WHEN("printing list with no elements") {
			THEN("no crash") {
				REQUIRE(getOutput(list) == "[]");
			}
		}
		WHEN("printing with one element") {
			list.enqueueFront(5);

			THEN("only the element is printed") {
				REQUIRE(getOutput(list) == "[5]");
			}
		}

		WHEN("Adding elements using enqueueFront()") {
			list.enqueueFront(1);
			list.enqueueFront(2);
			list.enqueueFront(3);
			list.enqueueFront(4);

			THEN("elements are added to the front in order")
			{
				REQUIRE(getOutput(list) == "[4,3,2,1]");
			}

			AND_WHEN("adding negative elements") {
				list.enqueueBack(-5);

				THEN("nothing happens") {
					REQUIRE(getOutput(list) == "[4,3,2,1]");
				}
			}
		}
	}
}

SCENARIO("testing Deque::enqueueBack()") {

	GIVEN("an empty Deque object") {
		Deque list;

		WHEN("Adding elements using enqueueBack()") {
			list.enqueueBack(1);
			list.enqueueBack(2);
			list.enqueueBack(3);
			list.enqueueBack(4);

			THEN("elements are added to the back in order")
			{
				REQUIRE(getOutput(list) == "[1,2,3,4]");
			}

			AND_WHEN("adding negative elements") {
				list.enqueueBack(-5);

				THEN("nothing happens") {
					REQUIRE(getOutput(list) == "[1,2,3,4]");
				}
			}
		}
	}
}

SCENARIO("testing Deque::dequeueFront()") {
	GIVEN("a Deque object with some elements") {
		Deque list;
		list.enqueueBack(1);
		list.enqueueBack(2);
		list.enqueueBack(3);
		list.enqueueBack(4); // 1 2 3 4

		WHEN("calling dequeue") {
			int result = list.dequeueFront();

			THEN("the first element is removed and returned") {
				REQUIRE(result == 1);
				REQUIRE("[2,3,4]");
			}
		}

		WHEN("calling dequeueFront() until empty") {
			THEN("elements are returned from the back in order") {
				REQUIRE(list.dequeueFront() == 1);
				REQUIRE(list.dequeueFront() == 2);
				REQUIRE(list.dequeueFront() == 3);
				REQUIRE(list.dequeueFront() == 4);

			}
		}

	}

	GIVEN("a Deque object with one element") {
		Deque list;
		list.enqueueBack(1);

		WHEN("calling dequeueFront") {
			int result = list.dequeueFront();

			THEN("the first element is removed and returned") {
				REQUIRE(result == 1);
				REQUIRE("[]");
			}
		}
	}

	GIVEN("a Deque object with no elements") {
		Deque list;

		WHEN("calling dequeue") {
			int result = list.dequeueFront();

			THEN("-1 is returned and no crash") {
				REQUIRE(result == -1);
				REQUIRE("[]");
			}
		}
	}
}

SCENARIO("testing Deque::dequeueBack()") {
	GIVEN("a Deque object with some elements") {
		Deque list;
		list.enqueueFront(1);
		list.enqueueFront(2);
		list.enqueueFront(3);
		list.enqueueFront(4); // 4 3 2 1

		WHEN("calling dequeueBack") {
			int result = list.dequeueBack();

			THEN("the last element is removed and returned") {
				REQUIRE(result == 1);
				REQUIRE("[4,3,2]");
			}

			// AND_WHEN("calling dequeueBack again") {
			// 	int result = list.dequeueBack();
			//
			// 	THEN("the last element is removed and returned") {
			// 		REQUIRE(result == 2);
			// 		REQUIRE("[4,3]");
			// 	}
			// }
		}

		WHEN("calling dequeueBack() until empty") {
			THEN("elements are returned from the back in order") {
				REQUIRE(list.dequeueBack() == 1);
				REQUIRE(list.dequeueBack() == 2);
				REQUIRE(list.dequeueBack() == 3);
				REQUIRE(list.dequeueBack() == 4);

			}
		}

	}

	GIVEN("a Deque object with one element") {
		Deque list;
		list.enqueueBack(1);

		WHEN("calling dequeueBack") {
			int result = list.dequeueBack();

			THEN("the first element is removed and returned") {
				REQUIRE(result == 1);
				REQUIRE("[]");
			}
		}
	}

	GIVEN("a Deque object with no elements") {
		Deque list;

		WHEN("calling dequeueBack") {
			int result = list.dequeueBack();

			THEN("-1 is returned and no crash") {
				REQUIRE(result == -1);
				REQUIRE("[]");
			}
		}
	}
}


SCENARIO("deleting a Deque object on the heap") {
	GIVEN("a Deque object on the heap with some elements") {
		Deque *list = new Deque();

		list->enqueueFront(1);
		list->enqueueFront(2);
		list->enqueueFront(3);
		list->enqueueFront(4);

		WHEN("deleting") {
			delete list;
			list = NULL;

			THEN("No crash") {
				REQUIRE(1 == 1);
			}
		}
	}
}

SCENARIO("testing Deque assignment operator") {
	GIVEN("a Deque Object with some elements and an empty Deque Object") {
		Deque listA;
		Deque listB;

		listA.enqueueBack(1);
		listA.enqueueBack(2);
		listA.enqueueBack(3);
		listA.enqueueBack(4); // 1 2 3 4

		WHEN("assigning list with some elements to empty list") {
			listB = listA;

			THEN("both lists contain the same elements and are not empty") {
				REQUIRE(getOutput(listA) == "[1,2,3,4]");
				REQUIRE(getOutput(listB) == "[1,2,3,4]");
			}

			AND_WHEN("one list is modified") {
				listA.dequeueFront();

				THEN("the other is not") {
					REQUIRE(getOutput(listA) == "[2,3,4]");
					REQUIRE(getOutput(listB) == "[1,2,3,4]");
				}
			}
		}

		WHEN("chain linking assignment operator") {
			Deque listC;

			listC = listB = listA;

			THEN("all lists have the same data") {
				REQUIRE(getOutput(listA) == "[1,2,3,4]");
				REQUIRE(getOutput(listB) == "[1,2,3,4]");
				REQUIRE(getOutput(listC) == "[1,2,3,4]");
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
			Deque listC;

			listC = listB;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listB) == "[]");
				REQUIRE(getOutput(listC) == "[]");
			}
		}

		WHEN("assigning a list to itself") {
			Deque listC;

			listA = listA;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listA) == "[1,2,3,4]");
			}
		}
	}
}

SCENARIO("testing Deque::isEmpty()") {

	GIVEN("a Deque object with one element") {
		Deque list;
		list.enqueueBack(1);

		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return false") {
				REQUIRE(result == false);
			}
		}
	}

	GIVEN("a Deque object with some elements") {
		Deque list;
		list.enqueueBack(1);
		list.enqueueBack(2);
		list.enqueueBack(3);
		list.enqueueBack(4); // 1 2 3 4

		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return false") {
				REQUIRE(result == false);
			}
		}
	}

	GIVEN("an empty Deque object") {
		Deque list;
		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return true") {
				REQUIRE(result == true);
			}
		}
	}

}

SCENARIO("testing Deque::~Deque") {
	GIVEN("a Deque with no elements") {
		Deque *list = new Deque();

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a Deque with some elements") {
		Deque *list = new Deque();

		list->enqueueBack(1);
		list->enqueueBack(2);
		list->enqueueBack(3);
		list->enqueueBack(4);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a Deque with one element") {
		Deque *list = new Deque();

		list->enqueueBack(1);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}
}

TEST_CASE("Testing complex operations on Deque object") {
	Deque list;

	list.enqueueFront(-5);
	REQUIRE(getOutput(list) == "[]");
	REQUIRE(list.isEmpty() == true);

	list.enqueueFront(1);
	REQUIRE(getOutput(list) == "[1]");
	REQUIRE(list.isEmpty() == false);

	list.enqueueBack(-69);
	REQUIRE(getOutput(list) == "[1]");

	list.enqueueBack(2);
	REQUIRE(getOutput(list) == "[1,2]");

	list.enqueueFront(3);
	REQUIRE(getOutput(list) == "[3,1,2]");

	list.enqueueBack(4);
	REQUIRE(getOutput(list) == "[3,1,2,4]");

	Deque copyList;
	copyList = list;

	REQUIRE(list.dequeueFront() == 3);
	REQUIRE(getOutput(list) == "[1,2,4]");

	REQUIRE(list.dequeueBack() == 4);
	REQUIRE(getOutput(list) == "[1,2]");

	REQUIRE(list.dequeueBack() == 2);
	REQUIRE(getOutput(list) == "[1]");

	REQUIRE(list.dequeueFront() == 1);
	REQUIRE(getOutput(list) == "[]");
	REQUIRE(list.isEmpty() == true);

	REQUIRE(getOutput(copyList) == "[3,1,2,4]");
}
