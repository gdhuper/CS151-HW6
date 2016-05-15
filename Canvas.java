import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel implements MouseListener{
	
	 boolean buttonRect = false;
	 boolean buttonOval = false;
	 boolean buttonLine = false;
	 boolean buttonText = false;
	
	 static ArrayList<DShape> shapesList = new ArrayList<DShape>();

	
	
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
	/**
	 * Override the paint component to draw the shape.
	 */
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		this.setOpaque(true);
		
		for(DShape s : shapesList)
		{
			s.draw(g);
		}
		
		
		
	/**	if(buttonRect == true)
		{
		g.drawRect(10, 10, 50, 50);
		g.setColor(Color.CYAN);
		
		g.fillRect(10, 10, 50,50);
		setRectF();
		}
	 if(buttonOval == true)
		{
			g.drawOval(244, 10, 50, 50);
			
			g.setColor(Color.YELLOW);
			g.fillOval(244, 10, 50, 50);
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
	 //}*/
		
	}
	
	
	public void addToList(DShapeModel s) // connect with the drawwing 
	{
		DShape temp = null;
		if(s instanceof DRectModel)
		{
			temp = new DRect();
		}
		if(s instanceof DOvalModel)
		{
			temp = new DOval();
		}
		if(s instanceof DLineModel)
		{
			temp = new DLine();
		}
		if(s instanceof DTextModel)
		{
			temp = new DText();
		}
		
		shapesList.add(temp);
		
	}
	
	public void remove(DShapeModel s)
	{
		shapesList.remove(s);
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
	
	
	
	public void print()
	{
		for(DShape m : shapesList)
		{
			System.out.println(m.toString());
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
