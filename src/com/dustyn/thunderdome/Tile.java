package com.dustyn.thunderdome;

import com.dustyn.thunderdome.graphics.Renderable;
import com.dustyn.thunderdome.graphics.Sprite;
import java.awt.Point;

/**
 *
 * @author MRogue
 */
public class Tile implements Renderable{

    static double getDistance(Tile a, Tile b) {
        
        if(!a.isTraversable || ! b.isTraversable) {
            return Double.MAX_VALUE;
        }
        
        return Math.sqrt(
                
            Math.pow((a.getCenter().x - b.getCenter().x),2) + 
            Math.pow((a.getCenter().y - b.getCenter().y),2)
        
        );
    }

    private Sprite sprite;
    Point origin, bounds, center;
    private int color;
    public Tile parent;
    
    public boolean isTraversable;
    
    Tile(Point origin, Point bounds) {
        this();
        this.center = new Point((origin.x + bounds.x)/2,(origin.y + bounds.y)/2);
        this.sprite = new Sprite(center, Sprite.tileSprite(Map.tileWidth));
    }

    public void setColor(int color) {
        this.color = color;
        sprite.setColor(color);
    }

    
    public Tile() {
        this.sprite = new Sprite(new Point(0,0), Sprite.tileSprite(Map.tileWidth));
        this.isTraversable = true;
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

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    
}