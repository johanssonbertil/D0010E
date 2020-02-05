package lab2.level;

import java.util.ArrayList;
import java.util.Observable;


public class Level extends Observable {
	
	ArrayList<Room> rooms = new ArrayList<Room>();
	Boolean firstLocationAdded = false;
	Room currentRoom;

	
	public boolean place(Room r, int x, int y)  {
		
		if(firstLocationAdded){ return false; }
	
		r.setCoordinates(x, y);
		
		Boolean yOverlap = false, xOverlap = false;
		
		for (int i = 0; i < rooms.size(); i++){
			Room room = rooms.get(i);
			if (room.x() >= r.x()){
				if (r.x() + r.xSize() - 1 >= room.x()){ xOverlap = true; }
			}
			if (room.x() < r.x()){
				if (room.x() + room.xSize() - 1 >= r.x()){xOverlap = true; }
			}
			if (room.y() <= r.y()){
				if ( r.y() - r.ySize() < room.y()){ yOverlap = true; }
			}
			if (room.y() > r.y()){
				if (room.y() - room.ySize() < r.y()){ yOverlap = true;}
			}
		}
		
		if (xOverlap && yOverlap){ return false; }
		
		rooms.add(r);
		
		return true;		

	}

	
	public void firstLocation(Room r) {
		firstLocationAdded = true;
		currentRoom = r;	
	}
	
}
