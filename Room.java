package lab2.level;

import java.awt.Color;

//Denna klass ska representera rum.
public class Room {
	
	Color floorColor;
	int xSize, ySize, xCoor, yCoor;
	Room southRoom, northRoom, eastRoom, westRoom = null;

	public Room(int dx, int dy, Color color) {
		floorColor = color;
		xSize = dx;
		ySize = dy;
		System.out.println(floorColor.toString() +"X: " + dx+"y: "+ dy);
	}
	
	//Denna metod tilldelar rummets x- och y-koordinater.
	void setCoordinates(int x, int y){
		xCoor = x;
		yCoor = y;
	}
	
	//Denna metod returnerar rummets x-koordinat. 
	int x(){
		return  xCoor;
	}

	//Denna metod returnerar rummets y-koordinat. 
	int y(){
		return yCoor;
	}

	//Denna metod returnerar rummets storlek i x-led. 
    int xSize(){
		return xSize;
    }

    //Denna metod returnerar rummets storlek i y.led. 
    int ySize(){
		 return ySize;
    }

    /*
     * Denna metod tar som parameter ett rum och sammanlänkar
     *  de två rummen med varandra åt norr. 
     */
	public void connectNorthTo(Room r) {
		northRoom = r;
		System.out.println("connected north doorway");
	}
	
	/*
     * Denna metod tar som parameter ett rum och sammanlänkar
     *  de två rummen med varandra åt öster. 
     */
	public void connectEastTo(Room r) {
		eastRoom = r;
		System.out.println("connected east doorway");
	}
	
	/*
     * Denna metod tar som parameter ett rum och sammanlänkar
     *  de två rummen med varandra åt söder. 
     */
	public void connectSouthTo(Room r) {
		southRoom = r;
		System.out.println("connected south doorway");
	}
	
	/*
     * Denna metod tar som parameter ett rum och sammanlänkar
     *  de två rummen med varandra åt väster. 
     */
	public void connectWestTo(Room r) {
		westRoom = r;
		System.out.println("connected west doorway");
	}
}
