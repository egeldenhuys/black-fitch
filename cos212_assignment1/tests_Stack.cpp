#include <iostream>
#include <sstream>

#include "../catch/catch.hpp"
#include "Stack.h"
#include "utils.h"

SCENARIO("testing Stack::push() and Stack::operator<<") {
	GIVEN("an empty Stack object") {
		Stack stack;

		WHEN("printing empty list") {
			THEN("no crash") {
				REQUIRE(getOutput(stack) == "[]");
			}
		}

		WHEN("pushing positive elements (Excluding 0)") {

			stack.push(5);

			AND_WHEN("printing list with one element") {
				THEN("it works") {
					REQUIRE(getOutput(stack) == "[5]");
				}
			}

			stack.push(1);
			stack.push(2);
			stack.push(3);

			THEN("elements are added to front") {
				REQUIRE(getOutput(stack) == "[3,2,1,5]");
			}

			AND_WHEN("adding negative elements") {
				stack.push(-1);

				THEN("nothing happens") {
					REQUIRE(getOutput(stack) == "[3,2,1,5]");
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
		list.push(4); // 4 3 2 1

		WHEN("pop() is called") {
			int result = list.pop(); // 4 3 2

			THEN("the last element is removed and its data returned") {
				REQUIRE(result == 4);
				REQUIRE(getOutput(list) == "[3,2,1]");
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
			int result = list.pop();

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
				REQUIRE(getOutput(listA) == "[4,3,2,1]");
				REQUIRE(getOutput(listB) == "[4,3,2,1]");
			}

			AND_WHEN("one list is modified") {
				listA.pop();

				THEN("the other is not") {
					REQUIRE(getOutput(listA) == "[3,2,1]");
					REQUIRE(getOutput(listB) == "[4,3,2,1]");
				}
			}
		}

		WHEN("chain linking assignment operator") {
			Stack listC;

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
				REQUIRE(getOutput(listA) == "[4,3,2,1]");
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

				REQUIRE(getOutput(listA) == "[69,40,30,20,10]");
				REQUIRE(getOutput(listB) == "[50,40,30,20,10]");
			}
		}
	}
}

SCENARIO("testing stack::peek()") {
	GIVEN("an empty Stack object") {
		Stack list;

		WHEN("calling peek()") {
			int result = list.peek();

			THEN("returns -1 and no crash") {
				REQUIRE(result == -1);
			}
		}
	}

	GIVEN("a Stack object with one element") {
		Stack list;
		list.push(1);

		WHEN("calling peek()") {
			bool result = list.peek();

			THEN("the top element is returned") {
				REQUIRE(result == 1);
				REQUIRE(getOutput(list) == "[1]");
			}
		}
	}

	GIVEN("a Stack object with some elements") {
		Stack list;
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4); // 4 3 2 1

		WHEN("calling peek()") {
			bool result = list.peek();

			THEN("first element is returned") {
				REQUIRE(result == 1);
				REQUIRE(getOutput(list) == "[4,3,2,1]");
			}
		}
	}


}

SCENARIO("testing Stack::isEmpty()") {

	GIVEN("a Stack object with one element") {
		Stack list;
		list.push(1);

		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return false") {
				REQUIRE(result == false);
			}
		}
	}

	GIVEN("a Stack object with some elements") {
		Stack list;
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4);

		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return false") {
				REQUIRE(result == false);
			}
		}
	}

	GIVEN("an empty stack object") {
		Stack list;
		WHEN("calling isEmpty()") {
			bool result = list.isEmpty();

			THEN("return true") {
				REQUIRE(result == true);
			}
		}
	}
}

SCENARIO("testing Stack::~Stack") {
	GIVEN("a Stack with no elements") {
		Stack *list = new Stack();

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a Stack with some elements") {
		Stack *list = new Stack();

		list->push(1);
		list->push(2);
		list->push(3);
		list->push(4);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}

	GIVEN("a Stack with one element") {
		Stack *list = new Stack();

		list->push(1);

		WHEN("deleting list") {
			delete list;

			THEN("no crash") {
				REQUIRE(1 == 1);
			}
		}
	}
}
