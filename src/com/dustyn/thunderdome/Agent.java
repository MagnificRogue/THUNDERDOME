/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dustyn.thunderdome;

import com.dustyn.thunderdome.graphics.Renderable;
import java.awt.Point;
import java.util.Stack;
/**
 *
 * @author MRogue
 */
public class Agent implements Renderable{

    private int speed;
    private int[][] sprite;
    private Point origin;
    private Point bound;
    private Point center;
    private int color;
    
    public Agent() {
        color = 0x000000;
        this.sprite = new int[6][6];
        for(int i=0; i < 5; i++) {
            for(int j=0; j < 5; j++) {
                sprite[i][j] = color;
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

 
    
    Boolean pathFound = false;
    Boolean pathInversed = false;
    Stack<Tile> path = new Stack<>();

    private double getFCost( Tile question, Tile beginning, Tile end) {
        double g = Tile.getDistance(question, beginning);
        double h = Tile.getDistance(question, end);

        return g+h;
    }
    
    public void restartAStar() {
        pathFound = false;
        pathInversed = false;
        path = new Stack<>();
    }
    
    public void aStar(Map m, Item item) {
        
        
            
        if(!pathFound) {
            if(path.empty()) {
                   Tile start = m.getTile(this.center);
                   path.push(start);
            }
            if(path.peek().equals(m.getTile(item.getCenter()))) {
                pathFound = true;
                return;
            }


            Tile[] adjacentTiles = m.getAdjacentTiles(path.peek());
            Tile lowestCost = adjacentTiles[0];
            for(Tile t: adjacentTiles) {
                if(getFCost(t,path.peek(),m.getTile(item.getCenter())) < getFCost(lowestCost,path.peek(),m.getTile(item.getCenter()))) {
                    lowestCost = t;
                }
            }
            path.push(lowestCost);
            return;
     
        } else if (! pathInversed) {
            
            Stack<Tile> newPath = new Stack<>();
            
            while(!path.empty()) {
                newPath.push(path.pop());
            }
            
            path = newPath;
            pathInversed = true;
            
        }   
        
        
        if(pathFound && pathInversed && !path.empty()){
            Tile top = path.peek();
            this.setCenter(new Point((top.center.x + this.center.x )/2, (top.center.y + this.center.y)/2));

            if(m.getTile(this.center).equals(top)) {
                path.pop();
            }
        } else if (path.empty()) {
            m.generateNewItem();
            pathFound = pathInversed = false;
        }
            

    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
        
        for(int i=0; i < 5; i++) {
            for(int j=0; j < 5; j++) {
                sprite[i][j] = color;
            }
        }

    }
    
}
