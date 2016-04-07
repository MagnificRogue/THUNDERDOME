/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dustyn.thunderdome;

import com.dustyn.thunderdome.graphics.Renderable;
import com.dustyn.thunderdome.graphics.Sprite;
import java.awt.Point;

/**
 *
 * @author MRogue
 */
class Item implements Renderable{

    Sprite sprite;
    private Point center;
    
    public Item() {
        this.sprite = Sprite.getItemSprite();
    }
    
    public Item(Point p) {
        this();
        this.center = p;
        sprite.setCenter(p);
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
        sprite.setCenter(center);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
