package com.bubblerunner.game.utils;

/**
 * Created by Mario on 31/05/2017.
 */

public class Point<T> {

    private T x;
    private T y;

    public Point(T x, T y){
        this.x = x;
        this.y = y;
    }

    public T getX(){
        return this.x;
    }

    public T getY(){
        return this.y;
    }

}
