# To be configured by configure.sh:
# black_fitch_path = /path/to/black-fitch/prac5

task1_tests="$(black_fitch_path)/task1.cpp"
task2_tests=$(task_1_tests) "$(black_fitch_path)/task2.cpp"

task1:
	g++ -std=c++11 -Wall -pedantic -g -I "$(PWD)" $(task1_tests) Exception.cpp -o task1.out
	./task1.out

task2:
	g++ -std=c++11 -Wall -pedantic -g -I "$(PWD)" $(task2_tests) CharString.cpp Exception.cpp -o task2.out
	./task2.out

clean:
	rm -f *.o *.out

# Configure and copy the latest makefile
update:
	cd "$(black_fitch_path)" && ./configure.sh
	mv "$(black_fitch_path)/black-fitch-makefile" .

# Pull black-fitch, configure and copy the latest makefile
pull:
	cd "$(black_fitch_path)" && git pull
	cd "$(black_fitch_path)" && ./configure.sh
	mv "$(black_fitch_path)/black-fitch-makefile" .
