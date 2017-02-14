#!/bin/bash

# This script sets the black_fitch_path in black-fitch-makefile by prepending it to the
# makefile_template in the current directory

# Write path to new makefile
printf "black_fitch_path=$PWD\n\n" > black-fitch-makefile

# Append template makefile to black-fitch-makefile
cat makefile_template >> black-fitch-makefile

##############################
# PARSE COMMAND LINE ARGUMENTS
##############################
quiet=false # -q | --quiet

# From http://stackoverflow.com/questions/192249/how-do-i-parse-command-line-arguments-in-bash
# By Bruno Bronosky (http://stackoverflow.com/users/117471/bruno-bronosky)

# Use -gt 1 to consume two arguments per pass in the loop (e.g. each
# argument has a corresponding value to go with it).
# Use -gt 0 to consume one or more arguments per pass in the loop (e.g.
# some arguments don't have a corresponding value to go with it

while [[ $# -gt 0 ]]
do
key="$1"

case $key in
    -q|--quiet)
    quiet=true
    shift # past argument
    ;;
    *)
            # unknown option
    ;;
esac
shift # past argument or value
done

###################
# PRINT INFORMATION
###################

if [ "$quiet" == false ]
then
    # Provide the user some information
    echo "Configured 'black-fitch-makefile'"
    echo "Move this file into your project source directory."
    echo ""
    echo "See the README if you require further instructions."
fi
