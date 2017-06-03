package com.bubblerunner.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Mario on 03/06/2017.
 */

public class ScoreManager {

    private Highscores highscores;
    private FileHandle file;

    public ScoreManager(){

        highscores = new Highscores();

        try {
            readHighscores();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readHighscores() throws IOException {

        if(Gdx.files.local("highscores.dat").exists()) {

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

    public void saveHighscores() throws IOException {

        file = Gdx.files.local("highscores.dat");

        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(file.write(false));
            os.writeObject(highscores);
        }
        finally {
            if (os != null)
                os.close();
        }
    }


    public String displayHighscores(){
        return highscores.toString();
    }

}
