#!/bin/bash

# https://stackoverflow.com/questions/59895/getting-the-source-directory-of-a-bash-script-from-within/246128#246128
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

if [ ! -e "$DIR/catch.hpp" ]; then
    echo "Downloading catch.hpp..."
    curl -L 'https://github.com/philsquared/Catch/releases/download/v1.10.0/catch.hpp' -o $DIR/catch.hpp
fi

make -C $DIR DIR=$DIR a.out

echo "Binaries for debugging are in $DIR"

export DIR=$DIR
echo "Executing tests..."
$DIR/a.out
