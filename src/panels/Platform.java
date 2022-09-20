package panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Platform implements ActionListener {

    static final int UNIT_SIZE=(20);
    static int xSnake[]= new int[(600*600/(UNIT_SIZE*UNIT_SIZE))];
    static int ySnake[]= new int[(600*600/(UNIT_SIZE*UNIT_SIZE))];

    static int appleX;
    static int appleY;

    public static void startGame(){

    }

    public static void formApple(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
