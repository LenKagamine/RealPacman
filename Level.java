import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Level{
    private Player player;
    private Enemy[] enemy;
    private Map map;
    private BufferedImage cherry;
    private int cherryx,cherryy;
    private int score;
    private boolean dead;
    
    public Level(){
	score = 0;
	map = new Map();
	player = new Player("pacman.png");
	enemy = new Enemy[]{
	    new Enemy(195,260,"blinky.png"),
	    new Enemy(215,260,"pinky.png"),
	    new Enemy(235,260,"inky.png"),
	    new Enemy(255,260,"clyde.png")
	};
	try{
	    cherry = ImageIO.read(getClass().getResourceAsStream("cherry.png"));
	    do{
		cherryx = (int)(Math.random()*(GamePanel.WIDTH-20))+10;
		cherryy = (int)(Math.random()*(GamePanel.HEIGHT-100))+50;
	    } while(map.intersect(new Rectangle(cherryx,cherryy,12,12)));
	} catch(Exception e){}
    }
    
    public void update(){
	if(!dead){
	    player.update(map);
	
	    for(int i=0;i<enemy.length;i++) enemy[i].update(player.getX(),player.getY());
	
	    dead = false;
	    for(int i=0;i<enemy.length&&!dead;i++) dead = enemy[i].getRect().intersects(player.getRect());
	}
    }
    
    public void draw(Graphics2D g){
	map.draw(g);
	player.draw(g);
	for(int i=0;i<enemy.length;i++) enemy[i].draw(g);
	g.drawImage(cherry,cherryx,cherryy,null);
	
	g.setColor(Color.cyan);
	g.setFont(new Font(null,Font.BOLD,20));
	g.drawString("Score: "+score,170,560);
	
	if(dead){
	    g.setColor(Color.orange);
	    g.fillRoundRect(GamePanel.WIDTH/2-100,GamePanel.HEIGHT/2-50,200,100,20,20);
	    g.setColor(Color.blue);
	    g.drawString("You died!",GamePanel.WIDTH/2-45,GamePanel.HEIGHT/2-15);
	    g.drawString("Score: "+score,GamePanel.WIDTH/2-45,GamePanel.HEIGHT/2+15);
	}
    }
    
    public void move(int x,int y){
	player.move(x,y);
    }
    
    public void click(int x,int y){
	if(player.getRect().intersects(new Rectangle(cherryx,cherryy,12,12))){
	    score++;
	    do{
		cherryx = (int)(Math.random()*(GamePanel.WIDTH-20))+10;
		cherryy = (int)(Math.random()*(GamePanel.HEIGHT-100))+50;
	    } while(map.intersect(new Rectangle(cherryx,cherryy,12,12)));
	    player.fast();
	    for(int i=0;i<enemy.length;i++) enemy[i].fast();
	}
	else if(dead){
	    dead = false;
	    score = 0;
	    player.reset();
	    for(int i=0;i<enemy.length;i++) enemy[i].reset(195+i*20,260);
	}
    }
}
