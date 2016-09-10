# To be configured by configure.sh:
# black_fitch_path = ~/github/black-fitch/assignment1

task1_tests=${black_fitch_path}/task1.cpp
task2_tests=${task1_tests} ${black_fitch_path}/task2.cpp
task3_tests=${task2_tests} ${black_fitch_path}/task3.cpp

task1:
	g++ -std=c++11 -Wall -g -I ${PWD} ${task1_tests} Wizard.cpp Spell.cpp Hobbit.cpp -o black-fitch-task1.out
	./black-fitch-task1.out

task2:
	g++ -std=c++11 -Wall -g -I ${PWD} ${task2_tests} Wizard.cpp Spell.cpp Hobbit.cpp -o black-fitch-task2.out
	./black-fitch-task2.out

task3:
	g++ -std=c++11 -Wall -g -I ${PWD} ${task3_tests} Wizard.cpp Spell.cpp Hobbit.cpp -o black-fitch-task3.out
	./black-fitch-task3.out

clean:
	rm -f *.o *.out
	
update:
	cd $(black_fitch_path) && ./configure.sh
	mv $(black_fitch_path)/makefile-black-fitch .

pull:
	cd $(black_fitch_path) && git pull
	cd $(black_fitch_path) && ./configure.sh
	mv $(black_fitch_path)/makefile-black-fitch .
