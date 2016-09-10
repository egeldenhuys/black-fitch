#!/bin/bash

# This script sets the BLACK_FITCH path in the makefile by prepending it to the
# makefile template in the current directory

# Write path to new makefile
printf "black_fitch_path=$PWD\n\n" > makefile-black-fitch

# Append actual makefile
cat makefile >> makefile-black-fitch
