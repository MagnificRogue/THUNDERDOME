package com.dustyn.thunderdome.graphics;

import com.dustyn.thunderdome.Map;
import com.dustyn.thunderdome.Tile;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author MRogue
 */
public class Screen {

    private int width, height;
    public int[] pixels;
    
    
    
    private  Random r = new Random();
    private Map map;
    
    public Map getMap(){
        return map;
    }
    
    public void setMap(Map m){
        this.map = m;
    }
    
    
    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        
        this.map = Map.getInstance();
        
    }
    
    public void clear() {
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = 0xFFFFFF;
        }
    }
    
    public void render() {

        this.clear();
        for(Tile[] row : map.getTiles()) {
            for(Tile tile : row){
                this.render(tile);
            }
        }
       
        render(Map.getInstance().getItem());
        
        synchronized(Map.getInstance().getAgents()) {
            Map.getInstance().getAgents().stream().forEach((r) -> {
                render(r);
            });
        }
    }

    private void render(Renderable r) {

        Sprite s = r.getSprite();
        
        for(int i = s.getOrigin().x; i < s.getBound().x; i++) {
            for(int j = s.getOrigin().y; j < s.getBound().y; j++){
                int pixelIndex = i + j * width;
                
                if(pixelIndex > pixels.length || 0 > pixelIndex) continue;
                
                pixels[pixelIndex] = s.getPixels()[i - s.getOrigin().x][j-s.getOrigin().y];
            }
        }
    }
    
}
