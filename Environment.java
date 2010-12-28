package GravitationalSimulation;

import java.awt.*;
import javax.swing.*;
import java.math.*;	
import java.awt.geom.*;

public class Environment implements Runnable{
	static SimObject obj1;
	static SimObject obj2;
		
	static boolean gridded = true, lifeSize = false;

	final static Double G = (Double) (0.0000000000667  * 1);//00000000;
//	final static Double G = (Double) (0.0000000000000000000667  * 1);//00000000;
	static int offsetX = -830;
	static int offsetY = -970;
	
	static boolean running = false;
	
	static int resolution = 10000;
	
	static BigInteger res = new BigInteger(""+resolution);
	
	static int turns = 0;
	
	public Environment(){
		System.out.println("G : "+G);
		obj1 = new SimObject(new BigInteger("11500000"),new BigInteger("11504634"),
		1737.10,5973600000000000000000000.0);
		
//		obj2 = new SimObject(new BigInteger("11884401"),new BigInteger("11300000"),
//		obj2 = new SimObject(new BigInteger("11695366"),new BigInteger("11300000"),
		obj2 = new SimObject(new BigInteger("13884401"),new BigInteger("11506951"),	// Using meters
		6371,73490000000000000000000.0);
		
		obj2.vY = 9000;
		
		System.out.println(obj1);
		System.out.println(obj2);
	}
	
	public static void reset(){
		turns = 0;
		lifeSize = false;
		gridded = true;
		offsetX = -830;
		offsetY = -970;
		running = false;
		resolution = 10000;
		res = new BigInteger(""+resolution);
		
		obj1.setX(new BigInteger("11500000"));
		obj1.setY(new BigInteger("11504634"));
		obj1.vX = 0;
		obj1.vY = 0;
		obj1.setRadius(1737.10);
		obj1.setMass(73490000000000000000000.0);

		obj2.setX(new BigInteger("11884401"));
		obj2.setY(new BigInteger("11500000"));
		obj2.vX = 0;
		obj2.vY = 0;
		obj2.setRadius(6371.0);
		obj2.setMass(5973600000000000000000000.0);		
		
		collision = false;
		
		SimulationEnv.config1.refreshData();
		SimulationEnv.config2.refreshData();
	}
	SimObject obj1Copy, obj2Copy;
	
	public void act(){
		turns++;
		obj1Copy = new SimObject(obj1.getX(), obj1.getY(), obj1.getRadius(), obj1.getMass());
		
		obj2Copy = new SimObject(obj2.getX(), obj2.getY(), obj2.getRadius(), obj2.getMass());
		
		obj1.calculate(obj2Copy);
		obj2.calculate(obj1Copy);
		
		collisionCheck();
		if(!collision){
			obj1.setCenterX(obj1Copy.getCenterX().add(new BigDecimal(obj1.vX).toBigInteger()));
			obj1.setCenterY(obj1Copy.getCenterY().add(new BigDecimal(obj1.vY).toBigInteger()));
			
			obj2.setCenterX(obj2Copy.getCenterX().add(new BigDecimal(obj2.vX).toBigInteger()));
			obj2.setCenterY(obj2Copy.getCenterY().add(new BigDecimal(obj2.vY).toBigInteger()));
			
			SimulationEnv.config1.refreshData();
			SimulationEnv.config2.refreshData();
		}
	}
	double maxMoveRes = 10000;// 1000000
	public void collisionCheck(){
		double moveRes = maxMoveRes;
		
		double vX1 = obj1.vX/moveRes;
		double vX2 = obj2.vX/moveRes;
		
		double vY1 = obj1.vY/moveRes;
		double vY2 = obj2.vY/moveRes;

		double xDist = obj1.getXDistance(obj2);
		double yDist = obj1.getYDistance(obj2);
		
		double dist = StrictMath.sqrt( (StrictMath.pow(xDist, 2.0)) + (StrictMath.pow(yDist,2.0)) );
		double originalDist = dist;
		
		double totalRadius = obj1.getRadius()+obj2.getRadius();
		
		while(moveRes > 0 && !collision){
			moveRes--;
			xDist+=vX1;
			xDist+=vX2;
			
			yDist+=vY1;
			yDist+=vY2;
			
			dist = StrictMath.sqrt( (StrictMath.pow(xDist, 2.0)) + (StrictMath.pow(yDist,2.0)) );
			
			if(StrictMath.abs(dist) <= totalRadius){
				System.out.println();
				System.out.println("Collision Detected");
				System.out.println("Total Radius "+totalRadius);
				System.out.println(obj1);
				System.out.println(obj2);
				System.out.println("Distance "+dist+" ("+xDist+","+yDist+")");
				System.out.println();
				JOptionPane.showMessageDialog(null, "Collision Detected. Turn: "+turns);

				running = false;
				collision = true;
			}
		}
		System.out.println("No Collision: "+dist+" ( "+xDist+" , "+yDist+" ) ");
	}
	static boolean collision = false;

	public Double toNewtons(Double kgWeight){
		return (Double)(kgWeight * 9.8);
	}
	public Double toKG(Double Newtons){
		return (Double)(Newtons / 9.8);
	}

	public static BigInteger computeForce(double m1, double m2, double r){
		if(r==0){
			return new BigInteger("0");
		}
		BigDecimal rSquared = new BigDecimal(StrictMath.pow(r,2.0));
//		System.out.println(r+ "  R Squared: "+rSquared);
		BigDecimal F = new BigDecimal(m1 * m2);
		F = F.divide(rSquared,RoundingMode.HALF_UP);
		F = F.multiply(new BigDecimal(G));
		System.out.println("Force: "+F);
		return F.toBigInteger();
	}

	public void run(){
		while(true){
			try { Thread.sleep(250); } catch (Exception e) {System.out.println(e);}
			if(running)
				act();
		}
	}
	
	public static void zoomIn(int amount){
		if(Environment.resolution >= amount){		Environment.resolution/=amount;		}
		else{	Environment.resolution=1;	}
		
		Environment.res = new BigInteger(""+resolution); 
   		System.out.println("Resolution : "+Environment.resolution);
	}
	
	public static void zoomOut(int amount){
		Environment.resolution*=amount;
		Environment.res = new BigInteger(""+resolution); 
		System.out.println("Resolution : "+Environment.resolution);
	}
	
	public static Graphics drawPlanets(Graphics g){
		int obj1Rad = (int) obj1.getRadius();
		int obj2Rad = (int) obj2.getRadius();
				
		if(resolution != 0){
			obj1Rad/=resolution;
			obj2Rad/=resolution;
		}
		
		if(!lifeSize){
			g.setColor(new Color(253,253,253));
			g.fillOval( (obj1.getX().divide(res)).intValue()+Environment.offsetX,(obj1.getY().divide(Environment.res)).intValue()+Environment.offsetY,
			5, 5);

			g.setColor(new Color(0,0,253));
			g.fillOval((obj2.getX().divide(res)).intValue()+Environment.offsetX,(obj2.getY().divide(Environment.res)).intValue()+Environment.offsetY,
			5, 5);
		}
		else{
			if(obj1.img == null){
				g.setColor(new Color(253,253,253));

				g.fillOval( (obj1.getX().divide(Environment.res)).intValue()+Environment.offsetX,(obj1.getY().divide(Environment.res)).intValue()+Environment.offsetY,
				obj1Rad, obj1Rad);
			}
			else{
				g.drawImage(obj1.img, (obj1.getX().divide(Environment.res)).intValue()+Environment.offsetX,(obj1.getY().divide(Environment.res)).intValue()+Environment.offsetY,
				obj1Rad, obj1Rad, null);
			}
			
			if(obj2.img == null){
				g.setColor(new Color(0,0,253));
				g.fillOval((obj2.getX().divide(Environment.res)).intValue()+Environment.offsetX,(obj2.getY().divide(Environment.res)).intValue()+Environment.offsetY,
				obj2Rad, obj2Rad);
			}
			else{
				g.drawImage(obj2.img, (obj2.getX().divide(Environment.res)).intValue()+Environment.offsetX,(obj2.getY().divide(Environment.res)).intValue()+Environment.offsetY,
				obj2Rad, obj2Rad, null);
			}

		}
		
		g.setColor(Color.white);
		g.drawString("Zoom: "+Environment.res,5,580);
		g.drawString("("+(-1*Environment.offsetX)+","+(-1*Environment.offsetY)+")",5,590);
		
		return g;
	}
}