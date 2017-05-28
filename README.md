Black Fitch
===========
Unit tests for fitchfork practicals and assignments.

These tests are to be used as a guideline only and are not guaranteed to reflect the test cases used by fitchfork.

## Status
Currently tests are available for:
#### COS 212 Assignment 5
- [X] Task 1
- [ ] Task 2 (In progress)
- [ ] Task 3

## Usage
### Clone and configure Black Fitch
1. `git clone https://github.com/egeldenhuys/black-fitch.git`
2. `cd black-fitch/cos212_assignment1`
3. `./configure.sh` to generate a makefile that points to the black-fitch assignment directory
4. Copy the generated `black-fitch-makefile` into your project source directory
5. Run the tests from your source directory using `make -f black-fitch-makefile <target>`
	- See the `README.md` in the practical folder for prac specific targets

### black-fitch-makefile Targets
- `task<X>` - run tests for given task
- `pull` - Update black-fitch from GitHub and then updates the black-fitch-makefile
- `update` - Update the Black Fitch makefile (usefull when developing tests)

### Java Fitch submodule
It might be necessary to download Java Fitch with:

```
git submodule init
git submodule update
```
(From the black-fitch directory)

## Feedback
A pull request will be opened for each practical before being merged into master.
The PR will be used for reporting results as the tests are being developed.

If you find any problems with the test cases, please open a new issue.
