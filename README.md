black-fitch
===========

## Branches
- Please see the [branches](https://github.com/egeldenhuys/black-fitch/branches) for active development

## Usage
1. Run `./configure.sh` to generate and set the path in the makefile
2. Copy the created `makefile-black-fitch` into your project source directory
3. Test using `make -f makefile-black-fitch <target>`

`makefile-black-fitch` targets:
- `task<x>` - run tests for all tasks leading up to and including this task
- `update` - Configure and copy the makefile from the black-fitch folder it originated from
- `pull` - Pull the black-fitch repo, configure and update the black-fitch makefile in your source repo
