import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Enemy{
    private double x,y;
    private double angle;
    private double offset;
    private int width,height;
    private double speed;
    
    private BufferedImage image;
    public Enemy(double x,double y,String s){
	speed = 1;
	offset = Math.random();
	this.x = x;
	this.y = y;
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
    public void update(double xmove,double ymove){
	offset += Math.sqrt((x-xmove)*(x-xmove)+(y-ymove)*(y-ymove))/1000*(Math.random()-1.0/2);
	angle = Math.atan((y-ymove)/(x-xmove)) + offset;
	if(xmove<x) angle += Math.PI;
	
	x += speed * Math.cos(angle);
	y += speed * Math.sin(angle);
	
	if(x<10) x=10;
	if(x>GamePanel.WIDTH-width-10) x = GamePanel.WIDTH-width-10;
	if(y<50) y=50;
	if(y>GamePanel.HEIGHT-height-50) y = GamePanel.HEIGHT-height-50;
	
	
    }
    public void draw(Graphics2D g){
	AffineTransform tx = AffineTransform.getRotateInstance(angle, width/2, height/2);
	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	g.drawImage(op.filter(image, null), (int)x, (int)y, null);
    }
    
    public Rectangle getRect(){
	return new Rectangle((int)x,(int)y,width,height);
    }
    
    public void reset(double x,double y){
	this.x = x;
	this.y = y;
	speed = 1;
    }
    
    public void fast(){
	speed *= 1.1;
    }
}
