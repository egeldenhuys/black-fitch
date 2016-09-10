#ifndef PRAC3_TASK3_TESTS
#define PRAC3_TASK3_TESTS

bool runTests();

class Book;
class Library;
class Librarian;

using namespace std;

// Utility functions
bool addBooksToLibrary(Book **books, Librarian &john, int numBooks, string librarianName);
bool addBookToLibrary(Book *book, Librarian &john, string librarianName);
void popFromBookArray(Book** books, int index, int numBooks);
Book** createBooks(std::string prefix, int numBooks);
void deleteBooks(Book **books, int numBooks);
void createMockLibraryPrintOutput(Book **books, std::string libraryName, int numBooks, int librarySize, ostringstream &);
void captureLibraryPrintOutput(Library &lib, ostringstream &);
void captureLibraryAddBookOutput(Librarian &john, Book* book, ostringstream &);
Book* captureLibrarianLend(Librarian &john, string title, ostringstream &);
//bool createMockLibrarianLendOutput(string name, string title, ostringstream &);
void createFailedMockLibrarianLendOutput(string name, ostringstream &);


bool librarianLendBook();
bool librarianReturnFiveBooks();
bool librarianReturnSixtyNineBooks();

#endif
