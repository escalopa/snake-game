package panels;

import map.HardMap;
import map.NormalMap;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


public class GameFrame  implements MouseListener {

    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 800;

    ImageIcon wallpaper = new ImageIcon(("Wallpaper.jpg"));
    JLabel background = new JLabel(wallpaper);

    public static JFrame lobby= new JFrame();
    JButton buttonEasy = new JButton(" Easy");
    JButton buttonNormal = new JButton(" Normal");
    JButton buttonHard = new JButton(" Hard");
    static AudioInputStream audioStream;
    static File file;
    static Clip clip;


    public GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        playBackgroundMusic("Intergalactic Odyssey.wav");

        //this.add(new GamePanel());
        lobby.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        lobby.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lobby.setResizable(false);
        lobby.setTitle("Snake Game");

        addButtonProperties(buttonEasy);
        addButtonProperties(buttonNormal);
        addButtonProperties(buttonHard);

        buttonEasy.setBounds(400,200,150,30);
        buttonNormal.setBounds(400,400,150,30);
        buttonHard.setBounds(400,600,150,30);


        lobby.add(buttonEasy);
        lobby.add(buttonNormal);
        lobby.add(buttonHard);

        lobby.add(background);

        lobby.setVisible(true);

    }


    public static void playBackgroundMusic(String musicName) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        file = new File(musicName);
        audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void playButtonSound(String musicName) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        file = new File(musicName);
        audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }

    public void addButtonProperties(JButton jButton) {

        jButton.setFont(new Font("Ink Free", Font.BOLD, 30));
        jButton.setForeground(Color.ORANGE);
        jButton.setPreferredSize(new Dimension(80, 30));
        jButton.addMouseListener(this);
        jButton.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //lobby.setVisible(false);
        try {
            playButtonSound("ClickSound.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
            ioException.printStackTrace();
        }

        if (e.getSource().equals(buttonEasy)) {
//            GamePanel gamePanel = new GamePanel();
//            lobby.add(gamePanel.originalMap);
//            lobby.invalidate();
//            lobby.validate();
//            lobby.repaint();
//            gamePanel.originalMap.requestFocusInWindow();
            //SwingUtilities.updateComponentTreeUI(lobby);
        } else if (e.getSource().equals(buttonNormal)) {
            lobby.add(new NormalMap());
        } else {
            lobby.add(new HardMap());
        }
        lobby.remove(buttonEasy);
        lobby.remove(buttonNormal);
        lobby.remove(buttonHard);
        //lobby.setVisible(true);


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        try {
            playButtonSound("HoverSound.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
            ioException.printStackTrace();
        }
        if (e.getSource().equals(buttonEasy)) {
            buttonEasy.setForeground(Color.DARK_GRAY);
            //buttonEasy.setBounds(350, 150, 250, 70);
        } else if (e.getSource().equals(buttonNormal)) {
            buttonNormal.setForeground(Color.DARK_GRAY);
            //buttonNormal.setBounds(350, 350, 250, 70);

        } else {
            buttonHard.setForeground(Color.DARK_GRAY);
            //buttonHard.setBounds(350, 450, 250, 70);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(buttonEasy)) {
            buttonEasy.setForeground(Color.ORANGE);
            //buttonEasy.setBounds(400, 200, 200, 50);
        } else if (e.getSource().equals(buttonNormal)) {
            buttonNormal.setForeground(Color.ORANGE);
            //buttonNormal.setBounds(400, 400, 200, 50);

        } else {
            buttonHard.setForeground(Color.ORANGE);
            //buttonHard.setBounds(400, 600, 200, 50);
        }
    }
}
