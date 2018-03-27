
import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kelvin Raju & Chris Sequeira
 */
public class CardsTest {

    @Test
    public void CardtestRandomNum() {
        Cards cards = new Cards();
        int num;
        Random ranNum = new Random();

        num = ranNum.nextInt(13 - 1 + 1) + 1;

        assertTrue(num <= 14);

    }
}
