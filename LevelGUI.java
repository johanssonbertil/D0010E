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
	private int standardTransparency = 30; 
	
	public LevelGUI(Level level, String name) {
		
		lv = level;
		
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d = new Display(lv,1000,800);
		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0,0);
		frame.setVisible(true);
		lv.addObserver(this);
	}
	
	public void update(Observable arg0, Object arg1) {
		d.repaint();	
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
				g.setColor(r.floorColor);
				g.fillRect(r.xCoor, r.yCoor, r.xSize, r.ySize);
				
				BufferedImage image = null;
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream input = classLoader.getResourceAsStream("location_pin.png");
				try {
					image = ImageIO.read(input);
				} catch (IOException e) {
					System.out.println("FEL");
					e.printStackTrace();
				}
				
				double scale = (double) r.xSize/(5*370);
				System.out.println(scale);
				BufferedImage before = image;
				int w = before.getWidth();
				int h = before.getHeight();
				BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				AffineTransform at = new AffineTransform();
				at.scale(scale, scale);
				AffineTransformOp scaleOp = 
				   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
				after = scaleOp.filter(before, after);

				g.drawImage(after, r.xCoor+2*r.xSize/5, r.yCoor+r.ySize/3, null);
				
			}else {
				g.setColor(setTransparancy(r,standardTransparency));
				g.fillRect(r.xCoor, r.yCoor, r.xSize, r.ySize);
			}
			
			
		}
		
		public void paintConnections(Graphics g, Room r) {
			int rY =  (int) Math.round(0.1 * r.ySize);
			int rX = (int) Math.round(0.1 * r.xSize);   
			int roomSizeHalfX = r.xCoor+r.xSize/2;
			int roomSizeHalfY = r.yCoor+r.ySize/2;
			
			if(lv.getFirstLocation() == r) {
				if(r.northRoom != null) {
					g.setColor(r.northRoom.floorColor);
					g.fillOval(roomSizeHalfX-rX, r.yCoor-rX , 2*rX, 2*rY);
				}
				if(r.southRoom != null) {
					g.setColor(r.southRoom.floorColor);
					g.fillOval(roomSizeHalfX-rX, (r.yCoor+r.ySize)-rX , 2*rX, 2*rY);
				}
				if(r.westRoom != null) {
					g.setColor(r.westRoom.floorColor);
					g.fillOval(r.xCoor-rX,roomSizeHalfY-rY, 2*rX, 2*rY);
				}
				if(r.eastRoom != null){
					g.setColor(r.eastRoom.floorColor);
					g.fillOval(r.xCoor+r.xSize-rX,roomSizeHalfY-rY, 2*rX, 2*rY);
				}
			}else {
				if(r.northRoom != null) {
					g.setColor(setTransparancy(r.northRoom, standardTransparency));
					g.fillOval(roomSizeHalfX-rX, r.yCoor-rX , 2*rX, 2*rY);
				}
				if(r.southRoom != null) {
					g.setColor(setTransparancy(r.southRoom, standardTransparency));
					g.fillOval(roomSizeHalfX-rX, (r.yCoor+r.ySize)-rX , 2*rX, 2*rY);
				}
				if(r.westRoom != null) {
					g.setColor(setTransparancy(r.westRoom, standardTransparency));
					g.fillOval(r.xCoor-rX,roomSizeHalfY-rY, 2*rX, 2*rY);
				}
				if(r.eastRoom != null){
					g.setColor(setTransparancy(r.eastRoom, standardTransparency));
					g.fillOval(r.xCoor+r.xSize-rX,roomSizeHalfY-rY, 2*rX, 2*rY);
				}
			}
		}
			
		public Color setTransparancy(Room r, int trans) {
			int red = r.floorColor.getRed();
			int green = r.floorColor.getGreen();
			int blue = r.floorColor.getBlue();
			Color color = new Color(red,green,blue,trans);
			return color;
		}
		

	 	private class Listener implements KeyListener {

	 		
	 		public void keyPressed(KeyEvent arg0) {
	 		}

	 		public void keyReleased(KeyEvent arg0) {
	 		}

	 		public void keyTyped(KeyEvent event) {
	 			switch (event.getKeyChar()) {
				case 'w':
					lv.moveNorth();
					break;
				case 'd':
					lv.moveEast();
					break;
				case 's':
					lv.moveSouth();
					break;
				case 'a':
					lv.moveWest();
					break;
				default:
					System.out.println("Use w-a-s-d");
					break;
				}		
	 		}
	 	}
	}	
}
