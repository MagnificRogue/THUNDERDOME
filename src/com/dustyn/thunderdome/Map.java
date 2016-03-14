package com.dustyn.thunderdome;

import java.awt.Point;
import java.util.ArrayList;


/**
 *
 * @author MRogue
 */
public class Map {
    
    public static  final int tileWidth = 10 ;
    private Tile[][] tiles;    
    private ArrayList<Agent> agents;
    private  Item item;
    
    
    private Map() {
        this.tiles = new Tile[800/tileWidth][800/tileWidth];
        this.agents = new ArrayList<>();
        this.item = new Item(new Point(100,100));
        
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(new Point(tileWidth*i,tileWidth*j), new Point(tileWidth*i + tileWidth, tileWidth*j+tileWidth));
                tiles[i][j].setColor(0xFFFFFF);
            }
        }
        
    }
    
    public Tile[][] getTiles() {
        return this.tiles;
    }

    public Tile getTile(int x, int y) {
        
        int xIndex = (int) (x / tileWidth);
        int yIndex = (int) (y / tileWidth);
        return tiles[xIndex][yIndex];
    }
    
    public Tile getTile(Point p) {        
        return tiles[(int) (p.x / tileWidth)][(int) (p.y / tileWidth)];
    }    
    
    
    
    public static Map getInstance() {
        return MapHolder.INSTANCE;
    }

    /**
     * @return the items
     */
    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public Item getItem() {
        return item;
    }
    
    private static class MapHolder {

        private static final Map INSTANCE = new Map();
    }
}
