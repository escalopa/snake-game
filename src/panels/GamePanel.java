package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener  {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static final int DELAY = 175;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    JPanel originalMap;
    //Button replayButton = new JButton("Play again!");

    GamePanel() {
        random = new Random();
        originalMap.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        originalMap.setBackground(Color.black);
        //originalMap.addKeyListener(new MyKeyAdapter());
        originalMap.setFocusable(true);
        //this.requestFocusInWindow();
        startGame();

    }

    public void startGame() {
        newApple();
        bodyParts = 6;
        applesEaten = 0;
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {

            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }

    }

    public void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                //y[0] = y[0] - UNIT_SIZE;
                if (y[0] - UNIT_SIZE >= 0) {
                    y[0] = y[0] - UNIT_SIZE;
                } else {
                    y[0] = SCREEN_HEIGHT;
                }
                break;
            case 'D':
                //y[0] = y[0] + UNIT_SIZE;
                if (y[0] + UNIT_SIZE <= SCREEN_HEIGHT) {
                    y[0] = y[0] + UNIT_SIZE;
                } else {
                    y[0] = 0;
                }
                break;
            case 'L':
                //x[0] = x[0] - UNIT_SIZE;
                if (x[0] - UNIT_SIZE >= 0) {
                    x[0] = x[0] - UNIT_SIZE;
                } else {
                    x[0] = SCREEN_WIDTH;
                }
                break;
            case 'R':
                //x[0] = x[0] + UNIT_SIZE;
                if (x[0] + UNIT_SIZE <= SCREEN_WIDTH) {
                    x[0] = x[0] + UNIT_SIZE;
                } else {
                    x[0] = 0;
                }
                break;
        }

    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        //Game Over text
        //g.setColor(Color.red);
        //g.setFont(new Font("Ink Free", Font.BOLD, 75));
        //FontMetrics metrics2 = getFontMetrics(g.getFont());
        //g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
        startGame();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    class key extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("working");
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_ESCAPE: {
                    originalMap.setVisible(false);
//                    gameOver(g);
                }
            }
        }
    }
}
