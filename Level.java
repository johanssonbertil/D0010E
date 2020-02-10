package lab2.level;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;


public class Level extends Observable{
	
    Vector<Room> rooms = new Vector<Room>();
    Boolean firstLocationAdded = false;
    Room currentRoom = null;
	
    /*Denna metod kontrollerar samt sparar de godkända rum som man vill lägga till leveln. 
     * Detta görs genom att kolla så att något rum inte överlappar med något annat rum. Om ingen
     * överlappning sker kommer de godkända rummen att sparas till en vektor.
     */
	public boolean place(Room r, int x, int y){
	
		if(firstLocationAdded){ return false; }

        r.setCoordinates(x, y);
        
        boolean yOverlap = false,xOverlap = false;
    
        for (int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            if (room.x() >= r.x()){
                if (r.x() + r.xSize() > room.x()){ xOverlap = true; }
            }
            else{
                if (room.x() + room.xSize() > r.x()){ xOverlap = true; }
            }
            if (room.y() >= r.y()){
                if ( r.y() + r.ySize() > room.y()){ yOverlap = true; }
            }
            else{
                if (room.y() + room.ySize() > r.y()){ yOverlap = true;}
            }
        }
        
        if(xOverlap && yOverlap) {
        	r.roomExistsOnLevel = false;
        	return false;
        }
        rooms.add(r);
        return true;    

	}
	
	//Denna metod tilldelar det rum som spelaren kommer att börja i. 
	public void firstLocation(Room r) {
		firstLocationAdded = true;
	    currentRoom = r;
	}
	
	//Denna metod returnerar det rum som spelaren i nuläget befinner sig i. 
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/*
	 * Denna metod kontrollerar om det rum som spelaren befinner sig i
	 * har någon förbindelse med ett annat rum åt norr. Om detta stämmer
	 * går spelaren till det rummet. 
	 */
	void moveNorth() {
		if(currentRoom.northRoom != null && currentRoom.northRoom.roomExistsOnLevel == true) {
			currentRoom = currentRoom.northRoom;
			updateLevel();
		}
	}
	
	/*
	 * Denna metod kontrollerar om det rum som spelaren befinner sig i
	 * har någon förbindelse med ett annat rum åt söder. Om detta stämmer
	 * går spelaren till det rummet. 
	 */
	void moveSouth() {
		if(currentRoom.southRoom != null && currentRoom.southRoom.roomExistsOnLevel == true) {
			currentRoom = currentRoom.southRoom;
			updateLevel();
		}
	}
	
	/*
	 * Denna metod kontrollerar om det rum som spelaren befinner sig i
	 * har någon förbindelse med ett annat rum åt väst. Om detta stämmer
	 * går spelaren till det rummet. 
	 */
	void moveWest() {
		if(currentRoom.westRoom != null&& currentRoom.westRoom.roomExistsOnLevel == true) {
			currentRoom = currentRoom.westRoom;
			updateLevel();
		}
	}
	
	/*
	 * Denna metod kontrollerar om det rum som spelaren befinner sig i
	 * har någon förbindelse med ett annat rum åt öst. Om detta stämmer
	 * går spelaren till det rummet. 
	 */
	void moveEast() {
		if(currentRoom.eastRoom != null&& currentRoom.eastRoom.roomExistsOnLevel == true) {
			currentRoom = currentRoom.eastRoom;
			updateLevel();
		}
	}
	
	//Denna metod notifierar alla observerare att förändringar har skett. 
	void updateLevel() {
		setChanged();
		notifyObservers();
	}
}
