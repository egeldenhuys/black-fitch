#!/bin/bash

# This script sets the BLACK_FITCH path in the makefile by prepending it to the
# makefile template in the current directory

BLACK_FITCH_PATH=BLACK_FITCH=${PWD}

# Write path to new makefile
printf "$BLACK_FITCH_PATH\n\n" > makefile-black-fitch

# Append actual makefile
cat makefile >> makefile-black-fitch
