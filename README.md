Black Fitch  
===========
![Latest Assignment Version](https://img.shields.io/badge/Assignment-3.2-green.svg)
![Latest Practical Version](https://img.shields.io/badge/Practical-8.0-blue.svg)

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

## Tag Convention
- `AX.Y`
    - `A` - a letter to indicate whether it is a practical or assignment
    - `X` - a number to indicate the practical/assignment numnber
- Example `a3.2`
    - Assignment 3, Revision 2
- Start at `AX.0` for initial test case release and increment Y for every change introduced into master

### Multiple projects at the same time in master

Example: We currently have assignment 3 and prac 8 in master
- When prac 8 was merged it was tagged with `p8.0`
- Assignment 3 was tagged with `a3.0` when it was merged
- When we fix something in assignment 3, we only modify content in the assignment3 folder. We then tag the new merge with `a3.1`
- This still allows us to have a single master, with multiple projects
at independent versions
