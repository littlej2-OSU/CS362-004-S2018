/*----------------------------------------------------------------------
 * Unit testing for smithyCard() function located in dominion.c
 * ---------------------------------------------------------------------
 */

/*----------------------------------------------------------------------
 * The smithy card takes 3 cards fromt he players deck and adds it to
 * thier hand.
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
	int r, h1, h2, d1, d2, p1, p2;
	int seed = 1000;
	int numPlayer =2;
	int k[10] = {adventurer, council_room, feast, gardens, mine
                 , remodel, smithy, village, baron, great_hall};
	struct gameState G;
	int player;

	r = initializeGame(numPlayer, k, seed, &G);
	player = whoseTurn(&G);
	printf("\nTest smithy card:\n");

	G.hand[player][G.handCount[player]] = smithy;
	G.handCount[player]++;	

	h1 = G.handCount[player];
	d1 = G.discardCount[player];
	p1 = G.deckCount[player];
	smithyCard(&G, player, G.handCount[player]);
	h2 = G.handCount[player];
	d2 = G.discardCount[player];
	p2 = G.deckCount[player];

	//we look to see if the hand has increased by 2 because the smithy card is removed
	//and we gain 3, for a total net gain of 2 cards
	if(h2 == h1+2) {
		printf("SMITHY hand test PASS; %d cards added to hand\n", h2-h1);
	}
	else {
		printf("SMITHY hand test FAIL; %d cards added to hand\n", h2-h1);
		printf("\tstarting hand count: %d\n \tend hand count: %d\n", h1, h2);

	}
	if(d2 == d1+1) {
		printf("SMITHY discard test PASS; %d cards added to discard\n", d2-d1);
	}
	else {
		printf("SMITHY discard test FAIL; %d cards added to discard\n", d2-d1);
		printf("\tstarting discard count: %d\n \tend discard count: %d\n", d1, d2);

	}
	if(p2 == p1-3) {
		printf("SMITHY deck test PASS; %d cards removed from deck\n", p1-p2);
	}
	else {
		printf("SMITHY deck test FAIL; %d cards removed from deck\n", p1-p2);
		printf("\tstarting deck count: %d\n \tend deck count: %d\n", p1, p2);
	}


	return 0;
}
