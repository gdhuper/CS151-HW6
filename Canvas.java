import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel implements MouseListener{
	
	 boolean buttonRect = false;
	 boolean buttonOval = false;
	 boolean buttonLine = false;
	 boolean buttonText = false;
	
	 ArrayList<DShape> list = new ArrayList<DShape>();

	
	
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
	
	public void addToList(DShape s) // connect with the drawwing 
	{
		list.add(s);
	}
	
	
	public void setRect()
	{
		 buttonRect = true;
	}
	public void setRectF()
	{
		 buttonRect = false;
	}
	public void setOval()
	{
		 buttonOval = true;
	
	}
	public void setOvalF()
	{
		 buttonOval = false;
	
	}
	public void setLine()
	{
		 buttonLine = true;
	}
	public void setLineF()
	{
		 buttonLine = false;
	}
	public void setText()
	{
		 buttonText = true;
	}
	public void setTextF()
	{
		 buttonText = false;
	}
	
	/**
	 * Override the paint component to draw the shape.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(buttonRect == true)
		{
		g.drawRect(10, 10, 50, 50);
		g.setColor(Color.CYAN);
		
		g.fillRect(10, 10, 50,50);
		setRectF();
		}
	 if(buttonOval == true)
		{
			g.drawOval(10, 10, 50, 50);
			
			g.setColor(Color.YELLOW);
			g.fillOval(10, 10, 50, 50);
			setOvalF();
		}
	 if(buttonLine == true)
		{
			g.drawLine(10, 10, 50, 50);
			g.setColor(Color.BLUE);
			setLineF();
		
		}
	// if(buttonText = true)
	 //{
	//	 g.drawString("WhiteBoard", 30, 30);
	//	 setTextF();
	 //}
		
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
