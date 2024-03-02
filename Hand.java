/**
 * DESC: this class is used to hold multiple card objects and allow those cards 
 * to be manipulated in different ways like printing multiple cards to the
 * screen or revealing/hiding them from view or generating random, non duplicate
 * cards.
 * @author David Kim (CSS 142)
 * @version 5/29/23
 */
import java.util.Random;

public class Hand {
    private Card[] hand = null;
    private int numCards = 0;

    /**
     * constructor used to create the object and initialize the size of the card
     * array.
     * @param size      defines the size of the card array.
     */
    public Hand(int size) {
        this.hand = new Card[size];
    }

    /**
     * method used to add an already defined card to the card array.
     * @param crd       the card to be added to the card array.
     */
    public void addCard(Card crd) {
        this.hand[this.numCards] = crd;
        this.numCards++;
    }

    /**
     * method used to generate a random, non duplicate card to add to the card
     * array.
     * @param numDraw   the number of random cards to generate.
     * @param table     the existing hand which duplicate cards are classified.
     */
    public void drawRandomCard(int numDraw, Hand table) {
        // card ids and suits of a standard deck of cards are defined and used
        // as the pool for which random cards can be generated from
        int[] cardID = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
        char[] cardSuits = {'C', 'S', 'H', 'D'};
        Random rand = new Random();
        boolean isOnTable = false;
        // for loop to loop n number of times for n number of cards
        for (int i = 0; i < numDraw; i++) {
            Card newCard = null;
            // do while to loop until it generates a non duplicate card
            do {
                isOnTable = false;
                // random card is generated
                int cardIDRandIndex = rand.nextInt(13);
                int cardSuitsRandIndex = rand.nextInt(4);
                newCard = new Card(cardID[cardIDRandIndex], 
                    cardSuits[cardSuitsRandIndex]);

                // new card is checked against pre existing cards
                for (int j = 0; j < table.getNumCards(); j++)
                    if (newCard.isDuplicate(table.getHand()[j]))
                        isOnTable = true;
            } while (isOnTable);

            // non duplicate cards are added to the pre existing card pool and
            // the current hand
            this.hand[this.numCards] = newCard;
            this.numCards++;
            table.addCard(newCard);
        }
    }

    /**
     * method used to give access to the instance card array from outside the
     * class.
     * @return          this card array.
     */
    public Card[] getHand() {
        return this.hand;
    }

    /**
     * method used to give the number of cards in the card array outside the 
     * class.
     * @return          the number of defined cards in the card array.
     */
    public int getNumCards() {
        return this.numCards;
    }

    /**
     * method used to hide the hand when the toString() method is called.
     * @param isDealer  boolean to determine whether or not the method should
     *                  reveal the first card of the card array.
     */
    public void hideHand(boolean isDealer) {
        // hide all cards
        for (int i = 0; i < numCards; i++)
            this.hand[i].setVisibility(true);

        // reveal the first card if this hand is the dealer
        if (isDealer)
            this.hand[0].setVisibility(false);
    }

    /**
     * method to unhide the hand when the toString() method is called.
     */
    public void revealHand() {
        for (int i = 0; i < numCards; i++)
            this.hand[i].setVisibility(false);
    }

    /**
     * method to turn the card array into something that can be printed out and 
     * presented onto the screen. the data for each card in the card array is 
     * used to define the face values and suits of each card as they are printed
     * out.
     * @return          string containing all the cards in a presentable form.
     */
    public String toString() {
        String printedHand = "";
        int currentSection = 0;
        int currentCard = 0;
        // loops 7 times per card as there are 7 printed sections that make up
        // each card
        for (int i = 1; i < (numCards * 7) + 1; i++) {
            // 1st and 7th row, top and bottom borders
            if (currentSection == 0 || currentSection == 6)
                printedHand += " +-------+ ";

            // the 2nd row of the card which includes the face value of the 
            // card. this is a ? if hidden
            if (currentSection == 1) {
                if (hand[currentCard].getVisibility()) {
                    printedHand += " |?      | ";
                } else {
                    printedHand += " |";
                    printedHand += getFaceValueAtIndex(currentCard);

                    if (this.hand[currentCard].getCardID() == 10) {
                        printedHand += "     | ";
                    } else {
                        printedHand += "      | ";
                    }
                }
            }

            // 3rd and 5th row making up the body of the card
            if (currentSection == 2 || currentSection == 4)
                printedHand += " |       | ";

            // the 4th middle row of the card which includes the suit of the
            // card. this is a ? if hidden
            if (currentSection == 3) {
                if (hand[currentCard].getVisibility()) {
                    printedHand += " |   ?   | ";
                } else {
                    printedHand += " |   ";
                    printedHand += "" + this.hand[currentCard].getCardSuit();
                    printedHand += "   | ";
                }
            }

            // the 6th row of the card which includes the face value of the card
            // this is a ? if hidden
            if (currentSection == 5) {
                if (hand[currentCard].getVisibility()) {
                    printedHand += " |      ?| ";
                } else {
                    if (this.hand[currentCard].getCardID() == 10) {
                        printedHand += " |     ";
                    } else {
                        printedHand += " |      ";
                    }
                    printedHand += getFaceValueAtIndex(currentCard);
                    printedHand += "| ";
                }
            }
            // move onto next card's data
            currentCard++;
            // if statement to determine when to place a nextline character
            if (i % numCards == 0) {
                printedHand += "\n";
                currentCard = 0;
                currentSection++;
            }
        }
        return printedHand;
    }

    /**
     * helper method used to get the face value of a card based on the index at 
     * which it is positioned in the card array.
     * @param index     the index of where the desired card is located.
     * @return          string containing the face value of the card.
     */
    private String getFaceValueAtIndex(int index) {
        if (this.hand[index].getCardID() == 11)
            return "J";
        if (this.hand[index].getCardID() == 12)
            return "Q";
        if (this.hand[index].getCardID() == 13)
            return "K";
        if (this.hand[index].getCardID() == 1)
            return "A";
        return "" + this.hand[index].getCardID();
    }
}
