package com.dustyn.thunderdome;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author MRogue
 */
public class Map {
    
    public static  final int tileWidth = 10 ;
    private Tile[][] tiles;
    private int rowLength;
    private ArrayList<Agent> agents;
    private  Item item;
    private Random rnd;
    
    private Map() {
        this.tiles = new Tile[800/tileWidth][800/tileWidth];
        this.agents = new ArrayList<>();
        this.rnd = new Random();
        this.rowLength = tiles.length;
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(new Point(tileWidth*i,tileWidth*j), new Point(tileWidth*i + tileWidth, tileWidth*j+tileWidth));
                tiles[i][j].setColor(0xFFFFFF);
                
                if(rnd.nextInt(10) == 1) {
                    tiles[i][j].isTraversable = false;
                    tiles[i][j].setColor(0x000000);
                }
                
            }
        }
        generateNewItem();
        
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
        return tiles[  ((int) p.x/ tileWidth)  ][  ((int) p.y/ tileWidth)];
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

    void generateNewItem() {
        int x = rnd.nextInt(800);
        int y = rnd.nextInt(800);
        
        while(!getTile(x,y).isTraversable) {
             x = rnd.nextInt(800);
             y = rnd.nextInt(800);
        }

        this.item = new Item(new Point(x,y));
        
        for(Agent a : agents) {
            a.restartAStar();
        }
    }
    
    private static class MapHolder {

        private static final Map INSTANCE = new Map();
    }
    
    public Tile[] getAdjacentTiles(Tile t) {
        
        int x = 0;
        int y = 0;
        
        int realX = 0;
        int realY = 0;
        
        for(Tile[] row : tiles) {

            for(Tile tile : row) {                
                if(tile.equals(t)) {
                    realX = x;
                    realY = y;
                }
                y++;

            }
            y=0;
            x++;

        }
        
        x = realX;
        int xMinus = (x - 1) > -1 ? x-1 : 79; 
               
        
        y = realY;
        int yMinus = (y - 1) > -1 ? y - 1 : 79; 
                
                
        try {
            Tile left = tiles[xMinus][y];
            Tile right = tiles[(x+1)%rowLength][y];
            Tile up = tiles[x][(y+1)%rowLength];
            Tile down = tiles[x][yMinus];
            Tile upRight = tiles[(x+1)%rowLength][(y+1)%rowLength];
            Tile upLeft = tiles[xMinus][(y+1)%rowLength];
            Tile downRight = tiles[(x+1)%rowLength][yMinus];
            Tile downLeft = tiles[xMinus][yMinus];


            Tile[] adjacentTiles = {
                left,
                right,
                up,
                down,
                upLeft,
                upRight,
                downLeft,
                downRight
            };
            return adjacentTiles;
            
        } catch (Exception e) {
            Tile[] tiles = {t};
            return tiles;
        }
    }
    
}
