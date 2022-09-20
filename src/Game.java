import panels.GameFrame;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Game {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new GameFrame();
    }
}
