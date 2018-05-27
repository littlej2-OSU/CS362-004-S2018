/*----------------------------------------------------------------------
 * Unit testing for isGameOver function located in dominion.c
 * ---------------------------------------------------------------------
 */

/*----------------------------------------------------------------------
 * Function takes a pointer to a games state.
 * 1. Checks the stack of province cards.  If this  pile is empty then
 *    return a 1 for game over.
 * 2. Check each supply pile to see if they have cards left.  If 3 or 
 *    more supply piles are empty then return 1 for a game over.
 * 3. If neither 1 or 2 are true then return 0 to continue play.
 * ---------------------------------------------------------------------
*/

#include "dominion.h"
#include "dominion_helpers.h"
#include "interface.h"
#include "rngs.h"
#include "refactored.h"

#include <string.h>
#include <stdio.h>
#include <assert.h>

int main() {
	int i, r, n, status, l;
	int seed = 1000;
	int numPlayer =2;
	int k[10] = {adventurer, council_room, feast, gardens, mine
                 , remodel, smithy, village, baron, great_hall};
	struct gameState G;
	struct gameState new;

	n = initializeGame(numPlayer, k, seed, &new);
	r = initializeGame(numPlayer, k, seed, &G);

	printf("\nTesting isGameOver():\n");

	for(i = 0; i < 3; i++) {
		printf("Test %d province cards: ", i);
		G.supplyCount[province] = i;
		status = isGameOver(&G);
		if(G.supplyCount[province] == 0) {
		//	assert(status == 1);
			if(status == 1) 
				printf("0 province cards test PASS\n");
			else
				printf("0 province card test FAIL\n");
	
		}
		else {
		//	assert(status == 0);
			if(status == 0) 
				printf("%d province cards test PASS\n", i);
			else
				printf("%d province card test FAIL\n", i);
		}
	}

	l = *(&new.supplyCount + 1) - new.supplyCount;
	printf("\n");
	for(i=0; i < l; i++) {
		printf("Test %d empty supply piles: ", i+1);
		G.supplyCount[i] = 0;
		status = isGameOver(&G);
		if(i < 2) {
			if(status == 0) 
				printf("%d empty supply piles test PASS\n", i+1);
			else
				printf("%d empty supply piles test FAIL\n", i+1);
		}
		else {
			if(status == 1) 
				printf("%d empty supply piles test PASS\n", i+1);
			else
				printf("%d empty supply piles test FAIL\n", i+1);

		}
	}

	return 0;
}
