Assignment 1 Tests
==================

## Usage
1. `./configure.sh` to set the path in the makefile
2. Copy `makefile-black-fitch` into your assignment source directory
3. Test using `make -f makefile-black-fitch <target>`

- makefile-black-fitch targets:
- Usage: `makefile -f makefile-black-fitch <target>`
- `task<x>` - run tests for all tasks leading up to and including this task
- `update` - Configure and copy the makefile from the black-fitch folder it originated from
- `pull` - Pull the black-fitch repo, configure and update the black-fitch makefile in your source repo

### Updates and the makefile
When black-fitch has been updated, from your source directory run `make -f makefile-black-fitch pull`
 to update your copy of black-fitch and your makefile in your source directory
