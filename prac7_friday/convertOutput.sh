#!/bin/bash

# Add padding
sed -r -e 's/\(Y,(.)\)/(Y,\1)  /g' output.txt > tmp1.txt

# Convert newlines
# http://stackoverflow.com/questions/1251999/how-can-i-replace-a-newline-n-using-sed
sed ':a;N;$!ba;s/\n/\\n/g' tmp1.txt > tmp2.txt

# Add quotes

sed 's/^/\"/g' tmp2.txt > tmp1.txt
sed 's/$/\"/g' tmp1.txt > tmp2.txt

# Add ;
sed 's/$/;/g' tmp2.txt > tmp1.txt

# Add expected =
sed 's/^/expected = /g' tmp1.txt > final.txt

rm tmp1.txt
rm tmp2.txt

cat final.txt
rm final.txt
