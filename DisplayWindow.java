package GravitationalSimulation;

import javax.swing.*;
import java.awt.*;

class DisplayWindow extends JComponent{
	public DisplayWindow(){}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0,0,1024,768);
		if(Environment.gridded)
			g = drawGrid(g);

		g = Environment.drawPlanets(g);
		
		try{
			//	Thread.sleep(10);
		}
		catch(Exception e){System.out.println(e);		}
		
		repaint();
	}
	
	public Graphics drawGrid(Graphics g){
		g.setColor(Color.green);
		int gridWidth = 50;
		for(int k = 0; k < (1024/gridWidth); k++){
			g.drawLine(gridWidth * k, 0, gridWidth * k, 768);
			g.drawLine(0, gridWidth * k, 1024, gridWidth * k);
		}
		return g;
	}
}