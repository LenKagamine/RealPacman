import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener{
    //Levels
    private static int currentLevel;
    private Level level;
    //Width 448, Height 576
    public static final int WIDTH = 448,HEIGHT = 576;
    
    //Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    //Image/Drawing
    private BufferedImage image;
    private Graphics2D g;
    
    public GamePanel(){
	super();
	setPreferredSize(new Dimension(WIDTH,HEIGHT)); //Set size
	setFocusable(true); //Allow focus
	requestFocus(); //Set focus
    }
    
    public void addNotify(){
	super.addNotify();
	if(thread == null){
	    thread = new Thread(this); //Create the thread if not created before
	    addMouseMotionListener(this);
	    addMouseListener(this);
	    thread.start(); //Start thread
	}
    }
    
    public void run(){ //Main game loop
	image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	g = (Graphics2D) image.getGraphics();
	level = new Level();
	running = true;
	
	long start,elapsed,wait; //Time keeping for FPS
	while(running){
	    start = System.currentTimeMillis();
	    
	    update();
	    draw();
	    drawToScreen();
	    
	    elapsed = System.currentTimeMillis()-start;
	    
	    wait = targetTime - elapsed;
	    
	    if(wait < 0) wait = 0; //Negative wait time
	    try{
		Thread.sleep(wait); //Wait for FPS
	    }
	    catch(Exception e){
		e.printStackTrace();
	    }
	}
    }
    
    public void update(){//Update game
	level.update();
    }
    public void draw(){//Draw game
	g.setColor(Color.black);
	g.fillRect(0,0,WIDTH,HEIGHT);
	level.draw(g);
    }
    public void drawToScreen(){ //Draw buffered image to screen
	Graphics g2 = getGraphics();
	g2.drawImage(image,0,0,WIDTH,HEIGHT,null);
	g2.dispose();
    }
    
    public void mousePressed(MouseEvent e){}
    public void mouseClicked(MouseEvent e){
	try{
	level.click(e.getX(),e.getY());
	} catch(Exception ex){}
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseMoved(MouseEvent e){
	try{
	    level.move(e.getX(),e.getY());
	} catch(Exception ex){}
    }
    public void mouseDragged(MouseEvent e){
	try{
	    level.move(e.getX(),e.getY());
	} catch(Exception ex){}
    }
}
