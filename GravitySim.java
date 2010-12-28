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


public class GravitySim extends JFrame implements ActionListener{
	// Try reset with a velocity of 9000
	// 376293 is the distance between...
	
	
	// Try:
	// 11500000 and 13884401
	static Input input = new Input();
	static Environment env;
	public static void main(String args[]){
		try{
	    	File f = new File("Data.txt");
			if(f.exists()){	f.delete();	}
			f.createNewFile();
			PrintStream out = new PrintStream(f);
//			System.setOut(out);
			
	    	File er = new File("Errors.txt");
			if(er.exists()){	er.delete();	}
			er.createNewFile();
			PrintStream err = new PrintStream(er);
			System.setErr(err);
			
			env = new Environment();
			new Thread(env).start();
			new GravitySim();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	JMenuBar menuBar;
	JMenu simulation, objects, data;
	
	// Main buttons
	JButton run, step, reset;
	
	// Simulation menu
	JMenuItem newSim, close;
	
	// Objects menu
	JMenuItem obj1, obj2;
	
	// Data Menu
	JMenuItem mainDat;
	
	static SimulationEnv desktop;
	
	public GravitySim(){
		
		desktop = new SimulationEnv();
		pack();
		add(desktop, BorderLayout.CENTER);
		setBounds(265,0,1024,768);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
		
		menuBar = new JMenuBar();
		
		simulation = new JMenu("Simulation");
		newSim = new JMenuItem("New");
		close = new JMenuItem("Exit");
		
		simulation.add(newSim);
		simulation.add(close);
		
		menuBar.add(simulation);
		
		objects = new JMenu("Objects");
		obj1 = new JMenuItem("Object 1");
		obj2 = new JMenuItem("Object 2");
		
		objects.add(obj1);
		objects.add(obj2);
		
		menuBar.add(objects);
		
		data = new JMenu("Data");
		mainDat = new JMenuItem("Main Data");

		data.add(mainDat);
		
		menuBar.add(data);
		
		run = new JButton("Pause/Run");
		step = new JButton("Step");
		reset = new JButton("Reset");
		
		menuBar.add(run);
		menuBar.add(step);
		menuBar.add(reset);
	
		run.addActionListener(this);
		step.addActionListener(this);
		reset.addActionListener(this);
		newSim.addActionListener(this);
		close.addActionListener(this);
	
		obj1.addActionListener(this);
		obj2.addActionListener(this);
	
		
	 	mainDat.addActionListener(this);

		setJMenuBar(menuBar);
	}
	
	public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();
		if(source == run){
			env.running = !env.running;
		}
		else if(source == step){
			env.act();
		}
		else if(source == reset){
			Environment.reset();
		}
		else if(source == newSim){
			Environment.reset();
		}
		else if(source == close){
			System.exit(0);
		}
		else if(source == obj1){
			SimulationEnv.config1.setVisible(true);
			SimulationEnv.config1.requestFocus();
			SimulationEnv.config1.toFront();
			SimulationEnv.config1.show();
			desktop.add(SimulationEnv.config1);
		}
		else if(source == obj2){
			SimulationEnv.config2.setVisible(true);
			SimulationEnv.config2.requestFocus();
			SimulationEnv.config2.toFront();
			SimulationEnv.config2.show();
			desktop.add(SimulationEnv.config2);
		}
		else if(source == mainDat){
			try{
				Process p = Runtime.getRuntime().exec("notepad Data.txt");			
			}
			catch(Exception e){System.out.println(e);	}
		}
	}
}