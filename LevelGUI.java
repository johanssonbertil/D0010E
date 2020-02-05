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
		
		this.lv = level;
		
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO: You should change 200 to a value 
		// depending on the size of the level
		d = new Display(lv,200,200);
		
		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0,0);
		frame.setVisible(true);
	}
	
	
	public void update(Observable arg0, Object arg1) {
		
	}
	
	private class Display extends JPanel {
		
		
		public Display(Level fp, int x, int y) {
		
			
			addKeyListener(new Listener());
			
			setBackground(Color.GREEN);
			setPreferredSize(new Dimension(x+20,y+20));
			setFocusable(true);
		}
	
		
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i = 0; i < lv.rooms.size(); i++) {
				Room r = lv.rooms.get(i);
				paintRooms(g, r);
				System.out.println(r.floorColor + " " + i);
				paintConnections(g,r);
			}	
			
		}
		
		public void paintRooms(Graphics g, Room r) {
			
			if(lv.getFirstLocation() == r) {
				g.setColor(r.floorColor);
			}else {
				int red = r.floorColor.getRed();
				int green = r.floorColor.getGreen();
				int blue = r.floorColor.getBlue();
				Color nyNyans = new Color(red,green,blue,127);
				g.setColor(nyNyans);
			}
			g.fillRect(r.xCoor, r.yCoor, r.xSize, r.ySize);
			g.drawRect(r.xCoor, r.yCoor, r.xSize, r.ySize);
		}
		
		public void paintConnections(Graphics g, Room r) {
			
			
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
