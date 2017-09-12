CC = g++
FLAGS = -std=c++11 -w -g -I -m64

a.out: clean task.o assignment4.o task_asm.o
	${CC} ${FLAGS} catchConfig.cpp task.o task_asm.o assignment4.o -o a.out -static

# NOTE: Fixed from the given makefile
task.o: task.cpp
	${CC} ${FLAGS} task.cpp -c -o task.o

assignment4.o:
	yasm -f elf64 -g dwarf2 ${PWD}/assignment4.asm -o assignment4.o

task_asm.o: task.asm
	yasm -f elf64 -g dwarf2 task.asm -o task_asm.o

run: a.out
	./a.out

clean:
	-@rm *.o a.out
