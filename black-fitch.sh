#!/bin/bash

# Black Fitch control script

# ARGS
# $1 - [0-9]{3} (Module Code (284))
# $2 - <prac|ass>
# $3 - Prac/Assignment number
# $4 - Task number (leave empty if no tasks)

# EXAMPLE
# ./blackfitch.sh 284 ass 4
# Will execute cos284/ass4/task.sh

# https://stackoverflow.com/questions/59895/getting-the-source-directory-of-a-bash-script-from-within/246128#246128
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

testDir="${DIR}/cos${1}/${2}${3}"
testScript="${testDir}/task${4}.sh"

source $testScript
