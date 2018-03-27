
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Class which sets up a new frame and plays the blackjack game.
 *
 * @author Chris Sequeira & Kelvin Raju.
 */
public class PlayFrame {

    public Player player;
    public Dealer dealer;
    public Cards c1;
    public Cards deal;
    public Deck deck;
    public Highscore storeScore;
    public JFrame frame;
    public JPanel playerPan;
    public JPanel dealerPan;
    public Background table;

    /**
     * Create the application.
     */
    public PlayFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame and play the game.
     */
    private void initialize() {
        this.dealer = new Dealer();
        this.player = new Player("Chris", "Seq");
        this.c1 = new Cards();
        this.deal = new Cards();
        this.deck = new Deck();
        this.storeScore = new Highscore();
        storeScore.connectHighscoreDB();
        String fName = "";
        String lName = "";
        int playAgain = 0; // for the JOptionpane
        boolean nameChecker = true;
        // frame:
        this.frame = new JFrame();
        this.frame.setSize(1360, 730);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(null);
        // panels:
        this.playerPan = new JPanel();
        this.playerPan.setBounds(39, 262, 472, 230);
        this.playerPan.setBackground(new Color(34, 139, 34));
        this.playerPan.setOpaque(true);
        this.playerPan.setBorder(new LineBorder(new Color(192, 192, 192), 3, true));
        // to hold dealer cards
        this.dealerPan = new JPanel();
        this.dealerPan.setBounds(800, 262, 472, 230);
        this.dealerPan.setBackground(new Color(34, 139, 34));
        this.dealerPan.setOpaque(true);
        this.dealerPan.setBorder(new LineBorder(new Color(192, 192, 192), 3, true));
        // to hold player details
        JPanel detailsHeader = new JPanel();
        detailsHeader.setBounds(20, 22, 1300, 103);
        detailsHeader.setLayout(new BorderLayout(0, 0));
        detailsHeader.setBackground(new Color(210, 105, 30));
        // Labels and locations:
        JLabel playerDet = new JLabel("Player Name: ");
        JLabel playerMoney = new JLabel("Money: ");
        JLabel displayLbl = new JLabel("");
        displayLbl.setBounds(0, 0, 1344, 146);
        JLabel pCardTotal = new JLabel("Player Card Total:");
        pCardTotal.setBounds(39, 607, 317, 45);
        JLabel dCardTotal = new JLabel("Dealer Card Total:");
        dCardTotal.setBounds(939, 607, 317, 45);
        // label fonts, etc:
        playerDet.setFont(new Font("Cambria", Font.BOLD, 35));
        playerMoney.setFont(new Font("Cambria", Font.BOLD, 35));
        displayLbl.setBackground(new Color(139, 69, 19));
        displayLbl.setOpaque(true);
        displayLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        pCardTotal.setForeground(new Color(0, 0, 0));
        pCardTotal.setFont(new Font("Cambria", Font.BOLD, 25));
        dCardTotal.setForeground(Color.BLACK);
        dCardTotal.setFont(new Font("Cambria", Font.BOLD, 25));

        detailsHeader.add(playerDet, BorderLayout.WEST);
        detailsHeader.add(playerMoney, BorderLayout.EAST);

        this.frame.getContentPane().add(detailsHeader);
        this.frame.getContentPane().add(displayLbl);
        this.frame.getContentPane().add(pCardTotal);
        this.frame.getContentPane().add(dCardTotal);
        this.frame.getContentPane().add(playerPan);
        this.frame.getContentPane().add(dealerPan);

        this.table = new Background("tableBackground.jpeg");
        this.table.setBounds(0, 0, 1350, 729);
        this.frame.getContentPane().add(table);

        this.frame.setVisible(true);

        do {
            fName = JOptionPane.showInputDialog("Enter First Name: ");
            lName = JOptionPane.showInputDialog("Enter Last Name: ");

            if (fName == null || lName == null || fName.length() == 0 || lName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Please input!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else if (stringChecker(fName) || stringChecker(lName)) {
                JOptionPane.showMessageDialog(null, "Letters only Please!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                nameChecker = false;
            }

        } while (nameChecker);

        this.player = new Player(fName, lName);
        playerMoney.setText("Money: " + player.getMoney());
        playerDet.setText("Player: " + player.getFirstname() + " " + player.getLastname());

        while (playAgain == 0) {
            this.player.betting();
            playerMoney.setText("Money: " + player.getMoney());

            this.deck.dealCards(player, dealer, pCardTotal, dCardTotal, playerPan, dealerPan);
            this.deck.gameResult(player, dealer, playerMoney);

            if (player.getMoney() < 10) {
                System.exit(0);
            }

            playAgain = JOptionPane.showConfirmDialog(null, "Bet Again", "Play Again", JOptionPane.YES_NO_OPTION);
            if (playAgain == 0) {
                this.player.setTotValue(0);
                this.dealer.setTotalVal(0);
                this.player.setBetMoney(0);
                this.playerPan.removeAll();
                this.playerPan.updateUI();
                this.dealerPan.removeAll();
                this.dealerPan.updateUI();
                this.frame.revalidate();

            } else if (playAgain == 1) {
                storeScore.add(player.getFirstname(), player.getLastname(), player.getMoney());
                System.exit(0);
            }
        }
    }

    /**
     * Method to check if the string contains only letters
     *
     * @param input the string to check
     * @return true if it the input string is not all letters.
     */
    public boolean stringChecker(String input) {
        return !input.matches("[a-zA-Z]+");
    }

}
