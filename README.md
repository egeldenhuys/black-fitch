Black Fitch
===========
Unit tests for fitchfork practicals and assignments.

These tests are to be used as a guideline only and are not guaranteed to reflect the test cases used by fitchfork.

## Dependencies
- curl

## Usage
### Clone and configure Black Fitch
1. `git clone https://github.com/egeldenhuys/black-fitch.git`
2. Import java-fitch if required
3. Run black-fitch from the directory that contains your source:
	- `$ .../black-fitch/black-fitch.sh 284 ass 4`

### black-fitch.sh
- `black-fitch.sh <MODULE_CODE> <prac|ass> <PRAC/ASS_NUMBER> [TASK_NUMBER]`

#### Arguments
- $1 - [0-9]{3} (Module Code)
- $2 - <prac|ass>
- $3 - Prac/Assignment number
- $4 - Task number (leave empty if no tasks)

#### Example
`.../black-fitch.sh 284 ass 4` Will execute `.../black-fitch/cos284/ass4/task.sh`

### Java Fitch submodule
If testing using Java, you need to import java-fitch:

```
git submodule init
git submodule update
```

## Feedback
A pull request will be opened for each practical before being merged into master.
The PR will be used for reporting results as the tests are being developed.

If you find any problems with the test cases, please open a new issue.
