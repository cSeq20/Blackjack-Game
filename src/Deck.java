
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class which implements the strategy of the blackjack game. Has a card object
 * in order to draw cards.
 *
 * @author Chris Sequeira & Kelvin Raju
 */
public class Deck {

    public Cards card;

    public Deck() {

        this.card = new Cards();

    }

    /**
     * Method that draws the initial two cards for the player.
     *
     * @param play to set the card totals to the player.
     * @param pValue to show the the value of the cards on the playframe object.
     * @param pCards to show the cards drawn for player in a panel.
     */
    public void playerDeal(Player play, JLabel pValue, JPanel pCards) {
        this.hit();
        pCards.add(new JLabel(new ImageIcon(card.getValue() + card.getSuit() + ".gif")));
        play.setTotValue(play.getTotValue() + card.getValue());
        this.hit();
        pCards.add(new JLabel(new ImageIcon(card.getValue() + card.getSuit() + ".gif")));
        play.setTotValue(play.getTotValue() + card.getValue());
        pValue.setText("Player Card Total: " + play.getTotValue()); // card
        // total.

    }

    /**
     * Method that draws the initial two cards for the dealer.
     *
     * @param deal to set the card values to the dealer.
     * @param dValue to show the the value of the cards on the playframe object.
     * @param dCards to show the cards drawn for dealer in a panel.
     */
    public void dealerDeal(Dealer deal, JLabel dValue, JPanel dCards) {
        this.hit();
        dCards.add(new JLabel(new ImageIcon(card.getValue() + card.getSuit() + ".gif")));
        deal.setTotalVal(deal.getTotalVal() + card.getValue());
        this.hit();
        dCards.add(new JLabel(new ImageIcon(card.getValue() + card.getSuit() + ".gif")));
        deal.setTotalVal(deal.getTotalVal() + card.getValue());
        dValue.setText("Dealer Card Total: " + deal.getTotalVal()); // card
        // total.
    }

    /**
     * Method which first calls the playerDeal() and dealerDeal() methods. Then
     * presents an option to hit or stand. If hit draws another card, if stand
     * stops the game.
     *
     * @param p1 sets card values to player
     * @param d1 set card values to dealer
     * @param pValue show total value of cards of player in playframe.
     * @param dValue show total value of cards of dealer in playframe
     * @param player display the cards drawn for player in the playframe
     * @param dealer display the cards drawn for dealer in the playframe
     */
    public void dealCards(Player p1, Dealer d1, JLabel pValue, JLabel dValue, JPanel player, JPanel dealer) {

        String[] options = {"Hit", "Stand"};
        int n = 0;
        // player initial cards:
        this.playerDeal(p1, pValue, player);
        this.dealerDeal(d1, dValue, dealer);

        while (p1.getTotValue() < 21 || d1.getTotalVal() < 21) {

            if (p1.getTotValue() >= 21 || d1.getTotalVal() >= 21) {
                break;
            }

            n = JOptionPane.showOptionDialog(null, "Hit or Stand", "Continue", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (n == 1) {
                break;
            } else if (n == 0) {
                this.hit();
                player.add(new JLabel(new ImageIcon(card.getValue() + card.getSuit() + ".gif")));
                p1.setTotValue(p1.getTotValue() + card.getValue());
                pValue.setText("Player Card Total: " + p1.getTotValue());
            }
        }
        if (d1.getTotalVal() < 17 && p1.getTotValue() < 21) {
            this.hit();
            dealer.add(new JLabel(new ImageIcon(card.getValue() + card.getSuit() + ".gif")));
            d1.setTotalVal(d1.getTotalVal() + card.getValue());
            dValue.setText("Dealer Card Total: " + d1.getTotalVal());
        }

    }

    /**
     * Method which checks to see the winner of the game using the playertotal
     * and dealer total of the cards drawn.
     *
     * @param p1 uses the playerTotal to compare which has the closest to 21.
     * @param d1 uses the dealerTotal to compare which has the closest to 21.
     * @param money resets the money of player depending on the result.
     */
    public void gameResult(Player p1, Dealer d1, JLabel money) {

        String wMessage = "Congratulations " + p1.getFirstname() + " " + p1.getLastname() + "\n You are the Winner!!! ";
        String lMessage = "Unlucky!!!!" + p1.getFirstname() + " " + p1.getLastname()
                + "\n The Dealer won better luck next time!!!";

        if (p1.getTotValue() == 21) {
            p1.setMoney(p1.getMoney() + (p1.getBetMoney() * 2));
            money.setText("Your balance is: " + p1.getMoney());
            JOptionPane.showMessageDialog(null, wMessage, "Winner", JOptionPane.PLAIN_MESSAGE);

        } else if (d1.getTotalVal() == 21) {
            money.setText("Your balance is: " + p1.getMoney());
            JOptionPane.showMessageDialog(null, lMessage, "Loser", JOptionPane.PLAIN_MESSAGE);

        } else if (p1.getTotValue() < 21 && p1.getTotValue() > d1.getTotalVal()) {
            p1.setMoney(p1.getMoney() + (p1.getBetMoney() * 2));
            money.setText("Your balance is: " + p1.getMoney());
            JOptionPane.showMessageDialog(null, wMessage, "Winner", JOptionPane.PLAIN_MESSAGE);

        } else if (p1.getTotValue() > 21 && p1.getTotValue() < d1.getTotalVal()) {
            p1.setMoney(p1.getMoney() + (p1.getBetMoney() * 2));
            money.setText("Your balance is: " + p1.getMoney());
            JOptionPane.showMessageDialog(null, wMessage, "Winner", JOptionPane.PLAIN_MESSAGE);

        } else if (d1.getTotalVal() > 21 && d1.getTotalVal() > p1.getTotValue()) {

            p1.setMoney(p1.getMoney() + p1.getBetMoney() * 2);
            money.setText("Your balance is: " + p1.getMoney());
            JOptionPane.showMessageDialog(null, wMessage, "Winner", JOptionPane.PLAIN_MESSAGE);

        } else {
            money.setText("Your balance is: " + p1.getMoney());
            JOptionPane.showMessageDialog(null, lMessage, "Loser", JOptionPane.PLAIN_MESSAGE);

        }
    }

    /**
     * Method to draw a card.
     */
    public void hit() {
        this.card.drawCard();

    }

}
