
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents the frame that users first see when starting the game
 * It has four buttons: play, rules, high scores and quit.
 *
 * @author Chris Sequeira & Kelvin Raju
 */
public class BlackJackFrame extends JPanel implements ActionListener {

    private JFrame frame;
    private JButton play;
    private JButton rules;
    private JButton highscores;
    private JButton quit;
    public Background image;

    /**
     * Create the application.
     */
    public BlackJackFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame. This includes setting up the frame,
     * background and the buttons within the frame
     */
    private void initialize() {
        this.frame = new JFrame();
        this.frame.setSize(1360, 730);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(null);
        // initialize buttons
        this.play = new JButton("Play");
        this.play.addActionListener(this);
        this.rules = new JButton("Rules");
        this.rules.addActionListener(this);
        this.highscores = new JButton("Highscores");
        this.highscores.addActionListener(this);
        this.quit = new JButton("Quit");
        this.quit.addActionListener(this);
        // button colors:
        this.play.setBackground(Color.ORANGE);
        this.rules.setBackground(Color.ORANGE);
        this.highscores.setBackground(Color.ORANGE);
        this.quit.setBackground(Color.ORANGE);
        // button fonts:
        this.play.setFont(new Font("Snap ITC", Font.BOLD, 18));
        this.rules.setFont(new Font("Snap ITC", Font.BOLD, 18));
        this.highscores.setFont(new Font("Snap ITC", Font.BOLD, 18));
        this.quit.setFont(new Font("Snap ITC", Font.BOLD, 18));
        // Button bounds:
        this.play.setBounds(329, 613, 126, 40);
        this.rules.setBounds(521, 613, 126, 40);
        this.highscores.setBounds(711, 613, 164, 40);
        this.quit.setBounds(942, 613, 126, 40);
        // add buttons to frame:
        this.frame.getContentPane().add(play);
        this.frame.getContentPane().add(rules);
        this.frame.getContentPane().add(highscores);
        this.frame.getContentPane().add(quit);
        // background image:
        this.image = new Background("BlackJBackground.png");
        this.image.setBounds(0, 0, 1350, 729);
        this.frame.getContentPane().add(image);

        this.frame.setVisible(true);
    }

    /**
     * This sets the action whenever a button is pressed.
     *
     * @param e sets the action of the button. Play initializes a new playframe
     * object and plays the game. Rules opens up the rules. Highscore shows the
     * scores and quit exits the program.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == rules) {
            try {
                ReadFile rule = new ReadFile("Rules.txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        if (src == play) {
            frame.setVisible(false);
            PlayFrame pf = new PlayFrame();

        }
        if (src == highscores) {
            Highscore hs = new Highscore();
            hs.connectHighscoreDB();
            hs.display();

        }
        if (src == quit) {
            System.exit(0);
        }

    }
}
