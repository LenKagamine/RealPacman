import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Player{
    private double x,y;
    private int xtarget,ytarget; //mouse coords
    private double angle;
    private int width,height;
    private double speed;
    private BufferedImage image; //image of player
    
    public Player(String s){
	speed = 2;
	x = 220.0;
	y = 405.0;
	angle = 0;
	try{
	    image = ImageIO.read(getClass().getResourceAsStream(s));
	    width = image.getWidth();
	    height = image.getHeight();
	}
	catch(Exception e){
	    width = 0;
	    height = 0;
	}
    }
    
    public void update(Map map){
	angle = Math.atan((y-ytarget)/(x-xtarget));
	if(xtarget<x) angle += Math.PI;
	
	double xnew = x + speed * Math.cos(angle), ynew = y + speed * Math.sin(angle);
	
	if(!map.intersect(new Rectangle((int)xnew,(int)y,width,height))) x = xnew;
	if(!map.intersect(new Rectangle((int)x,(int)ynew,width,height))) y = ynew;
	
	if(255 < y && y < 275){ //move through hole
	    if(x<20) x = 428;
	    else if(x>428) x = 20;
	}
    }
    
    public void draw(Graphics2D g){
	AffineTransform tx = AffineTransform.getRotateInstance(angle, width/2, height/2);
	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	g.drawImage(op.filter(image, null), (int)x, (int)y, null);
    }
    
    public void move(int xmove,int ymove){
	xtarget = xmove-width/2;
	ytarget = ymove-height/2;
    }
    
    public double getX(){
	return x;
    }
    
    public double getY(){
	return y;
    }
    
    public Rectangle getRect(){
	return new Rectangle((int)x,(int)y,width,height);
    }
    
    public void reset(){
	x = 220.0;
	y = 405.0;
	speed = 2;
    }
    
    public void fast(){
	speed *= 1.1;
    }
}
