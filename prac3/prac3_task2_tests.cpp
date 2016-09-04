/*
fitchfork Test Cases
====================

Execute FAILED
Test case '1' => Produced 564 bytes of output, exit code 2

main.cpp: In function 'int main(int, const char**)':
main.cpp:9:14: error: 'class Library' has no member named 'getName'
  cout << lib.getName() << endl;
              ^
main.cpp:12:6: error: 'class Library' has no member named 'setName'
  lib.setName("library");
      ^
main.cpp:13:14: error: 'class Library' has no member named 'getName'
  cout << lib.getName() << endl;
              ^
main.cpp:56:7: error: 'class Library' has no member named 'setName'
  lib2.setName("library2");
       ^
makefile:8: recipe for target 'main' failed
make: *** [main] Error 1

FAIL Test 1: Check getName()
FAIL Test 2: Check setName()
FAIL Test 3: Check output of print function.
FAIL Test 4: Check output of print function.
FAIL Test 5: Check output of print function when Library is empty.
FAIL Test 6: Check output of print function.
FAIL Test 7: Adding a book failed, or the print function is incorrect.
FAIL Test 8: Check output of print function.
FAIL Test 9: Printing a full library failed.
FAIL Test 10: Removing one book failed, or the print function is incorrect
FAIL Test 11: Check isFull()
FAIL Test 12: Check output when adding a book to a full Library
FAIL Test 13: Check ++ operator.
FAIL Test 14: Check -- operator.
FAIL Test 15: Check getBook().
FAIL Test 16: Check copy constructor.
FAIL Test 17: Check copy constructor.
FAIL Test 18: Check output of print function when Library is empty.
FAIL Test 19: Check getBook() when the book doesn't exist.
*/



#include <iostream>
#include <sstream>
#include <cassert>

#include "prac3_task2_tests.h"

#include "book.h"
#include "library.h"

using namespace std;

// 1 = success
bool addBooksToLibrary(Book **books, Library &lib, int numBooks)
{
    ostringstream fullString;
    fullString << "Library is full!" << endl;
    bool full = false;

    for (int i = 0; i < numBooks; i++) {
        //cout << books[i]->getTitle() << endl;

        ostringstream output = captureLibraryAddBookOutput(lib, books[i]);

        if (output.str() == fullString.str()) {
            full = true;
        }
    }

    return full;
}

void popFromBookArray(Book** books, int index, int numBooks)
{
    delete books[index];
    books[index] = 0;

    for (int j = index; j < numBooks - 1; j++) {
        books[j] = books[j + 1];
        books[j + 1] = 0;
    }
}
/*
Returns a pointer to an array of book pointers
*/
Book** createBooks(string prefix, int numBooks)
{
    // **books -> *bookArray[numBooks] -> book

    //cout << endl << "POINTERS: ------------" << endl;

    Book **books = new Book*[numBooks];
    //cout << &books << endl;

    //cout << "\t" << books << endl;

    for (int i = 0; i < numBooks; i++) {
        Book *tmpBook = new Book(prefix + "_Title_" + to_string(i), \
        prefix + "_Author_" + to_string(i), prefix + "_ISBN_" + to_string(i));

        *(books + i) = tmpBook;
        //cout << "\t\t" << (*books + i) << endl;
    }

    return books;
}

void deleteBooks(Book **books, int numBooks)
{
    // **books -> *bookPtrArray[numBooks] -> book

    // Delete each book
    for (int i = 0; i < numBooks; i++) {
        //cout << "delete: " << *(books + i) << endl;

        // Each pointer in array
        if (*(books + i) != 0) {
            delete *(books + i);
            *(books + i) = 0;
        }
        //cout << " = " << *(books + i) << endl;
    }

    //cout << "delete array: " << books << endl;

    delete [] books;
    books = 0;
}

/*
Produces mock output for Library::print()

*/
ostringstream createMockLibraryPrintOutput(Book **books, string libraryName, int numBooks, int librarySize)
{
    ostringstream expectedOutput;

    expectedOutput << "Inventory of " << libraryName << endl;
	expectedOutput << "===================================\n";

    // Books
    for (int i = 0; i < numBooks; i++) {
		expectedOutput << i + 1 << ". " << books[i]->getTitle() \
        << " - " << books[i]->getAuthor() \
        << " - " << books[i]->getISBN() << endl;
	}

    // Empty slots
    if (numBooks == 0) {
        expectedOutput << "EMPTY" << endl;
    } else {
        for (int i = numBooks; i < librarySize; i++)
        {
            expectedOutput << i + 1 << ". [Empty Space]\n";
        }
    }

    expectedOutput << "==================================" << endl;

    return expectedOutput;
}

ostringstream captureLibraryAddBookOutput(Library &lib, Book* book)
{
    // Redirect cout to our output stream
    ostringstream output;

	streambuf *oldCoutBuffer = cout.rdbuf();
	cout.rdbuf(output.rdbuf());
    cout << flush;

	// Capture output
	lib += book;

	// Reset cout
	cout.rdbuf(oldCoutBuffer);

    return output;
}

ostringstream captureLibraryPrintOutput(Library &lib)
{
    // http://stackoverflow.com/questions/4810516/c-redirecting-stdout

	// Redirect cout to our output stream
    ostringstream output;

	streambuf *oldCoutBuffer = cout.rdbuf();
	cout.rdbuf(output.rdbuf());
    cout << flush;

	// Capture output
	lib.print();

	// Reset cout
	cout.rdbuf(oldCoutBuffer);

    return output;
}

Book** getBooksFromLibraryByName(Book** books, Library &lib, int numBooks)
{
    Book **copyBooks = new Book*[numBooks];

    for (int i = 0; i < numBooks; i++) {
        copyBooks[i] = lib.getBook(books[i]->getTitle());
    }

    return copyBooks;
}

bool BookConstructor()
{
    cout << "Test: BookContructor() = ";
    string title = "My Title";
    string author = "Some Author";
    string ISBN = "7345";

    Book testBook(title, author, ISBN);

    assert(title==testBook.getTitle());
    assert(author==testBook.getAuthor());
    assert(ISBN==testBook.getISBN());

    cout << "PASS\n";

    return true;
}

bool BookSetAndGetFunctions()
{
    cout << "Test: BookSetAndGetFunctions() = ";

    Book testBook;
    string title = "Book Title";
    string author = "Book Author";
    string ISBN = "98473287572";
    string genre = "Book Genre";

    testBook.setTitle(title);
    testBook.setAuthor(author);
    testBook.setISBN(ISBN);
    testBook.setGenre(genre);

    assert(title==testBook.getTitle());
    assert(author==testBook.getAuthor());
    assert(ISBN==testBook.getISBN());
    assert(genre==testBook.getGenre());

    cout << "PASS\n";

    return true;
}

bool BookExtractionOperator()
{
    cout << "Test: BookExtractionOperator() = ";

    Book testBook;
    string title = "Book Title";
    string author = "Book Author";
    string ISBN = "98473287572";
    string genre = "Book Genre";

    testBook.setTitle(title);
    testBook.setAuthor(author);
    testBook.setISBN(ISBN);
    testBook.setGenre(genre);

    std::ostringstream expectedOutput;
    expectedOutput << title << " - " << author << " - " << ISBN;

    ostringstream output;
    output << testBook;

    assert(expectedOutput.str()==output.str());
    cout << "PASS\n";
    return true;

}

bool LibrarySetAndGetName()
{
    cout << "Test: LibrarySetAndGetName() = ";

    string libName = "bitchfork";

    Library lib;
    lib.setName(libName);
    assert(libName==lib.getName());

    Book **books = createBooks("fitchfork", 5);
    ostringstream expectedOutput = createMockLibraryPrintOutput(books, libName, 0, 5);
    ostringstream output = captureLibraryPrintOutput(lib);

    assert(expectedOutput.str()==output.str());

    libName = "EDIT";

    lib.setName(libName);
    assert(libName==lib.getName());
    addBooksToLibrary(books, lib, 5);

    expectedOutput = createMockLibraryPrintOutput(books, libName, 5, 5);
    output = captureLibraryPrintOutput(lib);
    assert(expectedOutput.str()==output.str());

    deleteBooks(books, 5);

    cout << "PASS" << endl;
	return true;
}

/*
TESTS:
- Adds books correctly using +=
- Default library size of 5
- Print outputs correctly
*/
bool LibraryAdd5BooksAndPrint()
{
	cout << "Test: LibraryAdd5BooksAndPrint() = ";

	ostringstream output;
	ostringstream expectedOutput;

	// Library Constructor with string
	string libName = "Lib Name";
	Library lib(libName);

	int numBooks = 5;

	// Create books
    Book **books = createBooks("A",numBooks);

    addBooksToLibrary(books, lib, numBooks);
    expectedOutput = createMockLibraryPrintOutput(books, libName, numBooks, 5);
	output = captureLibraryPrintOutput(lib);

	//cout << expectedOutput.str();
	//cout << output.str();

	assert(expectedOutput.str()==output.str());

    deleteBooks(books, 5);

	cout << "PASS" << endl;

	return true;
}

/*
The copy constructor needs to make a copy of each book in the inventory
of the old library and add it to the new library.
*/
bool LibraryCopyContructorCreatesDeepCopy()
{
	cout << "Test: LibraryCopyContructorCreatesDeepCopy() = ";
    int numBooks = 5;
    string libName = "Library A";

    // Create a library
    Library libA(libName);

    // Fill it with books
    Book **booksA = createBooks(libName, numBooks);
    addBooksToLibrary(booksA, libA, numBooks);

    //libA.print();

    // Create deep copy
    Library libB(libA);
    Book **booksB = getBooksFromLibraryByName(booksA, libB, numBooks);

    // Change all the books in Library A
    for (int i = 0; i < numBooks; i++) {
        booksA[i]->setTitle("EDIT_" + to_string(i));
    }

    // Compare with the books in the other library
    ostringstream expectedLibA = createMockLibraryPrintOutput(booksA, libName, numBooks, numBooks);
    ostringstream expectedLibB = createMockLibraryPrintOutput(booksB, libName, numBooks, numBooks);
    ostringstream outputLibA = captureLibraryPrintOutput(libA);
    ostringstream outputLibB = captureLibraryPrintOutput(libB);

    //cout << outputLibA.str();
    //cout << outputLibB.str();

    assert(expectedLibA.str()==outputLibA.str());
    assert(expectedLibB.str()==outputLibB.str());
    assert(outputLibA.str()!=outputLibB.str());

    deleteBooks(booksA, numBooks);
    deleteBooks(booksB, numBooks);

    cout << "PASS" << endl;
    return true;
}

bool LibraryDoesNotAddBookWhenFull()
{
    cout << "Test: LibraryDoesNotAddBookWhenFull() = ";
    string libName = "My Library";

    Library lib(libName);

    // Fill it with books
    Book **books = createBooks(libName, 6);
    addBooksToLibrary(books, lib, 5);

    ostringstream expectedLibA = createMockLibraryPrintOutput(books, libName, 5, 5);

    ostringstream addOutput = captureLibraryAddBookOutput(lib, books[5]);
    ostringstream expectedAddOutput;
    expectedAddOutput << "Library is full!" << endl;

    assert(addOutput.str()==expectedAddOutput.str());

    ostringstream outputLibA = captureLibraryPrintOutput(lib);

    assert(expectedLibA.str()==outputLibA.str());

    deleteBooks(books, 6);

    cout << "PASS" << endl;
    return true;
}

bool LibraryRemoveBookOperator()
{
    cout << "Test: LibraryRemoveBookOperator() = ";

    string libName = "My Library";
    int libSize = 5;

    Library lib(libName);

    // Fill it with books
    Book **books = createBooks(libName, libSize);
    addBooksToLibrary(books, lib, libSize);

    // Pop first book
    lib -= books[0];
    popFromBookArray(books, 0, libSize);

    ostringstream expectedLibA = createMockLibraryPrintOutput(books, libName, 4, libSize);
    ostringstream outputLibA = captureLibraryPrintOutput(lib);
    //cout << outputLibA.str();
    assert(expectedLibA.str()==outputLibA.str());

    // Pop last book
    lib -= books[3];
    popFromBookArray(books, 3, libSize);

    expectedLibA = createMockLibraryPrintOutput(books, libName, 3, libSize);
    outputLibA = captureLibraryPrintOutput(lib);
    //cout << outputLibA.str();
    assert(expectedLibA.str()==outputLibA.str());

    // pop Middle book
    lib -= books[1];
    popFromBookArray(books, 1, libSize);

    expectedLibA = createMockLibraryPrintOutput(books, libName, 2, libSize);
    outputLibA = captureLibraryPrintOutput(lib);
    //cout << expectedLibA.str();
    assert(expectedLibA.str()==outputLibA.str());

    // Pop second last
    lib -= books[0];
    popFromBookArray(books, 0, libSize);

    // Pop last
    lib -= books[0];
    popFromBookArray(books, 0, libSize);
    expectedLibA = createMockLibraryPrintOutput(books, libName, 0, libSize);
    outputLibA = captureLibraryPrintOutput(lib);

    //cout << expectedLibA.str();
    //cout << outputLibA.str();

    assert(expectedLibA.str()==outputLibA.str());

    // Pop invalid
    cout << endl << "\t" << "About to remove non-existing book from library..." << endl;

    string prefix = "";
    int i = 69;
    Book *tmpBook = new Book(prefix + "_Title_" + to_string(i), \
    prefix + "_Author_" + to_string(i), prefix + "_ISBN_" + to_string(i));

    lib -= tmpBook;

    expectedLibA = createMockLibraryPrintOutput(books, libName, 0, libSize);
    outputLibA = captureLibraryPrintOutput(lib);
    assert(expectedLibA.str()==outputLibA.str());

    delete tmpBook;
    deleteBooks(books, 5);

    cout << "PASS" << endl;
    return true;
}

bool LibraryAssignmentOperatorCreatesDeepCopy()
{
    cout << "Test: LibraryAssignmentOperatorCreatesDeepCopy() = ";
    int numBooks = 5;
    string libName = "Library A";
    string libNameB = "Library B";

    // Create a library
    Library libA(libName);

    // Fill it with books
    Book **booksA = createBooks(libName, numBooks);
    addBooksToLibrary(booksA, libA, numBooks);

    // Create deep copy
    Library libB(libNameB);
    libB = libA;

    Book **booksB = getBooksFromLibraryByName(booksA, libB, numBooks);

    // Change all the books in Library A
    for (int i = 0; i < numBooks; i++) {
        booksA[i]->setTitle("EDIT_" + to_string(i));
    }

    // Compare with the books in the other library
    ostringstream expectedLibA = createMockLibraryPrintOutput(booksA, libName, numBooks, numBooks);
    ostringstream expectedLibB = createMockLibraryPrintOutput(booksB, libName, numBooks, numBooks);
    ostringstream outputLibA = captureLibraryPrintOutput(libA);
    ostringstream outputLibB = captureLibraryPrintOutput(libB);

    assert(expectedLibA.str()==outputLibA.str());
    assert(expectedLibB.str()==outputLibB.str());
    assert(outputLibA.str()!=outputLibB.str());

    deleteBooks(booksA, numBooks);
    deleteBooks(booksB, numBooks);

    cout << "PASS" << endl;
    return true;
}

bool LibraryPostIncrementIncreasesLibrarySize()
{
    cout << "Test: LibraryPostIncrementIncreasesLibrarySize() = ";

    string libName = "Library A";

    // Create a library
    Library lib(libName);

    // Fill it with books
    Book **books = createBooks(libName, 6);
    addBooksToLibrary(books, lib, 5);

    // Increase size
    lib++;

    // Check output
    ostringstream expectedLib = createMockLibraryPrintOutput(books, libName, 5, 6);
    ostringstream outputLib = captureLibraryPrintOutput(lib);

    assert(expectedLib.str()==outputLib.str());

    // Add a book
    lib += books[5];

    // Check output
    expectedLib = createMockLibraryPrintOutput(books, libName, 6, 6);
    outputLib = captureLibraryPrintOutput(lib);

    assert(expectedLib.str()==outputLib.str());

    deleteBooks(books, 6);

    cout << "PASS" << endl;
    return true;
}

bool LibraryPreDecrementDecreasesLibrarySize()
{
    cout << "Test: LibraryPreDecrementDecreasesLibrarySize() = ";

    string libName = "Wat";

    // Create a library
    Library lib(libName);

    // Fill it with books, but leave empty space
    Book **books = createBooks(libName, 4);
    addBooksToLibrary(books, lib, 4);

    // Decrease size
    --lib;

    // Check output
    ostringstream expectedLib = createMockLibraryPrintOutput(books, libName, 4, 4);
    ostringstream outputLib = captureLibraryPrintOutput(lib);
    assert(expectedLib.str()==outputLib.str());

    deleteBooks(books, 4);

    cout << "PASS" << endl;
    return true;
}

bool LibraryPreDecrementRemovesLastBookIfFull()
{
    cout << "Test: LibraryPreDecrementRemovesLastBookIfFull() = ";

    string libName = "K";

    // Create a library
    Library lib(libName);

    // Fill it with books
    Book **books = createBooks(libName, 5);
    addBooksToLibrary(books, lib, 5);

    // Decrease size
    --lib;

    // Check output
    ostringstream expectedLib = createMockLibraryPrintOutput(books, libName, 4, 4);
    ostringstream outputLib = captureLibraryPrintOutput(lib);
    assert(expectedLib.str()==outputLib.str());

    deleteBooks(books, 5);

    cout << "PASS" << endl;
    return true;
}

bool LibraryGetBook()
{
    cout << "Test: LibraryGetBook() = ";

    string libName = "Get a book";

    // Create a library
    Library lib(libName);

    // Fill it with books
    Book **books = createBooks(libName, 5);
    addBooksToLibrary(books, lib, 5);

    // Request valid book
    Book *tmp = lib.getBook(books[0]->getTitle());
    assert(books[0]==tmp);

    // Return first name
    for (int i = 0; i < 4; i++)
    {
        books[i]->setTitle("LOLCAT");
    }

    tmp = lib.getBook(books[3]->getTitle());
    assert(books[0]==tmp);

    // return non-existing book
    assert(NULL==lib.getBook("John and the giant dog"));

    deleteBooks(books, 5);
    cout << "PASS" << endl;
    return true;
}

bool LibraryIsFull()
{
    cout << "Test: LibraryIsFull() = ";

    string libName = "Fill me up";

    // Create a library
    Library lib(libName);

    // Fill it with books
    Book **books = createBooks(libName, 6);
    addBooksToLibrary(books, lib, 5);
    assert(true==lib.isFull());

    // Increase size
    lib++;
    assert(false==lib.isFull());

    // Add book
    addBooksToLibrary(books, lib, 6);

    assert(true==lib.isFull());

    // Pop a book
    lib -= books[3];
    popFromBookArray(books, 3, 6);
    assert(false==lib.isFull());

    // Add book
    addBooksToLibrary(books, lib, 6);
    assert(true==lib.isFull());

    deleteBooks(books, 6);
    cout << "PASS" << endl;
    return true;

}

bool LibraryRemoveAllSpace()
{
    cout << "Test: LibraryRemoveAllSpace() = ";

    string libName = "WATATA";

    Library lib;
    lib.setName(libName);

    Book **books = createBooks("LIBA", 5);
    addBooksToLibrary(books, lib, 5);
    Book *tmp = books[0];

    // Remove all space and break stuff

    for (int i = 0; i < 10; i++)
    {
        --lib;
    }

    assert(tmp==books[0]);

    // Check output
    ostringstream expectedLib = createMockLibraryPrintOutput(books, libName, 0, 0);
    ostringstream outputLib = captureLibraryPrintOutput(lib);
    assert(expectedLib.str()==outputLib.str());

    // Add a book
    addBooksToLibrary(books, lib, 1);
    expectedLib = createMockLibraryPrintOutput(books, libName, 0, 0);
    outputLib = captureLibraryPrintOutput(lib);
    assert(expectedLib.str()==outputLib.str());

    deleteBooks(books, 5);

    cout << "PASS" << endl;
    return true;
}

bool runTests()
{

	BookConstructor();
	BookSetAndGetFunctions();
	BookExtractionOperator();

    LibrarySetAndGetName();
	LibraryAdd5BooksAndPrint();
    LibraryCopyContructorCreatesDeepCopy();
    LibraryDoesNotAddBookWhenFull();
    LibraryRemoveBookOperator();
    LibraryAssignmentOperatorCreatesDeepCopy();
    LibraryPostIncrementIncreasesLibrarySize();
    LibraryPreDecrementDecreasesLibrarySize();
    LibraryPreDecrementRemovesLastBookIfFull();
    LibraryGetBook();
    LibraryIsFull();
    LibraryRemoveAllSpace();

    return true;
}
