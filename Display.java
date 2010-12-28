package GravitationalSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Display extends JInternalFrame implements ActionListener{	
	JMenuBar menuBar;
	JMenu options;
	
	JCheckBoxMenuItem visibleGrid, realSize;
	JButton zoomIn, zoomOut, vObj1, vObj2;
		
	public Display(){
		setTitle("Display");
		setBounds(25, 25, 600, 600);
		toFront();
		setFocusable(true);
		setClosable(false);
		setMaximizable(true);
		setResizable(true);
		setIconifiable(true);
		requestFocus();
		
		menuBar = new JMenuBar();
		options = new JMenu("Options");
		
		visibleGrid = new JCheckBoxMenuItem("Visible Grid", Environment.gridded);
		realSize = new JCheckBoxMenuItem("Scaled Size", Environment.lifeSize);
		
		zoomIn = new JButton("Zoom In");
		zoomOut = new JButton("Zoom Out");
		
		vObj1 = new JButton("Object 1");
		vObj2 = new JButton("Object 2");
		
		
		visibleGrid.setFocusable(false);
		realSize.setFocusable(false);
		zoomIn.setFocusable(false); 
		zoomOut.setFocusable(false);
		vObj1.setFocusable(false);
		vObj2.setFocusable(false);
		
		visibleGrid.addActionListener(this);
		realSize.addActionListener(this);
		zoomIn.addActionListener(this);
		zoomOut.addActionListener(this);
		vObj1.addActionListener(this);
		vObj2.addActionListener(this);
		
		menuBar.add(visibleGrid);
		menuBar.add(realSize);
		menuBar.add(zoomIn);
		menuBar.add(zoomOut);
		menuBar.add(vObj1);
		menuBar.add(vObj2);

		setMenuBar(menuBar);
		
		setContentPane(new DisplayWindow());
		setVisible(true);
		
		addKeyListener(GravitySim.input);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();

		if(source == visibleGrid){
			Environment.gridded = visibleGrid.getState();
		}
		else if(source == realSize){
			Environment.lifeSize = realSize.getState();
		}
		else if(source == zoomIn){
			Environment.zoomIn(10);
		}
		else if(source == zoomOut){
			Environment.zoomOut(10);
		}
		else if(source == vObj1){
			Environment.offsetX = -1 * Environment.obj1.getX().divide(Environment.res).intValue();
         	Environment.offsetY = -1 * Environment.obj1.getY().divide(Environment.res).intValue();
		}
		else if(source == vObj2){
			Environment.offsetX = -1 * Environment.obj2.getX().divide(Environment.res).intValue();
         	Environment.offsetY = -1 * Environment.obj2.getY().divide(Environment.res).intValue();
		}
	}
}
