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
class Item implements Renderable{

    int[][] sprite;
    private Point origin;
    private Point bounds;
    private Point center;
    
    public Item() {
        this.sprite = new int[4][4];
        
        for(int i=0; i < 4; i++){
            for(int j=0; j < 4; j++) {
                sprite[i][j] = 0xFF00FF;
            }
        }
             
        
        this.origin = new Point(0,0);
        this.bounds = new Point(4,4);
    }
    
    public Item(Point p) {
        this();
        this.setCenter(p);
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
        return bounds;
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
        origin.x = (int) (center.x - 2);
        origin.y = (int) (center.y - 2);
        
        bounds.x = center.x + 2;
        bounds.y = center.y + 2;
        
    }
}
