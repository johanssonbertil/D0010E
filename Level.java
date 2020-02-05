package lab2.level;

import java.util.Vector;
import java.util.Observable;


public class Level extends Observable {
	
    Vector<Room> rooms = new Vector<Room>();
    Boolean firstLocationAdded = false;
    Room currentRoom = null;
	
    public boolean place(Room r, int x, int y){
	
	if(firstLocationAdded){ return false; }

        r.setCoordinates(x, y);
        
        boolean yOverlap = false,xOverlap = false;
    
        //Avoid overlap
        for (int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            if (room.x() >= r.x()){
                if (r.x() + r.xSize() > room.x()){ xOverlap = true; }
            }else{
                if (room.x() + room.xSize() > r.x()){ xOverlap = true; }
            }
            if (room.y() >= r.y()){
                if (r.y() + r.ySize() > room.y()){ yOverlap = true; }
            }else{
                if (room.y() + room.ySize() > r.y()){ yOverlap = true;}
            }
        }
        
        if(xOverlap && yOverlap) {
        	return false;
        }
        
        
        rooms.add(r);
        return true;    

	}
	
    //Set first location on creating level, can only be called once. 
	public void firstLocation(Room r) {
		firstLocationAdded = true;
	    currentRoom = r;
	}
	
	public Room getFirstLocation() {
		return currentRoom;
	}	
}
