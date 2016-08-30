#include <iostream>
#include <sstream>
#include <cassert>

#include "Book.h"
#include "prac3Tests.h"

using namespace std;

bool BookSetAndGetFunctions()
{
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

    return true;
}

bool BookExtractionOperator()
{
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
    return true;

}

bool runTests()
{
    BookSetAndGetFunctions();
    BookExtractionOperator();

    return true;
}
