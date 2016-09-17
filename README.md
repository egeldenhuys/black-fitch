black-fitch
===========

Unit tests for fitchfork practicals. These tests are to be used a guideline only and are not guaranteed
to reflect the test cases used by fitchfork.

If you passed all the black-fitch tests, but did not received errors from fitchfork, please
open an issue with your results so that we can fix the tests. Even better: fork the repo, fix the tests and send a pull request.

## Usage
1. Run `./configure.sh` to generate and set the path in the makefile
2. Copy the generated `black-fitch-makefile` into your project source directory
3. Test using `make -f black-fitch-makefile <target>`

`black-fitch-makefile` targets:
- `task<x>` - run tests for given task
- `update` - Configure and copy the makefile from the black-fitch folder
- `pull` - Pull the black-fitch repo, configure and update the black-fitch makefile in your source repo

## Development Branches
- Please see the [branches](https://github.com/egeldenhuys/black-fitch/branches) for development braches
