/*----------------------------------------------------------------------
 * Unit testing for fullDeckCount() function located in dominion.c
 * ---------------------------------------------------------------------
 */

/*----------------------------------------------------------------------
 * Function takes an int player number, an int card number and a pointer
 * to a gameState.
 * 
 * Returns the count of a specific card in a specific players discard 
 * deck and hand.
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

	printf("\nfullDeckCount():\n");
	printf("testing with card: smithy\n");

	status = fullDeckCount(1, smithy, &G);
	if(status == 0) {
		printf("Zero card count test PASS\n");
	}	
	else {
		printf("Zero card count test FAIL\n");
	}

	//add cards to deck
	G.deck[ 1 ][ G.deckCount[1] ] = smithy;
	G.deckCount[1]++;
	G.supplyCount[smithy]--;
	status = fullDeckCount(1, smithy, &G);
	if(status == 1) {
		printf("ONE card count test PASS\n");
	}	
	else {
		printf("ONE card count test FAIL\n");
	}


	//add cards to discard
	G.discard[ 1 ][ G.discardCount[1] ] = smithy;
	G.discardCount[1]++;
	G.supplyCount[smithy]--;
	status = fullDeckCount(1, smithy, &G);
	if(status == 2) {
		printf("TWO card count test PASS\n");
	}	
	else {
		printf("TWO card count test FAIL\n");
	}


	//add cards to hand
	G.hand[ 1 ][ G.handCount[1] ] = smithy;
	G.handCount[1]++;
	G.supplyCount[smithy]--;
	status = fullDeckCount(1, smithy, &G);
	if(status == 3) {
		printf("THREE card count test PASS\n");
	}	
	else {
		printf("THREE card count test FAIL\n");
	}

    	

	return 0;
}
