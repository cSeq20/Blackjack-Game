
import java.util.Random;

/**
 * Class which creates card object and has methods that randomize the value and
 * suit of the cards.
 *
 * @author Chris Sequeira & Kelvin Raju
 */
public class Cards {

    private int value; // value of a card
    // array of suits
    private static final String[] cardSuits = {"Diamonds", "Hearts", "Spades", "Clubs"};
    private String suit; // to store a suit

    // Constructor
    public Cards() {

    }

    /**
     * Method that randomizes a number between 1 and 13.
     *
     * @return the value to be used for the card value.
     */
    public int randomize() {
        int num;
        Random ranNum = new Random();
        num = ranNum.nextInt(13 - 1 + 1) + 1;

        return num;
    }

    /**
     * Method to randomize the suit of the card.
     *
     * @return the string value of the suit.
     */
    public String randomSuit() {
        Random makeRan = new Random();
        String suit;
        suit = this.cardSuits[makeRan.nextInt(this.cardSuits.length)];

        return suit;
    }

    /**
     * Method which draws a card using the randomize() and randomSuit() methods.
     */
    public void drawCard() {
        this.value = randomize();
        this.suit = randomSuit();

    }

    /**
     *
     * @return the value of the card.
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @return the suit of the card.
     */
    public String getSuit() {
        return suit;
    }

    /**
     *
     * @param value sets the value of the card.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @return how the cards should be displayed (Only used for CUI version).
     */
    @Override
    public String toString() {

        switch (this.value) {
            case 1:
                return "Card drawn is Ace (1) of " + this.suit.toUpperCase();
            case 11:
                return "Card drawn is Jack (11)" + this.suit.toUpperCase();
            case 12:
                return "Card drawn is Queen (12)" + this.suit.toUpperCase();
            case 13:
                return "Card drawn is King (13)" + this.suit.toUpperCase();
            default:
                return "Card drawn is " + this.value + " of " + this.suit.toUpperCase();
        }
    }
}
