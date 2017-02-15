#include "utils.h"

using namespace std;

string applyPadding(string s)
{
    int matrixSize = pow(ceil(sqrt(double(s.size()))), 2);

    int diff = matrixSize - s.size();
    string result = s;

    for (int i = 0; i < diff; i++)
    {
        result += " ";
    }

    return result;
}
