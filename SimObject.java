package GravitationalSimulation;

import java.awt.*;
import java.math.*;	
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public class SimObject{
	
	BigInteger x, y;	// Miles
	double radius;	// Miles
	double mass;	// KG
	BigInteger force;	// Newtons
	
	Image img;
	
	double vX = 0;
	double vY = 0;
	
	public SimObject(BigInteger startX, BigInteger startY, 
		double rad, double objMass){
		x = startX; 
		y = startY;
		radius = rad;
		mass = objMass;
	}
	
	public void calculate(SimObject obj2){
		double xDist = getXDistance(obj2);
		double yDist = -1*getYDistance(obj2);
		
		double dist = StrictMath.sqrt( (StrictMath.pow(xDist, 2.0)) + (StrictMath.pow(yDist,2.0)) );
		
		double xRat = Math.abs(xDist/dist);
		double yRat = Math.abs(yDist/dist);
		
		System.out.println("Dist: "+xDist+" , "+yDist);
		System.out.println("Ratios: "+xRat+" , "+yRat);
		
		BigInteger m = new BigDecimal(getMass()).toBigInteger();
		
		double acc = ((Environment.computeForce(getMass(), obj2.getMass(), dist)).divide(m)).doubleValue();
				
		double accX = xRat * acc;
		double accY = yRat * acc;
		
		System.out.println("Pure accXY: "+accX+" "+accY);

		if(xDist > 0){
			accX *= -1;
		}
		if(yDist < 0){
			accY *= -1;
		}

		System.out.println("VX: "+vX+" + "+accX);
		System.out.println("VY: "+vY+" + "+accY);
		
		vX+=accX;
		vY+=accY;
		
		System.out.println("Vx: "+vX+" vY: "+vY);
//		double totalRadius = getRadius()+obj2.getRadius();
//		boolean collision = false;
		System.out.println();
	}

	public double getDistance(SimObject obj2){
		double xDist = getXDistance(obj2);
		double yDist = getYDistance(obj2);
		double dist = StrictMath.sqrt( (StrictMath.pow(xDist, 2.0)) + (StrictMath.pow(yDist,2.0)) );
		return dist;
	}
	public double getXDistance(SimObject obj2){	// 100 - 10 = 90
		return getCenterX().subtract(obj2.getCenterX()).doubleValue();
	}
	public double getYDistance(SimObject obj2){
		return getCenterY().subtract(obj2.getCenterY()).doubleValue();
	}
	public boolean collides(SimObject obj2){
		double totalRadius = obj2.getRadius() + getRadius();
		double dist = getDistance(obj2);
		if(dist < totalRadius){
			System.out.println();
			return true;
		}
		return false;
	}
	
	public SimObject getPeerObj(){
		if(Environment.obj1 == this){return Environment.obj2;	}
		else{return Environment.obj1;}
	}
	public BigInteger getX(){	return x;	}
	public BigInteger getY(){	return y;	}
	public double getMass(){	return mass;	}
	public double getRadius(){return radius;	}
	public BigInteger getCenterX(){	
		BigInteger rad = new BigDecimal(radius).toBigInteger();
		return x.add(rad);
	}
	public BigInteger getCenterY(){	
		BigInteger rad = new BigDecimal(radius).toBigInteger();
		return y.add(rad);	
	}
	public BigInteger getForce(){	return force;	}
	public void setRadius(double r){radius = r;}
	public void setX(BigInteger newX){	x = newX;}
	public void setY(BigInteger newY){	y = newY;}
	public void setCenterX(BigInteger newX){
		BigInteger rad = new BigDecimal(radius).toBigInteger();
		x = newX.subtract(rad);
	}
	public void setCenterY(BigInteger newY){
		BigInteger rad = new BigDecimal(radius).toBigInteger();
		y = newY.subtract(rad);
	}	
	public void setMass(double newMass){mass = newMass;	}
	public void setImage(Image i){
		img = i;
	}
	
	public String toString(){
		return "("+x+","+y+") ";
	}
}