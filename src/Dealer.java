
/**
 * Dealer class that has a variable to store the total
 * value of the cards drawn for the dealer.
 *
 * @author Chris Sequeira & Kelvin Raju
 *
 */
public class Dealer {

    private int totalVal;

    // default constructor
    public Dealer() {

    }

    /**
     *
     * @param total to set the card total for the dealer.
     */
    public Dealer(int total) {
        this.setTotalVal(total);
    }

    /**
     *
     * @return the total value of cards drawn.
     */
    public int getTotalVal() {
        return totalVal;
    }

    /**
     *
     * @param totalVal to set the value of cards drawn
     */
    public void setTotalVal(int totalVal) {
        this.totalVal = totalVal;
    }

    @Override
    public String toString() {
        return "Dealer total is: " + this.getTotalVal();
    }

}
