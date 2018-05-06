CFLAGS = -Wall -fpic -coverage -lm

testme: testme.c
	gcc -o testme testme.c -g $(CFLAGS)

testRun: testme
	./testme

all: testme testRun

clean:
	rm -f testme *.gcno *.gcda *.gcov 


