/**
 * DESC: this driver class is used as the primary .java file to run the simple 
 * blackjack game defined. this iteration of blackjack does not include
 * doubling down or splitting hands. this version only includes the basic
 * premise of blackjack of standing and hitting with the one goal of getting
 * your hand as close to 21 as possible. the dealer is defined in code to only
 * hit if their hand has a score of less than 17. any score higher means the
 * dealer will always stand. if the player wins the game, whether by reaching
 * 21 or having a greater score than the dealer, the player's initial bet is
 * doubled and added to their balance. the game can go on however long the
 * player wants as long as the player maintains a non zero, non negative amount
 * of money.
 * @author David Kim (CSS 142)
 * @version 5/29/23
 */
import java.util.Scanner;

public class BlackJack {
    /**
     * method contains the code used for blackjack. by using the defined hand
     * and card classes and a few helper methods, the game can be played in its
     * simplest form.
     * @param args
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\n*-=Welcome to Blackjack!=-*");
        int totalBet = 0;
        // do while to check for invalid inputs
        do {
            System.out.print("Please enter your total " + 
                "betting amount for this session: ");
            totalBet = keyboard.nextInt();
        } while (totalBet < 0);

        int roundNumber = 1;
        boolean gameContinue = true;
        // array to store all cards drawn during the game
        Hand table = new Hand(22);
        // while loop to continue looping if the user wants to continue playing 
        // and has a positive, non zero amount of money to bet from
        while (gameContinue && totalBet > 0) {
            boolean roundEnd = false;
            int roundBet = 0;
            System.out.println("Your balance: " + totalBet);
            System.out.println("=> ROUND " + roundNumber);
            // do while to check for invalid inputs
            do {
                System.out.print("Enter your bet for this round: ");
                roundBet = keyboard.nextInt();
            } while (roundBet <= 0 || roundBet > totalBet);

            // // CODE FOR TESTING PURPOSES
            // System.out.println("roundBet: " + roundBet);
            // System.out.println("totalBet: " + totalBet);

            // arrays to store user and dealer cards
            Hand userHand = new Hand(11);
            Hand dealerHand = new Hand(11);
            userHand.drawRandomCard(2, table);
            dealerHand.drawRandomCard(2, table);
            System.out.println("\n[Starting cards have been dealt.]");

            // // CODE FOR TESTING PURPOSES
            // System.out.println(userHand.toString());
            // System.out.println(dealerHand.toString());
            // System.out.println(table.toString());

            // print out messages on screen for user to see the starting hands
            System.out.println("\n  The dealer's starting hand." + 
                " Only one card will be shown.");
            dealerHand.hideHand(true);
            System.out.println(dealerHand.toString());
            System.out.println("The value of the dealer's hand is hidden.");
            System.out.println("\n  Your starting hand.");
            System.out.println(userHand.toString());
            System.out.println("The value of your hand is " + 
                getHandValue(userHand));

            // check starting hands for blackjack
            if (getHandValue(dealerHand) == 21 && 
                getHandValue(userHand) == 21) {
                System.out.println("=> Tie! Your bet has been returned.");
                roundEnd = true;
            } else if (getHandValue(dealerHand) == 21) {
                System.out.println("=> Natural blackjack! You lose.");
                totalBet -= roundBet;
                roundEnd = true;
            } else if (getHandValue(userHand) == 21) {
                System.out.println("=> Natural blackjack!" + 
                    " You win. 2x your bet is returned.");
                totalBet += (roundBet * 2);
                roundEnd = true;
            }

            boolean dealerTurn = false;
            // array of chars storing stand/hit decisions. if two stand
            // decisions are made in a row, the hands are compared and the
            // winner is decided
            char[] charDecisions = new char[22];
            int numDecisions = 0;
            // while loop to iterate through each player's turns
            while (!roundEnd) {
                // if statement checking whether or not it's the dealer's turn
                if (!dealerTurn) {
                    char userDecision = ' ';
                    // do while to check for invalid inputs
                    do {
                        System.out.print("Stand or hit? (s/h): ");
                        userDecision = keyboard.next().charAt(0);
                    } while (userDecision != 's' && userDecision != 'h');
                    // if statement to define what happens if the user chooses
                    // to stand or hit
                    if (userDecision == 's') {
                        System.out.println("  [You decide to stand. No cards" + 
                            " drawn.]");
                        System.out.println(userHand.toString());
                        System.out.println("The value of your hand is " + 
                            getHandValue(userHand));
                        charDecisions[numDecisions] = 's';
                        numDecisions++;
                        // end player turn
                        dealerTurn = true;
                    } else if (userDecision == 'h') {
                        System.out.println("  [You decide to hit. You draw a" + 
                            " card.]");
                        userHand.drawRandomCard(1, table);
                        System.out.println(userHand.toString());
                        System.out.println("The value of your hand is: " + 
                            getHandValue(userHand));
                        // check if the new card results in a win/lose condition
                        if (getHandValue(userHand) > 21) {
                            System.out.println("=> Bust! You lose.");
                            totalBet -= roundBet;
                            roundEnd = true;
                        } else if (getHandValue(userHand) == 21) {
                            System.out.println("=> Blackjack! You win. 2x" + 
                                " your bet is returned.");
                            totalBet += (roundBet * 2);
                            roundEnd = true;
                        }
                        charDecisions[numDecisions] = 'h';
                        numDecisions++;
                        // end player turn
                        dealerTurn = true;
                    }
                } else {
                    // if statement to decide whether or not the dealer will    
                    // stand or hit. in this case, the dealer will always hit if
                    // their hand value is lower than a 17. otherwise, they will
                    // always stand
                    if (getHandValue(dealerHand) < 17) {
                        System.out.println("  [Dealer decides to hit. A card" +
                            " is drawn.]");
                        dealerHand.drawRandomCard(1, table);
                        dealerHand.hideHand(true);
                        System.out.println(dealerHand.toString());
                        // check if the new card results in a win/lose condition
                        if (getHandValue(dealerHand) > 21) {
                            System.out.println("=> Bust! You win. 2x your" + 
                                " bet is returned.");
                            totalBet += (roundBet * 2);
                            roundEnd = true;
                        } else if (getHandValue(dealerHand) == 21) {
                            System.out.println("=> Blackjack! You lose.");
                            totalBet -= roundBet;
                            roundEnd = true;
                        }
                        charDecisions[numDecisions] = 'h';
                        numDecisions++;
                        // end dealer turn
                        dealerTurn = false;
                    } else {
                        System.out.println("  [Dealer decides to stand. No" + 
                            " cards drawn.]");
                        System.out.println(dealerHand.toString());
                        charDecisions[numDecisions] = 's';
                        numDecisions++;
                        // end dealer turn
                        dealerTurn = false;
                    }
                }
                boolean compareHands = false;
                // loop to check if both the player and dealer chose to stand.
                // if so, compare hands and determine the winner of this round
                for (int i = 1; i < numDecisions; i++) {
                    if (charDecisions[i] == 's' && charDecisions[i - 1] == 's')
                        compareHands = true;
                }
                if (compareHands) {
                    printAllHands(dealerHand, userHand);
                    // compare both hands for win/lose conditions
                    if (getHandValue(dealerHand) > getHandValue(userHand)) {
                        System.out.println("=> Dealer wins with the greater" + 
                            " hand! You lose.");
                        totalBet -= roundBet;
                    } else if (getHandValue(dealerHand) == 
                        getHandValue(userHand)) {
                        System.out.println("=> Tie! Your bet is returned.");
                    } else if (getHandValue(dealerHand) < 
                        getHandValue(userHand)) {
                        System.out.println("=> You win with the greater" + 
                            " hand! 2x your bet is returned.");
                        totalBet += (roundBet * 2);
                    }
                    // end this round
                    roundEnd = true;
                }
            }
            System.out.println("Your balance: " + totalBet);
            // if statement to check the total bet amount. if this is negative
            // or zero, the player may not play again and the game ends
            if (!(totalBet <= 0)) {
                char userPlayAgain = ' ';
                // do while to check for invalid inputs
                do {
                    System.out.print("Play another round? (y/n): ");
                    userPlayAgain = keyboard.next().charAt(0);
                } while (userPlayAgain != 'y' && userPlayAgain != 'n');
    
                // if user chose no, the game ends
                if (userPlayAgain == 'n')
                    gameContinue = false;
                roundNumber++;
            } else {
                System.out.println("=> Bring back more money! GAME OVER.");
                keyboard.close();
                gameContinue = false;
            }
        }
    }

    /**
     * method is used to get the value of a given hand according to the rules of
     * blackjack. takes any hand and returns an int representing the score of
     * the hand.
     * @param hand      the user or dealer hand in which a value is calculated.
     * @return          an int which is the sum of the values of all the cards
     *                  in the given hand.
     */
    public static int getHandValue(Hand hand) {
        int handValue = 0;
        int numAces = 0;
        // sums the value of all non ace cards together and counts the number of
        // aces in this hand
        for (int i = 0; i < hand.getNumCards(); i++) {
            if (hand.getHand()[i].getCardID() != 1) {
                handValue += hand.getHand()[i].getCardValue();
            } else {
                numAces++;
            }
        }
        // adds the value of aces to the total hand value. aces can be a 1 or 11
        // in the hand depending if adding 11 to the total hand value exceeds 21
        for (int i = 0; i < numAces; i++) {
            if (11 + handValue > 21) {
                handValue++;
            } else {
                handValue += 11;
            }
        }
        return handValue;
    }

    /**
     * method is used to print the dealer and user hands onto the screen for the
     * player to see so they can see all the cards on the table when the game
     * ends.
     * @param dlr       the dealer's hand.
     * @param usr       the player's hand.
     */
    public static void printAllHands(Hand dlr, Hand usr) {
        System.out.println("\n  Dealer's final hand:");
        dlr.revealHand();
        System.out.println(dlr.toString());
        System.out.println("  Dealer's hand value is " + getHandValue(dlr));
        System.out.println("\n Your final hand:");
        System.out.println(usr.toString());
        System.out.println("  The value of your hand is " + getHandValue(usr));
    }
}
