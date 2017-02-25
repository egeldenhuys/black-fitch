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


SCENARIO("testing Queue::enqueue() and Queue::operator<<()") {
	/*
		Cases:
			- When null
			- When one
			- When many
			- When inserting negative
	*/

	GIVEN("an empty Queue object") {
		Queue list;

		WHEN("printing with one element") {
			list.enqueue(5);

			THEN("only the element is printed") {
				REQUIRE(getOutput(list) == "[5]");
			}
		}

		WHEN("Adding elements") {
			list.enqueue(1);
			list.enqueue(2);
			list.enqueue(3);
			list.enqueue(4);

			THEN("elements are added to the back")
			{
				REQUIRE(getOutput(list) == "[1,2,3,4]");
			}

			AND_WHEN("adding negative elements") {
				list.enqueue(-1);

				THEN("nothing happens") {
					REQUIRE(getOutput(list) == "[1,2,3,4]");
				}
			}
		}

		WHEN("printing empty list") {
			THEN("no crash") {
				REQUIRE(getOutput(list) == "[]");
			}
		}
	}
}

SCENARIO("testing Queue::dequeue()") {
	GIVEN("a Queue object with some elements") {
		Queue list;
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4); // 1 2 3 4

		WHEN("calling dequeue") {
			int result = list.dequeue();

			THEN("the first element is removed and returned") {
				REQUIRE(result == 1);
				REQUIRE("[2,3,4]");
			}
		}

		WHEN("calling dequeue() until empty") {
			THEN("elements are returned from the front in order") {
				REQUIRE(list.dequeue() == 1);
				REQUIRE(list.dequeue() == 2);
				REQUIRE(list.dequeue() == 3);
				REQUIRE(list.dequeue() == 4);

			}
		}

	}

	GIVEN("a Queue object with one element") {
		Queue list;
		list.enqueue(1);

		WHEN("calling dequeue") {
			int result = list.dequeue();

			THEN("the first element is removed and returned") {
				REQUIRE(result == 1);
				REQUIRE("[]");
			}
		}
	}

	GIVEN("a Queue object with no elements") {
		Queue list;

		WHEN("calling dequeue") {
			int result = list.dequeue();

			THEN("-1 is returned and no crash") {
				REQUIRE(result == -1);
				REQUIRE("[]");
			}
		}
	}
}

SCENARIO("deleting a Queue object on the heap") {
	GIVEN("a Queue object on the heap with some elements") {
		Queue *list = new Queue();

		list->enqueue(1);
		list->enqueue(2);
		list->enqueue(3);
		list->enqueue(4);

		WHEN("deleting") {
			delete list;
			list = NULL;

			THEN("No crash") {
				REQUIRE(1 == 1);
			}
		}
	}
}

SCENARIO("testing Queue assignment operator") {
	GIVEN("a Queue Object with some elements and an empty Queue Object") {
		Queue listA;
		Queue listB;

		listA.enqueue(1);
		listA.enqueue(2);
		listA.enqueue(3);
		listA.enqueue(4); // 1 2 3 4

		WHEN("assigning list with some elements to empty list") {
			listB = listA;

			THEN("both lists contain the same elements and are not empty") {
				REQUIRE(getOutput(listA) == "[1,2,3,4]");
				REQUIRE(getOutput(listB) == "[1,2,3,4]");
			}

			AND_WHEN("one list is modified") {
				listA.dequeue();

				THEN("the other is not") {
					REQUIRE(getOutput(listA) == "[2,3,4]");
					REQUIRE(getOutput(listB) == "[1,2,3,4]");
				}
			}
		}

		WHEN("chain linking assignment operator") {
			Queue listC;

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
			Queue listC;

			listC = listB;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listB) == "[]");
				REQUIRE(getOutput(listC) == "[]");
			}
		}

		WHEN("assigning a list to itself") {
			Queue listC;

			listA = listA;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listA) == "[1,2,3,4]");
			}
		}
	}
}

SCENARIO("testing Queue::isEmpty()") {

	GIVEN("a Queue object with one element") {
		Queue list;
		list.enqueue(1);

		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return false") {
				REQUIRE(result == false);
			}
		}
	}

	GIVEN("a Queue object with some elements") {
		Queue list;
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4); // 1 2 3 4

		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return false") {
				REQUIRE(result == false);
			}
		}
	}

	GIVEN("an empty Queue object") {
		Queue list;
		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return true") {
				REQUIRE(result == true);
			}
		}
	}

}

SCENARIO("testing Queue::front()") {

	GIVEN("A Queue with one element") {
		Queue list;
		list.enqueue(1);

		WHEN("calling front()") {
			int result = list.front();

			THEN("the front element is returned and list not modified") {
				REQUIRE(result == 1);
				REQUIRE(getOutput(list) == "[1]");
			}
		}
	}

	GIVEN("a Queue object with some elements") {
		Queue list;
		list.enqueue(1);
		list.enqueue(2);
		list.enqueue(3);
		list.enqueue(4); // 1 2 3 4

		WHEN("calling front") {
			int result = list.front();

			THEN("the first element is returned and list not modified") {
				REQUIRE(result == 1);
				REQUIRE(getOutput(list) == "[1,2,3,4]");
			}
		}
	}
}

SCENARIO("testing Queue::~Queue") {
	GIVEN("a Queue with no elements") {
		Queue *list = new Queue();

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a Queue with some elements") {
		Queue *list = new Queue();

		list->enqueue(1);
		list->enqueue(2);
		list->enqueue(3);
		list->enqueue(4);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a Queue with one element") {
		Queue *list = new Queue();

		list->enqueue(1);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}
}

TEST_CASE("testing complex operations on Queue object") {
	Queue list;

	list.enqueue(-5);
	REQUIRE(getOutput(list) == "[]");
	REQUIRE(list.isEmpty() == true);
	REQUIRE(list.front() == -1);

	list.enqueue(1);
	REQUIRE(getOutput(list) == "[1]");
	REQUIRE(list.isEmpty() == false);
	REQUIRE(list.front() == 1);

	list.enqueue(-69);
	REQUIRE(getOutput(list) == "[1]");

	list.enqueue(2);
	REQUIRE(getOutput(list) == "[1,2]");

	list.enqueue(3);
	REQUIRE(getOutput(list) == "[1,2,3]");

	list.enqueue(4);
	REQUIRE(getOutput(list) == "[1,2,3,4]");

	Queue copyList;
	copyList = list;

	REQUIRE(list.dequeue() == 1);
	REQUIRE(getOutput(list) == "[2,3,4]");

	REQUIRE(list.dequeue() == 2);
	REQUIRE(getOutput(list) == "[3,4]");
	REQUIRE(list.front() == 3);
	REQUIRE(list.dequeue() == 3);
	REQUIRE(getOutput(list) == "[4]");

	REQUIRE(list.dequeue() == 4);
	REQUIRE(getOutput(list) == "[]");
	REQUIRE(list.isEmpty() == true);
	REQUIRE(list.front() == -1);

	REQUIRE(getOutput(copyList) == "[1,2,3,4]");
}
