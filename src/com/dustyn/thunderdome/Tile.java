package com.dustyn.thunderdome;

import com.dustyn.thunderdome.graphics.Renderable;
import java.awt.Point;
import java.lang.Math;

/**
 *
 * @author MRogue
 */
public class Tile implements Renderable{

    static double getDistance(Tile c, Tile tile) {
           return Math.sqrt(Math.pow((c.getCenter().x + tile.getCenter().x),2) + Math.pow((c.getCenter().y + tile.getCenter().y),2));
    }

    private int[][] tileSprite;
    Point origin, bounds, center;
    private int color;
    public double G;
    public double H;
    public double F;
    public Tile parent;
    
    Tile(Point origin, Point bounds) {
        this();
        this.origin = origin;
        this.bounds = bounds;
        this.center = new Point((origin.x + bounds.x)/2,(origin.y + bounds.y)/2);
        this.G = this.H = 5000;
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
    
    public Point getCenter() {
        return center;
    }
    
    public boolean contains(Point p) {
        return (origin.x <= p.x && p.x <= bounds.x) &&
               (origin.y <= p.y && p.y <= bounds.y);
    }
    
    public boolean contains(int x, int y) {
        return (origin.x <= x && x <= bounds.x) &&
               (origin.y <= y && y <= bounds.y);
    }

    
}