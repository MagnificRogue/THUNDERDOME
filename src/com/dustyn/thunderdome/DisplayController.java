package com.dustyn.thunderdome;


import com.dustyn.thunderdome.graphics.Screen;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame;


/**
 *
 * @author MRogue
 */
public class DisplayController extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    
    public static int windowWidth = 800;
    public static int windowHeight = 800;
    public static int windowScale = 1;
    private static String displayTitle = "THUNDERDOME! ";
    
    public JFrame frame;
        
    private Thread thread;
    private boolean running;
    
    private Screen screen;
    
    private BufferedImage image = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    public DisplayController() {
        Dimension size = new Dimension(windowWidth * windowScale, windowHeight * windowScale);

        screen = new Screen(windowWidth, windowHeight);
        
        setPreferredSize(size);
        
        frame = new JFrame();
        frame.pack();
        
        this.addMouseListener(new MouseListener(){
            Random r = new Random();
            private synchronized void add(MouseEvent e) {
                synchronized(Map.getInstance().getAgents()) {
                    Agent a = new Agent(e.getPoint());
                    a.setColor(r.nextInt(0xFFFFFF));
                    Map.getInstance().getAgents().add(a);   
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
               // add(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                add(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              //  add(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
              //  add(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        
        });
        
        
    }
    
    
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void run() {
        
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        
        final double ns = 1000000000.0 / 20.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        
        
        while(running) {
            
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // 
            lastTime = now;
            
            while(delta >= 1) {
                update(); //handles the logic, bounded per second
                updates++; //counting total number of updates
                delta--;
            }
            
            render(); //handles the drawing, unbounded per second
            frames++; //counting the total number of drawn frames
            
            if(System.currentTimeMillis() - timer > 1000) { 
             timer += 1000;
             
             frame.setTitle(DisplayController.displayTitle + updates + " ups " + frames + " fps");
             
             frames = 0;
             updates = 0;
            }
        }
        
        stop();        
    }
    
    private  void update() {
        synchronized(Map.getInstance().getAgents()) {
            for(Agent a : Map.getInstance().getAgents()) {
                a.aStar(Map.getInstance(), Map.getInstance().getItem());
            }
        }
    }

    private void render() {
        
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        screen.render();
        
        for(int i=0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
        
        
        g.dispose();
        bs.show();
    }
    
    
    public static void main(String args[]){
        DisplayController display = new DisplayController();
        display.frame.setResizable(false); //we can use builder here
        display.frame.setTitle(DisplayController.displayTitle);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setVisible(true);
        
        display.start();
    }


    
}
