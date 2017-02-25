#include "utils.h"

string getOutput(Stack& list) {
	ostringstream output;
	output << list;
	return output.str();
}

string getOutput(Queue& list) {
	ostringstream output;
	output << list;
	return output.str();
}

string getOutput(CircularList& list) {
	ostringstream output;
	output << list;
	return output.str();
}
