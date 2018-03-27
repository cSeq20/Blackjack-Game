
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * Background class which represents the background used for the frames. Has a
 * override method to set the size of the background image to the frame size
 *
 * @author Chris Sequeira & Kelvin Raju
 */
public class Background extends JComponent {

    private final Image image;

    /**
     * Constructor to initialize the object.
     *
     * @param backImg used to get the location of the image.
     */
    public Background(String backImg) {
        image = Toolkit.getDefaultToolkit().getImage(backImg);
    }

    /**
     * Method that sets the size of the image to the size of the frame
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
