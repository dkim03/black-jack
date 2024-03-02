/**
 * DESC: this class is used to hold data for a single card. data includes the id
 * of the card (to determine if it is a face card like jack, queen, king), the
 * suit, and whether the card is hidden or not.
 * @author David Kim (CSS 142)
 * @version 5/29/23
 */

public class Card {
    private int cardID = 0;
    private char cardSuit = 'X';
    private boolean isHidden = false;

    /**
     * constructor used to create a card object and initialize the id and suit  
     * of the card.
     * @param id        the card id (should only be 1 to 14).
     * @param suit      the suit of the card (should only be a C, S, H, or D).
     */
    public Card(int id, char suit) {
        this.cardID = id;
        this.cardSuit = suit;
    }

    /**
     * getter method used to get the value of the cards according to blackjack
     * rules.
     * @return          the value of the card based on its id, an int.
     */
    public int getCardValue() {
        // if jack, queen, or king, return 10 as its value
        if (this.cardID == 11 ||
            this.cardID == 12 ||
            this.cardID == 13)
            return 10;
        return this.cardID;
    }

    /**
     * getter method used to get this card's id.
     * @return          this card's defined id, an int.
     */
    public int getCardID() {
        return this.cardID;
    }

    /**
     * getter method used to get this card's suit.
     * @return          this card's defined suit, a char.
     */
    public char getCardSuit() {
        return this.cardSuit;
    }

    /**
     * getter method used to get this card's visibility.
     * @return          this card's defined visibility, a boolean.
     */
    public boolean getVisibility() {
        return this.isHidden;
    }

    /**
     * setter method used to define a new id for this card.
     * @param id        new int to replace this card's id.
     */
    public void setCardID(int id) {
        this.cardID = id;
    }

    /**
     * setter method used to define a new suit for this card.
     * @param suit      new char to replace this card's suit.
     */
    public void setCardSuit(char suit) {
        this.cardSuit = suit;
    }

    /**
     * setter method used to define the visibility of this card.
     * @param isHidden  boolean to set whether or not this card is hidden.
     */
    public void setVisibility(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * setter method used to define both the id and suit for this card.
     * @param id        new int to replace this card's id.
     * @param suit      new char to replace this card's suit.
     */
    public void setIDAndSuit(int id, char suit) {
        this.cardID = id;
        this.cardSuit = suit;
    }

    /**
     * method used to compare and check whether this card is the same as another
     * card.
     * @param crd       the other card to compare this card to.
     * @return          true if the given card is the same as this one. false if
     *                  it is different.
     */
    public boolean isDuplicate(Card crd) {
        return (crd.getCardID() == this.cardID &&
            crd.getCardSuit() == this.cardSuit);
    }
}
