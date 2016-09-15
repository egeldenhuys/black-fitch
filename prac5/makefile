# To be configured by configure.sh:
# black_fitch_path = ~/github/black-fitch/assignment1

#task1_tests=${black_fitch_path}/task1.cpp
#task2_tests=${task1_tests} ${black_fitch_path}/task2.cpp
#task3_tests=${task2_tests} ${black_fitch_path}/task3.cpp
#task4_tests=${task3_tests} ${black_fitch_path}/task4.cpp

#task1:
#	g++ -std=c++11 -Wall -pedantic -g -I ${PWD} ${task1_tests} Wizard.cpp Spell.cpp Hobbit.cpp -o black-fitch-task1.out
#	./black-fitch-task1.out

clean:
	rm -f *.o *.out

# Configure and copy the latest makefile
update:
	cd $(black_fitch_path) && ./configure.sh
	mv $(black_fitch_path)/black-fitch-makefile .

# Pull black-fitch, configure and copy the latest makefile
pull:
	cd $(black_fitch_path) && git pull
	cd $(black_fitch_path) && ./configure.sh
	mv $(black_fitch_path)/black-fitch-makefile .
