package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;


public class Driver {

	public void run() {
	
		Level lvl = new Level();//Skapar ett nytt level objekt.
        
		//Skapar 5 nya room objekt. 
        	Room r1 = new Room(100, 100, Color.green);
        	Room r2 = new Room(100,100,Color.orange);
        	Room r3 = new Room(100, 100, Color.red);
        	Room r4 = new Room(100,100,Color.PINK);
        	Room r5 = new Room(100, 100, Color.CYAN);
        	Room r6 = new Room(100,100, Color.blue);
       
        	//Tilldelar rummen connections.
        	r1.connectEastTo(r2);
        	r2.connectWestTo(r1);
        	r2.connectNorthTo(r3);
        	r3.connectSouthTo(r2);
        	r4.connectEastTo(r3);
        	r3.connectWestTo(r3);
        	//r5.connectWestTo(r3);
        	r3.connectEastTo(r5);
        	r5.connectEastTo(r4);
        	r4.connectEastTo(r1);
        	r1.connectSouthTo(r6);
       

        	//Placerar ut rummen på leveln.
        	System.out.println(lvl.place(r1, 0, 0));
        	System.out.println(lvl.place(r2, 100, 0));
        	System.out.println(lvl.place(r3, 400, 500));
        	System.out.println(lvl.place(r4, 500, 620));
        	System.out.println(lvl.place(r5, 600, 200));
        	System.out.println(lvl.place(r6, 10, 10));
        
        	//Tilldelar startposition.
        	lvl.firstLocation(r1);
        
        	new LevelGUI(lvl,"level");  
	}
}
