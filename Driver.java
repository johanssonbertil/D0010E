package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {

	public void run() {

		Level lvl = new Level();
		
		Room r1 = new Room(10, 10, Color.blue);
		Room r2 = new Room(10,10,Color.orange);
		Room r3 = new Room(10, 10, Color.red);
		Room r4 = new Room(10,10,Color.green);
		Room r5 = new Room(10, 10, Color.yellow);
		

		r1.connectNorthTo(r2);
		r2.connectSouthTo(r1);
		r2.connectNorthTo(r3);
		r3.connectSouthTo(r2);
		r4.connectEastTo(r3);
		r3.connectWestTo(r3);
		r5.connectWestTo(r3);
		r3.connectEastTo(r5);


		System.out.println(lvl.place(r1, 11, 11));
		System.out.println(lvl.place(r2, 11, 21));
		System.out.println(lvl.place(r3, 11, 31));
		System.out.println(lvl.place(r4, 1, 31));
		System.out.println(lvl.place(r5, 21, 31));


	}

}