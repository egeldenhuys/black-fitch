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
ostringstream createMockLibraryPrintOutput(Book **books, std::string libraryName, int numBooks, int librarySize);
ostringstream captureLibraryPrintOutput(Library &lib);
ostringstream captureLibraryAddBookOutput(Librarian &john, Book* book);
Book* captureLibrarianLend(Librarian &john, string title);
ostringstream createMockLibrarianLendOutput(string name, string title);
ostringstream createMockLibrarianLendOutput(string name);


bool librarianLendBook();
bool librarianReturnFiveBooks();
bool librarianReturnSixtyNineBooks();

#endif
