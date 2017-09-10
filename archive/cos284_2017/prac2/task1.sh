#!/bin/bash

outputFile=/tmp/task1_output.txt
expectedFile=$testDir/task1_expected.txt

# Compile
yasm -f elf64 -g dwarf2 -o /tmp/task1.o task1.asm
ld -o /tmp/task1.out /tmp/task1.o

# Test
touch $outputFile
echo -en "1234567890\n" | /tmp/task1.out > $outputFile
echo -en "1234567890\r\n" | /tmp/task1.out >> $outputFile
od -xc $outputFile > $outputFile.swap
mv -f $outputFile.swap $outputFile

diff $outputFile $expectedFile
