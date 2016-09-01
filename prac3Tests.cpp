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

bool LibraryCopyContructor()
{
    cout << "Test: LibraryCopyContructor() = ";

    string name = "My Library Name";

    // Create books
    for ()

    cout << "PASS" << endl;

    return true;
}

bool runTests()
{

    BookContructor();
    BookSetAndGetFunctions();
    BookExtractionOperator();

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

    LibraryPrint();
    LibraryPrintWhenEmpty();


    return true;
}
