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
 * Created by Mario on 03/06/2017.
 */

public class Highscores implements Serializable {

    private Map<Integer, String > highscores;

    public Highscores(){
        highscores = new HashMap<Integer, String>();
    }

    public void updateHighscores(String nome, int score) {

        highscores.put(score,nome);

        Map<Integer, String> sorted = sortByKeys(highscores);
        highscores = sorted;

        if(highscores.size() > HIGHSCORES_MAX_PLAYERS) {
            removeOverflow();
        }
    }

    public void removeOverflow(){
            Iterator<Integer> it = highscores.keySet().iterator();
            Integer key = null;

            while(it.hasNext())
                key = it.next();

            highscores.remove(key);
    }

    public Map<Integer, String > getMap(){return this.highscores;}


    public static <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        Collections.sort(keys, Collections.<K>reverseOrder());

        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }

        return sortedMap;
    }

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
