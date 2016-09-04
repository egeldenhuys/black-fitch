/*
Task 3: Librarian Information Class [15]
The library ensures that an updated database is always available for all library users. Hence,
it’s not very safe to allow anyone to add and remove books from the library without the
knowledge of the librarian. It would be wise to have an interface class that allows one to
interact with the Library without changing it directly.
Change the Library class from Task 2 so that the +=, -=, =, ++, -- operators are private
instead of public .
Declare Librarian to be a friend in the Library class (so that librarian may access these private
methods). Take note that your library.h will be overwritten if uploading to fitchfork, so make
sure that Librarian only interfaces with library via the specified methods and overloaded
operators in task 2.
The following private variable should be added to the Librarian class.
•
Library &lib :
The Library that the Librarian ’works’ at. Note that member reference variables need
to be initialized at construction.
•
const string name :
The librarians name.
The following public methods should be added to the Librarian class. All output from these
methods should be prepended by the librarian’s name (See the example output for more
information):
•
Book* lendBook(String name) :
Lends a book from the Library, returning a pointer to the book. Remove that book
from the Library. If the book is found, print out a message which includes the title of
the book. If the book is not found, print out "Sorry, we don’t have that book!".
Example output:
Mary: Sorry, we don’t have that book!
John: Here is the Harry Potter, we hope you enjoy it!
•
void returnBook(Book* book) :
Returns a book to the Library, and adds it back to the inventory. Prints out a message
including the title of the book. If the Library is full, increase it’s size and then add the
book.
Example output:
Sandy: Thanks for returning Harry Potter!
Create a tarball containing all the source code of the system and upload it using the active
fitchfork assignment called Practical3 - Librarian Class.
*/
#include <iostream>
#include <sstream>
#include <cassert>

#include "prac3_task3_tests.h"

#include "book.h"
#include "library.h"
#include "librarian.h"

using namespace std;

ostringstream captureLibraryAddBookOutput(Librarian &john, Book* book)
{
    // Redirect cout to our output stream
    ostringstream output;

	streambuf *oldCoutBuffer = cout.rdbuf();
	cout.rdbuf(output.rdbuf());
    cout << flush;

	// Capture output
	john.returnBook(book);

	// Reset cout
	cout.rdbuf(oldCoutBuffer);

    return output;
}

// 1 = success
bool addBooksToLibrary(Book **books, Librarian &john, int numBooks, string librarianName)
{
    ostringstream expectedOutput;
    ostringstream receivedOutput;

    for (int i = 0; i < numBooks; i++) {
        expectedOutput << librarianName << ": Thanks for returning " << books[i]->getTitle() << "!" << endl;
        //cout << books[i]->getTitle() << endl;
        //output.str("");
        receivedOutput = captureLibraryAddBookOutput(john, books[i]);
        assert(receivedOutput.str()==expectedOutput.str());

        expectedOutput.str("");
        receivedOutput.str("");
    }

    return true;
}

void popFromBookArray(Book** books, int index, int numBooks, bool destroy = true)
{
    if (destroy == true)
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

Book* captureLibrarianLend(Librarian &john, string title, ostringstream &out)
{
    out.str("");

    Book *borrowed;

	// Redirect cout to our output stream
    ostringstream output;

	streambuf *oldCoutBuffer = cout.rdbuf();
	cout.rdbuf(output.rdbuf());
    cout << flush;

	// Capture output
	borrowed = john.lendBook(title);

	// Reset cout
	cout.rdbuf(oldCoutBuffer);

    out << output.str();
    return borrowed;
}

ostringstream createMockLibrarianLendOutput(string name, string title)
{
    ostringstream expectedOutput;
    expectedOutput << name << ": Here is the " << title << ", we hope you enjoy it!" << endl;
    return expectedOutput;
}

ostringstream createMockLibrarianLendOutput(string name)
{
    ostringstream expectedOutput;
    expectedOutput << name << ": Sorry, we don’t have that book!" << endl;

    return expectedOutput;
}

/*
Book* lendBook(String name) :
Lends a book from the Library, returning a pointer to the book. Remove that book
from the Library. If the book is found, print out a message which includes the title of
the book. If the book is not found, print out "Sorry, we don’t have that book!".
Example output:
Mary: Sorry, we don’t have that book!
John: Here is the Harry Potter, we hope you enjoy it!
*/
bool librarianLendBook()
{
    cout << "TEST: librarianLendBook() = ";

    string libName = "Lend Book lib";
    string librarianName = "John";
    ostringstream expectedOutput;
    ostringstream receivedOutput;

    // Create library
    Library lib(libName);

    // Create librarian
    // Add library to librarian
    Librarian john(librarianName, lib);

    Book **books = createBooks("A", 5);
    Book *borrowed[5];

    addBooksToLibrary(books, john, 5, librarianName);

    // Request valid book at slot 1
    borrowed[0] = captureLibrarianLend(john, books[0]->getTitle(), receivedOutput);
    // Verify it is the book we wanted
    assert(borrowed[0]==books[0]);
    expectedOutput = createMockLibrarianLendOutput(librarianName, books[0]->getTitle());
    assert(receivedOutput.str()==expectedOutput.str());
    popFromBookArray(books, 0, 5, false); // pop from mock

    // Verify it has been removed from library
    expectedOutput = createMockLibraryPrintOutput(books, libName, 4, 5);
    receivedOutput = captureLibraryPrintOutput(lib);
    assert(receivedOutput.str()==expectedOutput.str());

    // Remove Last book
    borrowed[1] = captureLibrarianLend(john, books[3]->getTitle(), receivedOutput);
    // Verify it is the book we wanted
    assert(borrowed[1]==books[3]);
    expectedOutput = createMockLibrarianLendOutput(librarianName, books[3]->getTitle());

    assert(receivedOutput.str()==expectedOutput.str());
    popFromBookArray(books, 3, 5, false); // pop from mock

    // Verify it has been removed from library
    expectedOutput = createMockLibraryPrintOutput(books, libName, 3, 5);
    receivedOutput = captureLibraryPrintOutput(lib);
    assert(receivedOutput.str()==expectedOutput.str());

    // Request invalid book
    expectedOutput = createMockLibrarianLendOutput(librarianName);
    captureLibrarianLend(john, "INVALID BOOK... lele", receivedOutput);
    assert(receivedOutput.str()==expectedOutput.str());

    // Borrow all remaining books
    borrowed[2] = captureLibrarianLend(john, books[2]->getTitle(), receivedOutput);
    borrowed[3] = captureLibrarianLend(john, books[1]->getTitle(), receivedOutput);
    borrowed[4] = captureLibrarianLend(john, books[0]->getTitle(), receivedOutput);

    // Verify it has been removed from library
    expectedOutput = createMockLibraryPrintOutput(books, libName, 0, 5);
    receivedOutput = captureLibraryPrintOutput(lib);
    //cout << receivedOutput.str();
    assert(receivedOutput.str()==expectedOutput.str());

    deleteBooks(books, 3);
    delete borrowed[0];
    delete borrowed[1];

    cout << "PASS" << endl;
    return true;
}

bool librarianReturnFiveBooks()
{
    cout << "TEST: librarianReturnFiveBooks() = ";

    string libName = "Return Book LIB";
    string librarianName = "Fitchy";
    ostringstream expectedOutput;
    ostringstream receivedOutput;

    // Create library
    Library lib(libName);

    // Create librarian
    // Add library to librarian
    Librarian john(librarianName, lib);

    // Create books

    Book **books = createBooks("A", 5);

    // Add 5 books
    addBooksToLibrary(books, john, 5, librarianName);

    // Verifiy library
    expectedOutput = createMockLibraryPrintOutput(books, libName, 5, 5);
    receivedOutput = captureLibraryPrintOutput(lib);
    assert(receivedOutput.str()==expectedOutput.str());


    deleteBooks(books, 5);
    cout << "PASS" << endl;
    return true;
}

bool librarianReturnSixtyNineBooks()
{
    cout << "TEST: librarianReturnSixtyNineBooks() = ";

    string libName = "Return Book LIB";
    string librarianName = "Fitchy";
    ostringstream expectedOutput;
    ostringstream receivedOutput;

    // Create library
    Library lib(libName);

    // Create librarian
    // Add library to librarian
    Librarian john(librarianName, lib);

    // Create books

    Book **books = createBooks("A", 69);

    // Add 5 books
    addBooksToLibrary(books, john, 69, librarianName);

    // Verifiy library
    expectedOutput = createMockLibraryPrintOutput(books, libName, 69, 69);
    receivedOutput = captureLibraryPrintOutput(lib);
    assert(receivedOutput.str()==expectedOutput.str());


    deleteBooks(books, 69);
    cout << "PASS" << endl;
    return true;
}

bool runTests()
{
    cout << "Prac 3 - Task 2 Tests:\n";

    librarianLendBook();
    librarianReturnFiveBooks();
    librarianReturnSixtyNineBooks();

    return true;

}
