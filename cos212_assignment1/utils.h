#ifndef UTILS_H
#define UTILS_H

#include <iostream>
#include <sstream>

#include "Stack.h"
#include "CircularList.h"
#include "Queue.h"
#include "Deque.h"

string getOutput(Stack& list);
string getOutput(Queue& list);
string getOutput(CircularList& list);
string getOutput(Deque& list);

#endif
