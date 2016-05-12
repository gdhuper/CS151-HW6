import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class Canvas extends JPanel implements MouseListener{
	
	public boolean buttonPressed = false;
	
	public Canvas()
	{
		createCanvas();
	}
	
	
	
	
	public void createCanvas()
	{
		
		//JPanel panel = new JPanel();
		
		setBackground(Color.WHITE);
		
		setPreferredSize(new Dimension(400, 400));
		setLayout(new BorderLayout());
		setVisible(true);
		
		//setLayout(new BorderLayout());
		
				
		
		
	}
	
	public void setButton()
	{
		 buttonPressed = true;
	}
	
	/**
	 * Override the paint component to draw the shape.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(buttonPressed == true)
		{
		g.drawRect(10, 10, 100, 100);
		g.setColor(Color.GRAY);
		
		g.fillRect(10, 10, 100, 100);
		}
		
	}
	
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
