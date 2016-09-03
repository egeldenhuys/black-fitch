#ifndef PRAC3_TESTS_H
#define PRAC3_TESTS_H

class Book;
class Library;

#include <sstream>

using namespace std;

bool runTests();

// Utility functions
void addBooksToLibrary(Book **books, Library &lib, int numBooks);
Book** createBooks(std::string prefix, int numBooks);
ostringstream createMockLibraryPrintOutput(Book **books, std::string libraryName, int numBooks, int librarySize);
ostringstream captureLibraryPrintOutput(Library &lib);
void deleteBooks(Book **books, int numBooks);
void popFromBookArray(Book** books, int index, int numBooks);

// Tests
bool BookContructor();
bool BookSetAndGetFunctions();
bool BookExtractionOperator();
bool LibraryAdd5BooksAndPrint();
bool LibraryCopyContructorCreatesDeepCopy();
bool LibraryDoesNotAddBookWhenFull();
bool LibraryRemoveBookOperator();
bool LibraryAssignmentOperatorCreatesDeepCopy();
bool LibraryPostIncrementIncreasesLibrarySize();
bool LibraryPreDecrementDecreasesLibrarySize();
bool LibraryPreDecrementRemovesLastBookIfFull();
bool LibraryGetBook();

/*
bool LibraryGetBookReturnsNullIfNotFound();
bool LibraryIsFullReturnsTrueIfFull();
bool LibraryIsFullReturnsFalseIfNotFull();
bool LibraryPrint();
bool LibraryPrintWhenEmpty();
*/
#endif
