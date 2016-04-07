/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dustyn.thunderdome;

import com.dustyn.thunderdome.graphics.Renderable;
import com.dustyn.thunderdome.graphics.Sprite;
import java.awt.Point;
import java.util.Stack;
/**
 *
 * @author MRogue
 */
public class Agent implements Renderable{

    private int speed;
    private Sprite sprite;
    private Point origin;
    private Point bound;
    private Point center;
    private int color;

    public Agent() {
        speed = 1;
        color = 0x000000;
        this.sprite = Sprite.getAgentSprite();
        
    }

    Agent(Point locationOnScreen) {
        this();
        this.setCenter(locationOnScreen);
    }
    
    
    @Override
    public Sprite getSprite() {
        return sprite;
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

 
    
    Boolean pathFound = false;
    Boolean pathInversed = false;
    Stack<Tile> path = new Stack<>();

    private double getFCost( Tile question, Tile beginning, Tile end) {
        double g = Tile.getDistance(question, beginning);
        double h = Tile.getDistance(question, end);
        return g+h;
    }
    
    public void restartAStar() {
        for(Tile t : path) {
            t.setColor(0xFFFFFF);
        }
        pathFound = false;
        pathInversed = false;
        path = new Stack<>();
    }
    
    public void aStar(Map m, Item item) {
        
        
            
        if(!pathFound) {
          //  for(int i =0; i < speed; i++){
              // while(!pathFound) {
            
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
                //t.setColor(0xFF00FF);
                if(getFCost(t,path.peek(),m.getTile(item.getCenter())) < getFCost(lowestCost,path.peek(),m.getTile(item.getCenter()))) {
                    lowestCost = t;
                }
            }
            path.peek().setColor(0xFFFFFF);
            lowestCost.setColor(this.color);
            path.push(lowestCost);
           // }
            //return;
                //}
        } else if (! pathInversed) {
            
            Stack<Tile> newPath = new Stack<>();
            
            while(!path.empty()) {
                newPath.push(path.pop());
            }
            
            path = newPath;
            pathInversed = true;
            
        }   
        
        
        if(pathFound && pathInversed && !path.empty()){
            Tile current = m.getTile(this.center);
            Tile top = path.peek();
            this.setCenter(new Point((top.center.x + this.center.x  )/2, (top.center.y + this.center.y)/2));

            if(m.getTile(this.center).equals(top)) {
                path.pop();
            }
        } else if (path.empty()) {
            grow();
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
        this.sprite.setColor(color);

    }

    private void grow() {
            speed++;
    }
    
}
