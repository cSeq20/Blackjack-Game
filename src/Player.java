
import javax.swing.JOptionPane;

/**
 * This class creates a player object that stores the name, money, amount bet
 * and the total value of the cards that are drawn.
 *
 * @author Chris Sequeira & Kelvin Raju
 *
 *
 */
public class Player {

    private String firstname;
    private String lastname;
    private int money; // amount of money user has
    private int totValue; // value of all cards drawn
    private int betMoney; // variable to store amount bet

    /**
     * Sets up a player object. Money is always set to $100
     *
     * @param firstname of the player
     * @param lastname of the player
     */
    public Player(String firstname, String lastname) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setMoney(100);
    }

    // Default constructor
    public Player() {
        this.setMoney(100); // player starts with $100
    }

    /**
     * Method which sets the bet amount of the player
     *
     * @return the value of the bet.
     */
    public int betting() {
        this.betMoney = 0;
        boolean repeat = true;

        do {
            try {
                betMoney = Integer.parseInt(JOptionPane.showInputDialog("How much would you like to bet?"));

                if (betMoney < 10 || betMoney > this.getMoney()) {
                    JOptionPane.showMessageDialog(null, "You have bet the incorrect amount. Try again.",
                            "ERROR: Invalid input", JOptionPane.ERROR_MESSAGE);

                } else if (betMoney <= this.getMoney() && betMoney >= 10) {
                    this.money -= betMoney;
                    repeat = false;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Integers only Please", "Error!", JOptionPane.ERROR_MESSAGE);

            }

        } while (repeat);

        return betMoney;
    }

    /**
     *
     * @return the first name of player
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname set first name of player.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return the last name of player.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname set last name of player.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return the money of the player.
     */
    public int getMoney() {
        return money;
    }

    /**
     *
     * @param money sets money for the player.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     *
     * @return value of all cards drawn.
     */
    public int getTotValue() {
        return totValue;
    }

    /**
     *
     * @param totValue of the cards drawn.
     */
    public void setTotValue(int totValue) {
        this.totValue = totValue;
    }

    /**
     *
     * @return the bet amount.
     */
    public int getBetMoney() {
        return betMoney;
    }

    /**
     *
     * @param betMoney set the bet for the game.
     */
    public void setBetMoney(int betMoney) {
        this.betMoney = betMoney;
    }

    @Override
    public String toString() {
        return this.getFirstname() + " " + this.getLastname() + " , \tYou have $" + this.getMoney();
    }

}
