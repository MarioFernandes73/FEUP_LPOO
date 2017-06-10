package com.bubblerunner.game.utils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.bubblerunner.game.utils.Constants.HIGHSCORES_INITIAL_POS;
import static com.bubblerunner.game.utils.Constants.HIGHSCORES_MAX_PLAYERS;


/**
 * Highscores class which holds the player's highscores session.
 */
public class Highscores implements Serializable {

    /**
     * Map containing the name's of the players and their scores.
     */
    private Map<Integer, String > highscores;

    /**
     * Constructs a new Highscores object.
     */
    public Highscores(){
        highscores = new HashMap<Integer, String>();
    }

    /**
     * Updates the highscores table with a new entry or substitutes
     * another one if the limit has been reached.
     *
     * @param nome the name of the player
     * @param score the score of the player
     */
    public void updateHighscores(String nome, int score) {

        highscores.put(score,nome);

        Map<Integer, String> sorted = sortByKeys(highscores);
        highscores = sorted;

        if(highscores.size() > HIGHSCORES_MAX_PLAYERS) {
            removeOverflow();
        }
    }

    /**
     * Removes the entry with the lowest score.
     */
    public void removeOverflow(){
            Iterator<Integer> it = highscores.keySet().iterator();
            Integer key = null;

            while(it.hasNext())
                key = it.next();

            highscores.remove(key);
    }

    /**
     * Returns the highscores map.
     *
     * @return The highscores map refering to the user's session.
     */
    public Map<Integer, String > getMap(){return this.highscores;}

    /**
     * Template comparable method.
     *
     * @param map highscores map.
     * @param <K> highscores map's score part
     * @param <V> highscores map's name part
     * @return
     */
    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        Collections.sort(keys, Collections.<K>reverseOrder());

        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }

        return sortedMap;
    }

    /**
     * Formats the highscores into a String, overridng
     * toString method.
     *
     * @return String based highscores
     */
    public String toString(){

        String text = "";
        int pos = HIGHSCORES_INITIAL_POS;
        Iterator<Integer> it = highscores.keySet().iterator();

        while(it.hasNext()){
            Integer key = it.next();
            text += pos + " : " + key + "  " + highscores.get(key) + "\n";
        }

        return text;
    }

}
