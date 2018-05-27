/*----------------------------------------------------------------------
 * Random testing for greatHallCard() function located in dominion.c
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
#include <time.h>
#include <stdlib.h>

int randomGameState(struct gameState * game);

int main() {
		int h1, h2, d1, d2;
		int i=0, numT=0, handf=0, discardf=0;
		int seed = 1000;
		int numPlayer =2;
		int k[10] = {adventurer, council_room, feast, gardens, mine
				, remodel, smithy, village, baron, great_hall};
		struct gameState G;
		int player;
		int * bonus = NULL;

		numT = 1000;

		srand(time(NULL));
		printf("\nTest Great Hall card:\n");

		for(i=0; i<numT; i++){	
				initializeGame(numPlayer, k, seed, &G);
				randomGameState(&G);
				player = whoseTurn(&G);

				G.hand[player][G.handCount[player]] = great_hall;
				G.handCount[player]++;	

				h1 = G.numActions;
				d1 = G.discardCount[player];
				cardEffect(great_hall, 0, 0, 0, &G, G.handCount[player], bonus);
				h2 = G.numActions;
				d2 = G.discardCount[player];

				if(h2 == h1+1) {
//						printf("Great Hall action test PASS; %d actions available\n", h2);
				}
				else {
						handf++;
//						printf("Great Hall action test FAIL; %d actions available\n", h2);

				}
				if(d2 == d1+1) {
//						printf("Great Hall discard test PASS; %d cards added to discard\n", d2-d1);
				}
				else {
						discardf++;
//						printf("Great Hall discard test FAIL; %d cards added to discard\n", d2-d1);

				}
		}
		printf("\n%d tests run; action test failed %d times; discard test failed %d times\n\n", numT, handf, discardf);

		return 0;
}

int randomGameState(struct gameState * game) {
		int i, j, rn;
		game->coins = 5000;
		for(i=0; i < game->numPlayers; i++){
				game->whoseTurn = i;
				game->numActions = rand() % 5;
				rn = rand() % 30;
				for(j=0; j<rn; j++){
						buyCard((rand() % 17), game);
				}
				shuffle(i, game);
				rn = rand() % 10;
				if(game->deckCount[i]-rn < 4) {
						rn = game->deckCount[i]-4;
				}
				for(j=0; j<rn; j++){
						drawCard(i, game);
				}
		}
		game->whoseTurn = rand() % game->numPlayers;
		return 0;
}

