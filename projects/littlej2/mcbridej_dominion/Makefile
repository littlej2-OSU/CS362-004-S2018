CFLAGS = -std=c99 -Wall -fpic -coverage -lm

smithy.o: refactored.h smithy.c
	gcc -c smithy.c -g $(CFLAGS)

adventurer.o: refactored.h adventurer.c
	gcc -c adventurer.c -g $(CFLAGS)

village.o: refactored.h village.c
	gcc -c village.c -g $(CFLAGS)

treasure_map.o: refactored.h treasure_map.c
	gcc -c treasure_map.c -g $(CFLAGS)

gardens.o: refactored.h gardens.c
	gcc -c gardens.c -g $(CFLAGS)

CARD_OBJS = smithy.o adventurer.o village.o treasure_map.o gardens.o

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o 
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c ${CARD_OBJS}
	gcc -o playdom playdom.c -g dominion.o rngs.o ${CARD_OBJS} $(CFLAGS)

#To run playdom you need to entere: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o ${CARD_OBJS}
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o ${CARD_OBJS} $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o ${CARD_OBJS}
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o ${CARD_OBJS} $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o ${CARD_OBJS}
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o ${CARD_OBJS} $(CFLAGS)

testAll: dominion.o testSuite.c ${CARD_OBJS}
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o ${CARD_OBJS} $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

unittest1: unittest1.c ${CARD_OBJS} dominion.o interface.o rngs.o 
	gcc -o unittest1 -g unittest1.c dominion.o interface.o rngs.o ${CARD_OBJS} $(CFLAGS)

unittest2: unittest2.c ${CARD_OBJS} dominion.o interface.o rngs.o 
	gcc -o unittest2 -g unittest2.c dominion.o interface.o rngs.o ${CARD_OBJS} $(CFLAGS)

cardtest1: cardtest1.c ${CARD_OBJS} dominion.o interface.o rngs.o 
	gcc -o cardtest1 -g cardtest1.c dominion.o interface.o rngs.o ${CARD_OBJS} $(CFLAGS)

cardtest3: cardtest3.c ${CARD_OBJS} dominion.o interface.o rngs.o 
	gcc -o cardtest3 -g cardtest3.c dominion.o interface.o rngs.o ${CARD_OBJS} $(CFLAGS)

randomtestsmithy: randomtestsmithy.c ${CARD_OBJS} dominion.o interface.o rngs.o 
	gcc -o randomtestsmithy -g randomtestsmithy.c dominion.o interface.o rngs.o ${CARD_OBJS} $(CFLAGS)

randomtestgreathall: randomtestgreathall.c ${CARD_OBJS} dominion.o interface.o rngs.o 
	gcc -o randomtestgreathall -g randomtestgreathall.c dominion.o interface.o rngs.o ${CARD_OBJS} $(CFLAGS)

runtests: randomtestsmithy randomtestgreathall cardtest1 cardtest3 unittest1 unittest2
	./unittest1 &> unittestresult.out
	./unittest2 &>> unittestresult.out
	./cardtest1 &>> unittestresult.out
	./randomtestsmithy &>> unittestresult.out
	./cardtest3 &>> unittestresult.out
	./randomtestgreathall &>> unittestresult.out
	gcov -f -b dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out


player: player.c interface.o ${CARD_OBJS}
	gcc -o player player.c -g  dominion.o rngs.o interface.o ${CARD_OBJS} $(CFLAGS)

all: playdom player runtests

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so cardtest1 unittest1 unittest2 cardtest1 cardtest3 randomtestsmithy randomtestgreathall
