package com.dustyn.thunderdome;

import com.dustyn.thunderdome.graphics.Renderable;
import java.awt.Point;

/**
 *
 * @author MRogue
 */
public class Tile implements Renderable{

    private int[][] tileSprite;
    Point origin, bounds;
    private int color;
    
    Tile(Point origin, Point bounds) {
        this();
        this.origin = origin;
        this.bounds = bounds;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
        for(int i = 0; i < Map.tileWidth; i ++) {
            for(int j =0; j < Map.tileWidth; j++) {
                tileSprite[i][j] = color;
            }
        }        
    }

    
    public Tile() {
        this.tileSprite = new int[Map.tileWidth][Map.tileWidth];
    }
    


    @Override
    public int[][] getSprite() {
        return tileSprite;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public Point getBounds() {
        return bounds;
    }
}