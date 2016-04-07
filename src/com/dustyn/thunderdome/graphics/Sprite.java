/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dustyn.thunderdome.graphics;

import java.awt.Point;

/**
 *
 * @author dustyn
 */
public class Sprite implements Renderable{
    private int[][] pixels;
    
    private Point origin;
    private Point bound;
    private Point center;
    int width, height; 
    
    public Sprite() {
        this.pixels = new int [1][1];
        pixels[0][0] = 0x000000;
        this.center = new Point(1,1);
        this.bound = this.center;
        this.origin = new Point(0,0);
    }
    
    public Sprite(Point center, int[][] graphic) {
        this.center = center;
        
        width = graphic.length;
        height = graphic[0].length;
        
        this.origin = new Point(center.x-(width/2), center.y-(height/2));
        this.bound = new Point(center.x+(width/2), center.y+(height/2));
                
        this.pixels = graphic;
    }

    
    public void setColor(int color) {
            for(int i=0; i < pixels.length; i++) {
                for(int j=0; j < pixels[i].length; j++) {
                    pixels[i][j] = color;
                }
            }
    }
    
    /**
     * @return this
     */
    @Override
    public Sprite getSprite() {
        return this;
    }

    public int[][] getPixels() {
        return pixels;
    }
    
    
    /**
     * @param pixels the pixels to set
     */
    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

    /**
     * @return the origin
     */
    public Point getOrigin() {
        return origin;
    }

    /**
     * @return the bound
     */
    public Point getBound() {
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
        this.origin = new Point(center.x-(width/2), center.y-(height/2));
        this.bound = new Point(center.x+(width/2), center.y+(height/2));
    }
   
    
    
    
    public static int[][] tileSprite(int size) {
        int[][] graphic = new int[size][size];
        return graphic;
    }
    
    private static int[][] agentPixels;
    public static Sprite getAgentSprite() {
        
        if(agentPixels == null) {
            agentPixels = new int[7][7];
            
            for(int i=0; i < agentPixels.length; i++) {
                for(int j=0; j < agentPixels[i].length; j++) {
                    agentPixels[i][j] = 0xFFFFFF;
                }
            }
        }
        
        
        return new Sprite(new Point(0,0), agentPixels.clone());
    }
    
    
    private static int[][] itemPixels;
    public static Sprite getItemSprite() {

        if(itemPixels == null) {
            itemPixels = new int[4][4];
            
            for(int i=0; i < itemPixels.length; i++) {
                for(int j=0; j < itemPixels[i].length; j++) {
                    itemPixels[i][j] = 0xFF00FF;
                }
            }
        } 
        
        return new Sprite(new Point(0,0), itemPixels.clone());
    
    }
    
}
