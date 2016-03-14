package com.dustyn.thunderdome.graphics;

import java.awt.Point;

/**
 *
 * @author MRogue
 */
public interface Renderable {
    int[][] getSprite();
    
    Point getOrigin();
    Point getBounds();

}
