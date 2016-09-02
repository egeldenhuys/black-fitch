#include <iostream>
#include <sstream>
#include <cassert>

#include "prac3Tests.h"

#include "book.h"
#include "Library.h"

using namespace std;

bool BookContructor()
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

/*
PROCESS
- Create library
- Create 4 books
- Add these 4 books to library
- Print library and compare output
*/
bool LibraryBaseFunctions()
{
	cout << "Test: LibraryBaseFunctions() = ";
	
	ostringstream output;
	ostringstream expectedOutput;
	
	// Library Constructor with string
	string libName = "Lib Name";
	
	Library lib(libName);
	expectedOutput << "Inventory of " << libName << endl;
	expectedOutput << "===================================\n";
	
	int bookCount = 4;
	
	// Create books
	Book *books[bookCount];

	for (int i = 0; i < bookCount; i++) {
		
		Book *tmpBook = new Book(string("Title_" + to_string(i)), string("Author_" + to_string(i)), string("ISBN_" + to_string(i)));
		
		expectedOutput << i + 1<< ". " << "Title_" + to_string(i) << " - " << "Author_" + to_string(i) << " - " << "ISBN_" + to_string(i) << endl;
		
		books[i] = tmpBook;
		
		// Add books
		lib += tmpBook;
	}

	expectedOutput << "5. [Empty Space]\n";
	expectedOutput << "==================================" << endl;

// http://stackoverflow.com/questions/4810516/c-redirecting-stdout

	
	// Redirect cout to our output stream
	streambuf *oldCoutBuffer = cout.rdbuf();
	cout.rdbuf(output.rdbuf());
	
	// Capture output
	cout << flush;
	
	lib.print();
	
	// Reset cout
	cout.rdbuf(oldCoutBuffer);
	
	//cout << expectedOutput.str();
	//cout << output.str();
	
	assert(expectedOutput.str()==output.str());
	
	for (int i = 0; i < bookCount; i++) {
		delete books[i];
	}
	
	cout << "PASS" << endl;
	
	return true;
}
//~ bool LibraryCopyContructor()
//~ {
	//~ // Create library using known variables
	//~ // Invoke CC
	//~ // Compare print output
	
	//~ return true;
//~ }

bool runTests()
{

	BookContructor();
	BookSetAndGetFunctions();
	BookExtractionOperator();

	LibraryBaseFunctions();
	
	//LibraryPrint();
	
/*
    LibraryCopyContructor();

    LibraryConstructorWithName();
    LibraryDefaultSizeOf5();

    LibraryAddBookOperator();
    LibraryDoesNotAddBookWhenFull();

    LibraryRemoveBookOperator();
    LibraryReturnsWhenEmptyAndRemoveBook();

    LibraryAssignmentOperatorCreatesCopy();
    LibraryPostIncrementIncreasesLibrarySize();
    LibraryPreDecrementDecreasesLibrarySize();
    LibraryPreDecrementRemovesLastBookIfFull();

    LibraryGetBook();
    LibraryGetBookReturnsNullIfNotFound();

    LibraryIsFullReturnsTrueIfFull();
    LibraryIsFullReturnsFalseIfNotFull();


    LibraryPrintWhenEmpty();
*/

    return true;
}
