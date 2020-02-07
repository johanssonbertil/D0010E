package lab2.level;

import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LevelGUI implements Observer {

	private Level lv;
	private Display d;
	private int standardTransparency = 30;//Denna variabel bestämmer hur transparent rummen ska vara.  
	
	//Denna konstruktor skapar ett nytt fönster. 
	public LevelGUI(Level level, String name) {
		
		this.lv = level;
		
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d = new Display(lv,1200,1000);//Skapar ett nytt display objekt. 
		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0,0);
		frame.setVisible(true);
		lv.addObserver(this);//Lägger till en ny observatör som observerar level klassen. 
	}
	
	//Denna metod uppdaterar det grafiska gränsnittet när något i leveln ändras. 
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
		private void paintRooms(Graphics g, Room r) {
			if(lv.getCurrentRoom() == r) {// Om spelaren befiiner sig i det rum som skickas in som parameter. 
				g.setColor(r.floorColor);
			}else {
				g.setColor(setTransparancy(r,standardTransparency));//Om spelaren inte befinner sig i det rum som skickas in som parmaeter. 
			}
			g.fillRect(r.xCoor, r.yCoor, r.xSize, r.ySize);
			
		}
		//Denna metod ritar ut cirklarna som ska representera förbindingarna mellan rummen. 
		private void paintConnections(Graphics g, Room r) {
			int rY =  (int) Math.round(0.1 * r.ySize);//Radien på cirekln i y-led. 
			int rX = (int) Math.round(0.1 * r.xSize);  //Radien på cirkeln i x-led.  
			int roomSizeHalfX = r.xCoor+r.xSize/2;//Sparar en ny x-koordinat som är x-koordinaten för rummet adderat med halva längden i x-led för rummet. 
			int roomSizeHalfY = r.yCoor+r.ySize/2;//Sparar en ny y-koordinat som är y-koordinaten för rummet adderat med halva längden i y-led för rummet. 
			
			//Om det rum som tas som parameter är det rum som spelaren befiiner sig i. 
			if(lv.getCurrentRoom() == r) {
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
			}else {//Om metoden tar ett rum som spelaren ej befinner sig i. 
				if(r.northRoom != null) {
					g.setColor(setTransparancy(r.northRoom, standardTransparency));
					g.fillOval(roomSizeHalfX-rX, r.yCoor , 2*rX, 2*rY);
				}
				if(r.southRoom != null) {
					g.setColor(setTransparancy(r.southRoom, standardTransparency));
					g.fillOval(roomSizeHalfX-rX, (r.yCoor+r.ySize)-2*rX , 2*rX, 2*rY);
				}
				if(r.westRoom != null) {
					g.setColor(setTransparancy(r.westRoom, standardTransparency));
					g.fillOval(r.xCoor,roomSizeHalfY-rY, 2*rX, 2*rY);
				}
				if(r.eastRoom != null){
					g.setColor(setTransparancy(r.eastRoom, standardTransparency));
					g.fillOval(r.xCoor+r.xSize-2*rX,roomSizeHalfY-rY, 2*rX, 2*rY);
				}
			}
		}
		
		/*
		 * Denna metod returnerar exakt samma färg som den färg på golvet i det rum
		 * som tas som parameter i metoden, fast mer transparent. 
		 */
		private Color setTransparancy(Room r, int transparens) {
			int red = r.floorColor.getRed();
			int green = r.floorColor.getGreen();
			int blue = r.floorColor.getBlue();
			Color color = new Color(red,green,blue,transparens);
			return color;
		}
		
		//Denna klass implementerar styrning via tangentbordet. 
	 	private class Listener implements KeyListener {

	 		public void keyPressed(KeyEvent arg0) {
	 		}

	 		public void keyReleased(KeyEvent arg0) {
	 		}

	 		//Denna metod reagerar när man trycker på någon knapp på tangentbordet. 
	 		public void keyTyped(KeyEvent event) {
	 			switch (event.getKeyChar()) {
				case 'w':
					lv.moveNorth();//Denna metod anropas om "w" trycks ner. 
					break;
				case 'd':
					lv.moveEast();//Denna metod anropas om "d" trycks ner. 
					break;
				case 's':
					lv.moveSouth();//Denna metod anropas om "s" trycks ner. 
					break;
				case 'a':
					lv.moveWest();//Denna metod anropas om "a" trycks ner. 
					break;
				default:
					System.out.println("Use w-a-s-d");//Detta skrivs ut om någon annan knapp trycks ner. 
					break;
				}		
	 		}
	 	}
	}	
}
