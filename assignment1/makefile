BLACK_FITCH_PATH = ~/github/black-fitch

black-fitch:
	g++ -std=c++11 -Wall -g -I . $(BLACK_FITCH_PATH)/assignment1/tests.cpp Wizard.cpp Spell.cpp -o black-fitch.out

test: black-fitch
	./black-fitch.out
