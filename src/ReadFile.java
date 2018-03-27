
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * This class has a method to read a text file. it is used for rules.
 *
 * @author Chris Sequeira & Kelvin Raju
 *
 */
public class ReadFile {

    /**
     * Opens a new frame with a text file in a JTextArea.
     *
     * @param file the location of the file to open.
     * @throws IOException if there is no file present.
     */
    public ReadFile(String file) throws IOException {

        JFrame rules = new JFrame("Blackjack");
        rules.setSize(670, 650);

        JTextArea showFile = new JTextArea();

        try {
            showFile.read(new FileReader(file), null);
        } catch (IOException e) {
            System.out.println("No File Found!!");
        }
        rules.add(showFile);

        rules.setVisible(true);

    }
}
