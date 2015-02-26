import java.awt.*;
import hsa.*;

public class Map{
    // pseudomap makes it easier for me to create a map
    private int[][] pseudomap = {
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
      {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
      {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
      {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
      {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
      {1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
      {1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
      {1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1},
      {1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,1,1,1,1,1},
      {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
      {1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
      {1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
      {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
      {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
      {1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
      {1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
      {1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1},
      {1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1},
      {1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1},
      {1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
      {1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
      {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    private Polygon[] poly;
    public Map(){
	boolean[][] map = new boolean[pseudomap.length][pseudomap[0].length];
	for(int y=0;y<pseudomap.length;y++) for(int x=0;x<pseudomap[0].length;x++) map[y][x] = (pseudomap[y][x] == 1);
	
	poly = mapConvert(map);
    }
    public Polygon[] mapConvert(boolean[][] temp){
	//Add a border of false around the map
	boolean[][] map = border(temp);
	/*
	The border of false is simpler than checking to see if we're about to
	   run off the edge of the array all the time.
	*/

	Polygon[] walls = new Polygon[wallCount(map)]; // declare polygon array
	for(int x=0;x<walls.length;x++) walls[x] = new Polygon();
	
	int wallcount = 0;
	int direction;
	int posX,posY;
	boolean turnedright;
	/*
	direction refers to the direction the algorithm is moving. 0 is up, 1 is left, 2 is down,and 3 is right.
	The algorithm starts by moving down.
	turnedRight prevents the program from turning right twice in a row without moving
	*/

	for(int y=0;y<map.length;y++) //loop through the whole map
	    for (int x=0;x<map[0].length;x++)
		if(map[y][x]){ //if a wall is found
		    posX = x; //get coordinates
		    posY = y;
		    direction = 2; //start moving down
		    turnedright = false; //???
		    do{
			if(map[nextY(direction + 3,posY)][nextX(direction + 3,posX)] && !turnedright){ // turn right
			    direction += 3;
			    turnedright = true;
			    addPoint(walls[wallcount],posX,posY,direction); // Add new point on corner
			}
			else if(map[nextY(direction,posY)][nextX(direction,posX)]){ //if the block in front is a wall
			    posY = nextY(direction,posY); //move to wall (no corners,so don't addPoint)
			    posX = nextX(direction,posX);
			    turnedright = false;
			}
			else{ // turn left
			    addPoint(walls[wallcount],posX,posY,direction); // Add new point on corner
			    direction++;
			}
		    }
		    while(!(posX == x && posY == y && direction % 4 == 2)); // comes back to original place with same direction (2 = down)
		    wallcount++;
		    delete(map,y,x); // Delete all the already encoded points
		}
	return walls;
    }

    public void addPoint(Polygon poly,int x,int y,int direction){
	poly.addPoint(16 * (x - 1) + ((((direction + 3) % 4) / 2 + 1) * 16 / 3),
		      16 * (y - 1) + (((direction % 4) / 2 + 1) * 16 / 3) + 40);
    }

    public int nextY(int direction,int y){
	switch(direction % 4){
	    case 0:
		return y - 1;
	    case 2:
		return y + 1;
	    default:
		return y;
	}
    }

    public int nextX(int direction,int x){
	switch (direction % 4){
	    case 1:
		return x - 1;
	    case 3:
		return x + 1;
	    default:
		return x;
	}
    }

    public boolean[][] border(boolean[][] temp){
	boolean[][] map = new boolean[temp.length + 2][temp[0].length + 2];
	for(int x=1;x<=temp[0].length;x++) map[0][x] = false; // first row
	for(int y=1;y<=temp.length;y++){
	    map[y][0] = false; // first column
	    for(int x=1;x<=temp[0].length;x++) // map
		map[y][x] = temp[y - 1][x - 1];
	    map[y][temp[0].length + 1] = false; // last column
	}
	for(int x=1;x<=temp[0].length;x++) map[temp.length + 1][x] = false; // last row
	return map;
    }

    public int wallCount(boolean[][] temp){
	int wallCount = 0;
	// Create a new matrix to use for counting
	boolean[][] map = new boolean[temp.length][temp[0].length];
	for(int y=0;y<temp.length;y++) for(int x=0;x<temp[0].length;x++) map[y][x] = temp[y][x];
	/*The algorithm for finding the total number of seperate walls is:
	 * If spot is a wall,add one to wallCount
	 * delete spot and all spots connected to it (uses recursion!)
	 tl;dr: searches for a solid block,then removes the entire wall connected to the block (one piece)
	 */
	for(int y=0;y<map.length;y++)
	    for(int x=0;x<map[0].length;x++)
		if(map[y][x]){
		    delete(map,y,x);
		    wallCount++;
		}
	return wallCount;
    }

    public void delete(boolean[][] map,int y,int x){ //recursively finds walls
	map[y][x] = false; //set square to false
	if(map[y - 1][x]) delete(map,y - 1,x); //delete tile above
	if(map[y][x - 1]) delete(map,y,x - 1); //right
	if(map[y + 1][x]) delete(map,y + 1,x); //down
	if(map[y][x + 1]) delete(map,y,x + 1); //right
    }

    public boolean intersect(Rectangle rect){
	boolean hit = false;
	for(int i=0;i<poly.length&&!hit;i++) hit = poly[i].intersects(rect);
	return hit;
    }
    
    public void draw(Graphics2D g){
	for(int i=0;i<poly.length;i++){
	    g.setColor(Color.white);
	    g.drawPolygon(poly[i]);
	}
    }
}
