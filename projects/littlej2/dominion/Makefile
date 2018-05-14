CFLAGS = -Wall -fpic -coverage -lm

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

#unittest1: unittest1.c dominion.o rngs.o
#	gcc -o unittest1 -g  unittest1.c dominion.o rngs.o $(CFLAGS)
#
#unittest2: unittest2.c dominion.o rngs.o interface.o
#	gcc -o unittest2 -g  unittest2.c dominion.o rngs.o interface.o $(CFLAGS)
#
#cardtest1: cardtest1.c dominion.o rngs.o interface.o
#	gcc -o cardtest1 -g  cardtest1.c dominion.o rngs.o interface.o $(CFLAGS)
#
#cardtest3: cardtest3.c dominion.o rngs.o interface.o
#	gcc -o cardtest3 -g  cardtest3.c dominion.o rngs.o interface.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

#runtests: unittest1 unittest2 cardtest1 cardtest3 testDrawCard
#	./testDrawCard &> unittestresult.out
#	./unittest1 &>> unittestresult.out
#	./unittest2 &>> unittestresult.out
#	./cardtest1 &>> unittestresult.out
#	./cardtest3 &>> unittestresult.out
#	gcov -f -b dominion.c >> unittestresult.out
#	cat dominion.c.gcov >> unittestresult.out

randomtestsmithy: randomtestsmithy.c dominion.o interface.o rngs.o
	gcc -o randomtestsmithy -g randomtestsmithy.c dominion.o interface.o rngs.o $(CFLAGS)

randomtestgreathall: randomtestgreathall.c dominion.o interface.o rngs.o
	gcc -o randomtestgreathall -g randomtestgreathall.c dominion.o interface.o rngs.o $(CFLAGS)

randomtestadventurer: randomtestadventurer.c dominion.o interface.o rngs.o
	gcc -o randomtestadventurer -g randomtestadventurer.c dominion.o interface.o rngs.o $(CFLAGS)

runtests: randomtestsmithy randomtestgreathall randomtestadventurer
	./randomtestsmithy &> randomtestresult.out
	./randomtestgreathall &>> randomtestresult.out
	./randomtestadventurer &>> randomtestresult.out
	gcov -f -b dominion.c >> randomtestresult.out
	cat dominion.c.gcov >> randomtestresult.out

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player randomtestsmithy randomtestgreathall randomtestadventurer runtests
# testDrawCard unittest1 unittest2 runtests

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out unittest1 unittest2 cardtest1 cardtest3 testDrawCard testDrawCard.exe
