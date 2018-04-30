/*----------------------------------------------------------------------
 * Unit testing for greatHallCard() function located in dominion.c
 * ---------------------------------------------------------------------
 */

/*----------------------------------------------------------------------
 * The great hall card adds a victory point to the players score at the
 * end and then gives the player another action.
 * ---------------------------------------------------------------------
*/

#include "dominion.h"
#include "dominion_helpers.h"
#include "interface.h"
#include "rngs.h"

#include <string.h>
#include <stdio.h>
#include <assert.h>

int main() {
	int r, h1, h2, d1, d2;
	int seed = 1000;
	int numPlayer =2;
	int k[10] = {adventurer, council_room, feast, gardens, mine
                 , remodel, smithy, village, baron, great_hall};
	struct gameState G;
	int player;

	r = initializeGame(numPlayer, k, seed, &G);
	player = whoseTurn(&G);
	printf("\nTest Great Hall card:\n");

	G.hand[player][G.handCount[player]] = great_hall;
	G.handCount[player]++;	

	h1 = G.numActions;
	d1 = G.discardCount[player];
	greatHallCard(&G, G.handCount[player]);
	h2 = G.numActions;
	d2 = G.discardCount[player];

	if(h2 == h1+1) {
		printf("Great Hall action test PASS; %d actions available\n", h2);
	}
	else {
		printf("Great Hall action test FAIL; %d actions available\n", h2);

	}
	if(d2 == d1+1) {
		printf("SMITHY discard test PASS; %d cards added to discard\n", d2-d1);
	}
	else {
		printf("SMITHY discard test FAIL; %d cards added to discard\n", d2-d1);

	}
	printf("\n");

	return 0;
}
