package GravitationalSimulation;

import java.util.*;
import java.awt.*;

class Star{
	Point location;
	int r;
	int g;
	int b;
	int a;
	
	int pulseRate = 1;
	Random rand = new Random();
	public Star(Point loc, int red, int green, int blue, int alpha){
		location = loc;
		r = red;
		b = blue;
		g = green;
		a = alpha;
		pulseRate = rand.nextInt(254);
	}
	
	public Star(Point loc, Color c, int alpha){
		location = loc;
		r = c.getRed();
		b = c.getBlue();
		g = c.getGreen();
		a = alpha; 
		pulseRate = rand.nextInt(155);
	}
	
	boolean down = false;
	public Color getColor(){
//		System.out.println("Clolor "+r+" "+g+" "+" "+b+" : "+a);
		if(down){
			a-=pulseRate;
			if(a <= 0){
				a = 0;
				down= false;
			}
		}
		else{
			a+=pulseRate;
			if(a >= 255){
				a=255;
				down = true;
			}
		}
		return new Color(r,g,b,a);
	}
}