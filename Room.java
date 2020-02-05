package lab2.level;

import java.awt.Color;
import java.awt.Point;


public class Room { 

	private Color floorColor;
	int xSize, ySize, xCoor, yCoor;
	public Room southRoom, northRoom, eastRoom, westRoom = null;

	
	public Room(int dx, int dy, Color color) {
		floorColor = color;
		xSize = dx;
		ySize = dy;
		System.out.println(floorColor.toString()+"\tx: "+dx+"\ty: "+dy);
	
	}

	public void setCoordinates(int x, int y){
		xCoor = x;
		yCoor = y;
	}
	

	public int x(){
		return 	xCoor;
	}

	public int y(){
		return yCoor;
	}

	public int xSize(){
		return xSize;
	}

	public int ySize(){
		return ySize;
	}

	public void connectNorthTo(Room r) {
		northRoom = r;
		System.out.println("connected north doorway");
		
	}
	
	public void connectEastTo(Room r) {
		eastRoom = r;
		System.out.println("connected East doorway");
	}
	
	public void connectSouthTo(Room r) {
		southRoom = r;
		System.out.println("connected South doorway");
	}
	
	public void connectWestTo(Room r) {
		westRoom = r;
		System.out.println("connected West doorway");

	}


}
