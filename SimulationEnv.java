package GravitationalSimulation;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;	
import java.io.*;
import java.util.*;
import java.net.URL;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


class SimulationEnv extends JDesktopPane{
	ArrayList stars = new ArrayList<Star>();
	int numStars = 1000;
	
	static ObjConfig config1, config2;
	public SimulationEnv(){
		super();
		setVisible(true);
		setBounds(0, 0, 1024, 768);
		setVisible(true);
		setFocusable(false);
		add(new Display());
		config1 = new ObjConfig(Environment.obj1);
		config1.setName("Planet 1");
		config1.setImage("Images/planet2.png");
		
		config2 = new ObjConfig(Environment.obj2);
		config2.setName("Planet 2");
		config2.setImage("Images/planet3.png");
				
//		initStars();
	}
	Random rand = new Random();
	public void initStars(){
		Color[] colors = {Color.RED, Color.CYAN, Color.YELLOW, Color.WHITE, Color.ORANGE};
		for(int k = 0; k < numStars; k++){
			int x = rand.nextInt(1024);
			int y = rand.nextInt(768);
			// Red, blue, yellow, white, orange, 
			Color sColor = colors[rand.nextInt(4)];
			Star s = new Star(new Point(x, y), sColor, 155);
			stars.add(s);
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0,0,1024,768);
	//	drawStars(g);
		repaint();
	}
	public void drawStars(Graphics g){
		for(int k = 0; k < stars.size(); k++){
			Star s = (Star) stars.get(k);
			g.setColor(s.getColor());
			int x = ((Double)s.location.getX() ).intValue();
			int y = ((Double)s.location.getY() ).intValue();
			g.fillOval(x, y, 1, 1);
		}
	}
}