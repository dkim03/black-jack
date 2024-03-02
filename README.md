# Description
This program defines a **very simple** version of blackjack, following the rules stated below:
- Game uses a single deck of cards, no duplicates.
- If player gets blackjack or has a greater hand than the dealer, game returns double the initial bet.
- Ties return the player's initial bet.
- Dealer will always decide to hit if their hand is less than 17 and stand if their hand is greater than or equal to 17.
- Player goes first every round and can only stand or hit.
- The player may see the dealer's first card but all other cards will be hidden.
- Aces are valued at 1 or 11, whichever works.
- Round ends if:
  - Player or dealer hand is valued at 21.
  - Player or dealer hand is valued greater than 21.
  - Player and dealer decide to stand.
- Game terminates if player decides to quit or if player loses all money.

# Game Overview
The game functions through a double ```while``` loop which checks conditions that end the game or round mentioned in the rules. If no game-end or round-end conditions are met, the game continues.

Each round follows the same gameplay loop:
1. Initial cards are drawn, check for blackjack
2. Ask player to stand or hit, check round-end conditions
3. Shift to dealer
4. Dealer stands or hits, check round-end conditions
5. Shift to player
6. Check round-end conditions
7. Repeat 2-6 until round ends

# Purpose
This project was created to practice fundamental coding concepts such as functional decomposition, nested loops, arrays, and the use of multiple classes.
