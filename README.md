Black Fitch
===========

Unit tests for fitchfork practicals and assignments.

These tests are to be used as a guideline only and are not guaranteed to reflect the test cases used by fitchfork.

## Usage
### Clone and configure Black Fitch
1. `git clone https://github.com/egeldenhuys/black-fitch.git`
2. `cd black-fitch/assignment3`
3. `./configure.sh` to generate a makefile that points to the black-fitch tests directory
4. Copy the generated `black-fitch-makefile` into your project source directory
5. Run the tests from your source directory using `make -f black-fitch-makefile <target>`

### black-fitch-makefile Targets
- `task<X>` - run tests for given task
- `pull` - Update black-fitch from GitHub and then updates the black-fitch-makefile
- `update` - Update the Black Fitch makefile (usefull when developing tests)

## Feedback
A pull request will be opened for each practical before being merged into master.
The PR will be used for reporting results as the tests are being developed.

If you find any problems with the test cases, please open a new issue.
