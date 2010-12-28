package GravitationalSimulation;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.math.*;
import javax.imageio.*;
import java.io.*;

public class DataView extends JInternalFrame{
	SimObject obj;
	
	JTextArea textArea;
	
	public DataView(SimObject o){
		obj = o;
		
		setTitle("Data Viewer");
		setBounds(25, 25, 500, 500);
		toFront();
		setFocusable(true);
		setClosable(false);
		setMaximizable(true);
		setResizable(true);
		setIconifiable(true);
		requestFocus();
		
		textArea = new JTextArea();
				
		add(textArea);
      	setVisible(true);
	}
	
	BufferedReader reader;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		File f = new File("Data.txt");
		
		try{
			
		textArea.setText("");
		reader = new BufferedReader(new FileReader(f));
		
		String str = "";
		while( !(str = reader.readLine()).equals("")){
			textArea.append(str+"\n");
		}
				reader.close();

		}
		catch(Exception e){System.out.println(e);	}
		

		repaint();
	}
}