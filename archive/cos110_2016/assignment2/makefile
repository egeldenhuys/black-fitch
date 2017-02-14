# To be configured by configure.sh:
# black_fitch_path = /path/to/black-fitch/prac5

compile=g++ -std=c++11 -Wall -pedantic -g -I "$(PWD)"

task1_tests="$(black_fitch_path)/task1.cpp" "$(black_fitch_path)/../catch/catchConfig.cpp" \
Caesar.cpp Cipher.h SubstitutionCipher.cpp OneTimePad.cpp

task2_tests=$(task1_tests) "$(black_fitch_path)/task2.cpp" "$(black_fitch_path)/utils.cpp" \
RowsColumns.cpp TranspositionCipher.cpp ZigZag.cpp

task3_tests=$(task2_tests) "$(black_fitch_path)/task3.cpp" \
CipherPipeline.cpp

task1:
	 $(compile) $(task1_tests) -o task1.out
	./task1.out

task2:
	$(compile) $(task2_tests) -o task2.out
	./task2.out

task3:
	$(compile) $(task3_tests) -o task3.out
	./task3.out

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
