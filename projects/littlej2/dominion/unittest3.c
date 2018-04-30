/*----------------------------------------------------------------------
 * Unit testing for fullDeckCount() function located in dominion.c
 * ---------------------------------------------------------------------
 */

/*----------------------------------------------------------------------
 * Function takes an int player number, an int card number and a pointer
 * to a gameState.
 * 
 * Returns the count of a specific cared in a specific players discard 
 * deck and hand.
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


	return 0;
}
