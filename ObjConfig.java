package GravitationalSimulation;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.math.*;
import javax.imageio.*;
import java.io.*;

public class ObjConfig extends JInternalFrame implements ActionListener {
	SimObject obj;
	
	JLabel imagePreview;
	
	JTextField txtX, txtY, txtMass, txtVX, txtVY, txtRad, imageLoc;
	String name = "";

	
	public ObjConfig(SimObject o){
		obj = o;
		
		setTitle("Object Configuration");
		setBounds(25, 25, 100, 450);
		toFront();
		setFocusable(true);
		setClosable(true);
		setMaximizable(true);
		setResizable(false);
		setIconifiable(true);
		requestFocus();
		
		imagePreview = new JLabel();
		imageLoc = new JTextField(10);
		
      	JLabel objX = new JLabel("X");
      	JLabel objY = new JLabel("Y");
      	JLabel objMass = new JLabel("Mass");
      	JLabel velX = new JLabel("VX");
      	JLabel velY= new JLabel("VY");
      	JLabel objRad = new JLabel("Radius");
      	
      	
      	txtX = new JTextField(""+obj.getX(), 10);
      	txtY = new JTextField(""+obj.getY(), 10);
      	txtMass = new JTextField(""+obj.getMass(), 10);
      	txtVX = new JTextField(""+obj.vX, 10);
      	txtVY = new JTextField(""+obj.vY, 10);
      	txtRad = new JTextField(""+obj.radius, 10);
      	
      	txtX.addActionListener(this);
      	txtY.addActionListener(this);
      	txtMass.addActionListener(this);
      	txtVX.addActionListener(this);
      	txtVY.addActionListener(this);
      	txtRad.addActionListener(this);
      	imageLoc.addActionListener(this);
      	
      	JPanel myPanel = new JPanel();
      	myPanel.add(objX);
      	myPanel.add(txtX);
      	myPanel.add(objY);
      	myPanel.add(txtY);
      	myPanel.add(objMass);
      	myPanel.add(txtMass);
      	myPanel.add(velX);
     	myPanel.add(txtVX);
      	myPanel.add(velY);
     	myPanel.add(txtVY);
      	myPanel.add(objRad);
     	myPanel.add(txtRad);     	
     	myPanel.add(imageLoc);
     	myPanel.add(imagePreview);
      	this.add(myPanel);
      	
      	setVisible(true);
	}
	
	public void setName(String n){
		name = n;
		setTitle(name);
	}
	
	public void refreshData(){
		txtX.setText(""+obj.getX());
		txtY.setText(""+obj.getY());
		txtMass.setText(""+obj.getMass());
		txtVX.setText(""+obj.vX);
      	txtVY.setText(""+obj.vY);
      	txtRad.setText(""+obj.radius);
	}
	
	public void setImage(String str){
		try{ 
			Image img = ImageIO.read(new File(str));
			if(img != null){
				imagePreview.setIcon(new ImageIcon(img.getScaledInstance(100,100,Image.SCALE_DEFAULT)));
				obj.img = img;
				imageLoc.setText(str);
			}
		}
		catch(Exception e){	System.out.println(e);}

	}
	
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if(source == txtX){
			obj.setX(new BigInteger(txtX.getText()));
		}
		else if(source == txtY){
			obj.setY(new BigInteger(txtY.getText()));
		}
		else if(source == txtMass){
			obj.setMass(Double.parseDouble(txtMass.getText()));
		}
		else if(source == txtVX){
			obj.vX = (Double.parseDouble(txtVX.getText()));
		}
		else if(source == txtVY){
			obj.vY = (Double.parseDouble(txtVY.getText()));
		}
		else if(source == txtRad){
			obj.radius = Double.parseDouble(txtRad.getText());
		}
		else if(source == imageLoc){
			setImage(imageLoc.getText());
		}
	}
}