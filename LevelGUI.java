package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LevelGUI implements Observer {

	private Level lv;
	private Display d;
	
	public LevelGUI(Level level, String name) {
		
		lv = level;
		
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO: You should change 200 to a value 
		// depending on the size of the level
		d = new Display(lv,1000,800);
		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0,0);
		frame.setVisible(true);
		//lv.addObserver(this);
	}
	
	
	public void update(Observable arg0, Object arg1) {
		//d.repaint();
		
	}
	
	private class Display extends JPanel {
		
		
		public Display(Level fp, int x, int y) {
			addKeyListener(new Listener());	
			setBackground(Color.GRAY);
			setPreferredSize(new Dimension(x+20,y+20));
			setFocusable(true);
		}
	
		
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i = 0; i < lv.rooms.size(); i++) {
				Room r = lv.rooms.get(i);
				paintRooms(g, r);
				paintConnections(g,r);
			}	
		}

		
		//Denna metod ritar upp rummen som blivit placerad i leveln. 
		public void paintRooms(Graphics g, Room r) {
			if(lv.getFirstLocation() == r) {// If the 
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream input = classLoader.getResourceAsStream("bild.jpg");
				Image bild = null;
				try {
					bild = ImageIO.read(input);
				} catch (IOException e) {
					System.out.println("FEELL");
					e.printStackTrace();
				}
				
				g.drawImage(bild,0,0,null);
			}else {
				int red = r.floorColor.getRed();
				int green = r.floorColor.getGreen();
				int blue = r.floorColor.getBlue();
				Color newColor = new Color(red,green,blue,30);//Makes the color more transparent
				g.setColor(newColor);
			}
			g.fillRect(r.xCoor, r.yCoor, r.xSize, r.ySize);
			
		}
		
		public void paintConnections(Graphics g, Room r) {
			int rY =  (int) Math.round(0.1 * r.ySize);
			int rX = (int) Math.round(0.1 * r.xSize);   
			int roomSizeHalfX = r.xCoor+r.xSize/2;
			int roomSizeHalfY = r.yCoor+r.ySize/2;
			if(r.northRoom != null) {
				g.setColor(r.northRoom.floorColor);
				g.fillOval(roomSizeHalfX-rX, r.yCoor-rX , 2*rX, 2*rY);
		    	}
			  

			  
			 
		
		}
		

	 	private class Listener implements KeyListener {

	 		
	 		public void keyPressed(KeyEvent arg0) {
	 		}

	 		public void keyReleased(KeyEvent arg0) {
	 		}

	 		public void keyTyped(KeyEvent event) {
	 		}
	 	}

	}
}
