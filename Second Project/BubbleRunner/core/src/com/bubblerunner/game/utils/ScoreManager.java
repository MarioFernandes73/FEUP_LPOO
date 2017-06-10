package com.bubblerunner.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Score manager which handles loads and saves of the highscores.dat file.
 */
public class ScoreManager {

    /**
     * Highscores object containing the list of the players
     * which are currently on the highscores table.
     */
    private Highscores highscores;

    /**
     * Highscores file "highscores.dat".
     */
    private FileHandle file;

    /**
     * Constructs a new Score manager loading the map into the highscores's map.
     */
    public ScoreManager() {

        highscores = new Highscores();

        try {
            readHighscores();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deserialize the highscores.dat file.
     *
     * @throws IOException if operation has failed
     */
    private void readHighscores() throws IOException {

        if (Gdx.files.local("highscores.dat").exists()) {

            ObjectInputStream is = null;
            file = Gdx.files.local("highscores.dat");

            try {
                is = new ObjectInputStream(file.read());
                highscores = (Highscores) is.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null)
                    is.close();
            }
        }
    }

    /**
     * Serializes the highscores object.
     *
     * @throws IOException if operation has failed
     */
    public void saveHighscores() throws IOException {

        file = Gdx.files.local("highscores.dat");

        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(file.write(false));
            os.writeObject(highscores);
        } finally {
            if (os != null)
                os.close();
        }
    }

    /**
     * Returns the highscores and makes sure it is up to date
     * by reading the file once again.
     *
     * @return The highscores object.
     */
    public Highscores getHighscores() {
        try {
            readHighscores();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.highscores;
    }

    /**
     * Wrapper for the highscores toString method.
     *
     * @return highscores map in String form.
     */
    public String displayHighscores() {
        return highscores.toString();
    }

}
