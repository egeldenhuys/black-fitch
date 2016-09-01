#ifndef PRAC3_TESTS_H
#define PRAC3_TESTS_H

bool runTests();
bool BookContructor();
bool BookSetAndGetFunctions();
bool BookExtractionOperator();
bool LibraryCopyContructor();
bool LibraryConstructorWithName();
bool LibraryDefaultSizeOf5();
bool LibraryAddBookOperator();
bool LibraryDoesNotAddBookWhenFull();
bool LibraryRemoveBookOperator();
bool LibraryReturnsWhenEmptyAndRemoveBook();
bool LibraryAssignmentOperatorCreatesCopy();
bool LibraryPostIncrementIncreasesLibrarySize();
bool LibraryPreDecrementDecreasesLibrarySize();
bool LibraryPreDecrementRemovesLastBookIfFull();
bool LibraryGetBook();
bool LibraryGetBookReturnsNullIfNotFound();
bool LibraryIsFullReturnsTrueIfFull();
bool LibraryIsFullReturnsFalseIfNotFull();
bool LibraryPrint();
bool LibraryPrintWhenEmpty();

#endif
