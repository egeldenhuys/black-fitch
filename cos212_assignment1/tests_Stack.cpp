#include <iostream>
#include <sstream>

#include "../catch/catch.hpp"
#include "Stack.h"

string getOutput(Stack& list) {
	ostringstream output;
	output << list;
	return output.str();
}


SCENARIO("testing Stack::push() and Stack::operator<<") {
	GIVEN("an empty Stack object") {
		Stack stack;

		WHEN("printing empty list") {
			THEN("no crash") {
				REQUIRE(getOutput(stack) == "[]");
			}
		}

		WHEN("pushing positive elements (Including 0)") {

			stack.push(0);

			AND_WHEN("printing list with one element") {
				THEN("it works") {
					REQUIRE(getOutput(stack) == "[0]");
				}
			}

			stack.push(1);
			stack.push(2);
			stack.push(3);

			THEN("elements are added to front") {
				REQUIRE(getOutput(stack) == "[0,1,2,3]");
			}

			AND_WHEN("adding negative elements") {
				stack.push(-1);

				THEN("nothing happens") {
					REQUIRE(getOutput(stack) == "[0,1,2,3]");
				}
			}
		}
	}
}


SCENARIO("testing Stack::pop()") {
	GIVEN("a Stack object with some elements") {
		Stack list;
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4); // 1 2 3 4


		WHEN("pop() is called") {
			int result = list.pop(); // 4 3 2

			THEN("the last element is removed and its data returned") {
				REQUIRE(result == 4);
				REQUIRE(getOutput(list) == "[1,2,3]");
			}
		}
	}

	GIVEN("a Stack object with one element") {
		Stack list;
		list.push(1);

		WHEN("pop() is called") {
			int result = list.pop(); // 4 3 2

			THEN("the last element is removed and its data returned") {
				REQUIRE(result == 1);
				REQUIRE(getOutput(list) == "[]");

			}
		}
	}

	GIVEN("a Stack object with no elements") {
		Stack list;

		WHEN("pop() is called") {
			int result = list.pop(); // 4 3 2

			THEN("-1 is returned") {
				REQUIRE(result == -1);
				REQUIRE(getOutput(list) == "[]");
			}
		}
	}
}

SCENARIO("deleting a Stack object on the heap") {
	GIVEN("a Stack object with some elements") {
		Stack *list = new Stack();
		list->push(1);
		list->push(2);
		list->push(3);
		list->push(4); // 1 2 3 4

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

SCENARIO("testing Stack assignment operator") {
	GIVEN("a Stack Object with some elements and an empty Stack Object") {
		Stack listA;
		Stack listB;

		listA.push(1);
		listA.push(2);
		listA.push(3);
		listA.push(4); // 1 2 3 4

		WHEN("assigning list with some elements to empty list") {
			listB = listA;

			THEN("both lists contain the same elements and are not empty") {
				REQUIRE(getOutput(listA) == "[1,2,3,4]");
				REQUIRE(getOutput(listB) == "[1,2,3,4]");
			}

			AND_WHEN("one list is modified") {
				listA.pop();

				THEN("the other is not") {
					REQUIRE(getOutput(listA) == "[1,2,3]");
					REQUIRE(getOutput(listB) == "[1,2,3,4]");
				}
			}
		}

		WHEN("chain linking assignment operator") {
			Stack listC;

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
			Stack listC;

			listC = listB;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listB) == "[]");
				REQUIRE(getOutput(listC) == "[]");
			}
		}

		WHEN("assigning a list to itself") {
			Stack listC;

			listA = listA;

			THEN("nothing happens and no crash") {
				REQUIRE(getOutput(listA) == "[1,2,3,4]");
			}
		}
	}

	// NOTE: Possibly redandant test
	GIVEN("two Stack objects with some elements") {
		Stack listA;
		Stack listB;

		listA.push(1);
		listA.push(2);
		listA.push(3);
		listA.push(4); // 1 2 3 4

		listB.push(10);
		listB.push(20);
		listB.push(30);
		listB.push(40);
		listB.push(50); // [10,20,30,40,50]

		WHEN("assigning one list to the other") {
			listA = listB;

			THEN("the lists contain the same data and are not linked") {
				listA.pop();
				listA.push(69);

				REQUIRE(getOutput(listA) == "[10,20,30,40,69]");
				REQUIRE(getOutput(listB) == "[10,20,30,40,50]");
			}
		}
	}
}
