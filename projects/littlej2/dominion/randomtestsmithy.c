/*----------------------------------------------------------------------
 * Random testing for smithyCard() function located in dominion.c
 * ---------------------------------------------------------------------
 */

/*----------------------------------------------------------------------
 * The smithy card takes 3 cards from the players deck and adds it to
 * thier hand.
 * ---------------------------------------------------------------------
 */

#include "dominion.h"
#include "dominion_helpers.h"
#include "interface.h"
#include "rngs.h"

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>

int randomGameState(struct gameState * game);

int main() {
		int i=0, numTests=0, handF=0, discardF=0, deckF=0;
		int h1, h2, d1, d2, p1, p2;
		int seed = 1000;
		int numPlayer =2;
		int k[10] = {adventurer, council_room, feast, gardens, mine
				, remodel, smithy, village, baron, great_hall};
		struct gameState G;
		int player;

		numTests = 1000;

		srand(time(NULL));
		printf("\nTest smithy card:\n");

		for(i=0; i<numTests; i++){
				initializeGame(numPlayer, k, seed, &G);
				randomGameState(&G);

				player = whoseTurn(&G) ;
				G.hand[player][G.handCount[player]] = smithy;
				G.handCount[player]++;	

				h1 = G.handCount[player];
				d1 = G.discardCount[player];
				p1 = G.deckCount[player];
				smithyCard(&G, G.handCount[player]);
				h2 = G.handCount[player];
				d2 = G.discardCount[player];
				p2 = G.deckCount[player];

				if(h2 == h1+3) {
		//				printf("SMITHY hand test PASS; %d cards added to hand\n", h2-h1);
				}
				else {
						handF++;
		//				printf("SMITHY hand test FAIL; %d cards added to hand\n", h2-h1);

				}

				if(d2 == d1+1) {
		//				printf("SMITHY discard test PASS; %d cards added to discard\n", d2-d1);
				}
				else {
						discardF++;
		//				printf("SMITHY discard test FAIL; %d cards added to discard\n", d2-d1);

				}
				if(p2 == p1-3) {
		//				printf("SMITHY deck test PASS; %d cards removed from deck\n", p1-p2);
				}
				else {
						deckF++;
		//				printf("SMITHY deck test FAIL; %d cards removed from deck\n", p1-p2);
				}
		}
		printf("\n\n%d tests run; hand test failed %d times; discard test failed %d times; deck test failed %d times\n\n", numTests, handF, discardF, deckF);
		return 0;
}


int randomGameState(struct gameState * game) {
		int i, j, rn;

		game->coins = 5000;
		for(i=0; i < game->numPlayers; i++){
				game->whoseTurn = i;
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
