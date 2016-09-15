#!/bin/bash

# This script sets the BLACK_FITCH path in the makefile by prepending it to the
# makefile template in the current directory

# Write path to new makefile
printf "black_fitch_path=$PWD\n\n" > black-fitch-makefile

# Append actual makefile
cat makefile >> black-fitch-makefile
