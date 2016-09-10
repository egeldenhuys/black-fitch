# To be configured by configure.sh:
# BLACK_FITCH = ~/github/black-fitch/assignment1

task1_tests=${BLACK_FITCH}/task1.cpp
task2_tests=${TASK1_TESTS} ${BLACK_FITCH}/task2.cpp

task1:
	g++ -std=c++11 -Wall -g -I ${PWD} ${task1_tests} Wizard.cpp Spell.cpp Hobbit.cpp -o black-fitch-task1.out
	./black-fitch-task1.out

task2:
	g++ -std=c++11 -Wall -g -I ${PWD} ${task2_tests} Wizard.cpp Spell.cpp Hobbit.cpp -o black-fitch-task2.out
	./black-fitch-task2.out
