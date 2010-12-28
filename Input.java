package GravitationalSimulation;

import java.awt.*;
import java.awt.event.*;
import java.math.*;	
import java.awt.geom.*;

public class Input implements KeyListener{
     public static boolean up,down,left,right,space,exit, enter, mouseClicked;
     int clickX, clickY;
     
     public Input(){
     	up = false;
     	down = false;
     	left = false;
     	right = false;
     	space = false;
     	enter = false;
     	clickX = 0;
     	clickY = 0;
     }
     public void keyTyped(KeyEvent e) {}
     public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
         	  exit = true; 
         	  System.exit(0);
         }
         else if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
         	if(e.isControlDown() && e.isShiftDown()){
         		Environment.offsetX+=1000;
         	}
         	else if(e.isControlDown()){
         		Environment.offsetX+=100;
         	}
         	else{
         		Environment.offsetX+=10;
         	}
         	left = true; 
         }
         else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
         	if(e.isControlDown() && e.isShiftDown()){
         		Environment.offsetX-=1000;
         	}
         	else if(e.isControlDown()){
         		Environment.offsetX-=100;
         	}
         	else{
         		Environment.offsetX-=10;
         	}
         	right = true; 
         }
         else if (e.getKeyCode() == KeyEvent.VK_UP) {
         	if(e.isControlDown() && e.isShiftDown()){
         		Environment.offsetY+=1000;
         	}
         	else if(e.isControlDown()){
         		Environment.offsetY+=100;
         	}
         	else{
         		Environment.offsetY+=10;
         	}
         	up = true;
         }
         else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
         	if(e.isControlDown() && e.isShiftDown()){
         		Environment.offsetY-=1000;
         	}
         	else if(e.isControlDown()){
         		Environment.offsetY-=100;
         	}
         	else{
         		Environment.offsetY-=10;
         	}
         	down = true;
         }
         else if(e.getKeyCode() == 121){// F10
         	Environment.lifeSize = !Environment.lifeSize;
         }
         else if(e.getKeyCode() == 122){// F11
         	Environment.offsetX = -1 * Environment.obj1.getX().divide(
         		Environment.res).intValue();
         	
         	Environment.offsetY = -1 * Environment.obj1.getY().divide(
         		Environment.res).intValue();         		
         }
         else if(e.getKeyCode() == 123){	// F12
         	Environment.offsetX = -1 * Environment.obj2.getX().divide(Environment.res).intValue();
         	
         	Environment.offsetY = -1 * Environment.obj2.getY().divide(Environment.res).intValue();
         }
         else if (e.getKeyCode() == KeyEvent.VK_SPACE) { space = true;}
         else if (e.getKeyCode() == KeyEvent.VK_ENTER) { enter = true;}
         else if (e.getKeyChar() == '+'){
     		if(e.isControlDown()){
     			Environment.zoomIn(10);
     		}
     		else{
				Environment.zoomIn(100);
     		}
         }
         else if (e.getKeyChar() == '-'){
     		if(e.isControlDown()){
     			Environment.zoomOut(10);
     		}
     		else{
     			Environment.zoomOut(100);
     		}
       	 }

     }
     public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_ESCAPE){  exit = false; }
         else if (e.getKeyCode() == KeyEvent.VK_LEFT) { left = false; }
         else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { right = false; }
         else if (e.getKeyCode() == KeyEvent.VK_DOWN) {  down = false; }
         else if (e.getKeyCode() == KeyEvent.VK_UP) { up = false; }
         else if (e.getKeyCode() == KeyEvent.VK_SPACE) { space = false;}
         else if (e.getKeyCode() == KeyEvent.VK_ENTER) { enter = false;}
     }

 }
