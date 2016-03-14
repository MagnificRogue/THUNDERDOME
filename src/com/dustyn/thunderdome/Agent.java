/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dustyn.thunderdome;

import com.dustyn.thunderdome.graphics.Renderable;
import java.awt.Point;

/**
 *
 * @author MRogue
 */
public class Agent implements Renderable{

    private int[][] sprite;
    private Point origin;
    private Point bound;
    private Point center;
    
    public Agent() {
        this.sprite = new int[6][6];
        for(int i=0; i < 5; i++) {
            for(int j=0; j < 5; j++) {
                sprite[i][j] = 0x000000;
            }
        }
           
        this.origin = new Point(0,0);
        this.bound = new Point(5,5);
    
        this.center = new Point((origin.x+bound.x)/2,(origin.y+bound.y)/2);
        
    }

    Agent(Point locationOnScreen) {
        this();
        this.setCenter(locationOnScreen);
    }
    
    
    @Override
    public int[][] getSprite() {
        return sprite;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public Point getBounds() {
        return bound;
    }

    /**
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(Point center) {
        this.center = center;
        origin.x = (int) (center.x - 3);
        origin.y = (int) (center.y - 3);
        
        bound.x = center.x + 3;
        bound.y = center.y + 3;
        
    }

    void aStar(Map instance, Item item) {
        System.out.println("I am doing AStar!");
    }
    
}
