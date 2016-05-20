import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel implements ModelListener{
	
	 public int oldX, oldY, oldW, oldH;	
	 private Color color;
	 Font font;
	 private String text;
	 private ArrayList<DShape> shapesList = new ArrayList<DShape>();

	
	
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
		
		// To get the point where mouse was clicked
		addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	               mousePressed(e);
	            }
	        });
				
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
               int x = e.getX();
               int y = e.getY();
               
               for(DShape s: shapesList)
               {
            	 // to get the bouds and test it 
               }
            }
        });
		
		
	}
	/**
	 * Override the paint component to draw the shape.
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(getColor());
		super.paintComponent(g);
		this.setOpaque(true);
		
		for(DShape s : shapesList)
		{
			s.draw(g);
		}
	
	}
	
	
	/**
	 * Method to clean the canvas
	 */
	public void clearCanvas()
	{
		shapesList.clear();
		repaint();
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color c)
	{
		color = c;
	}
	public void setText(String s)
	{
		text = s;
	}
	public String getText()
	{
		return text;
	}
	public void addShape(DShapeModel s) // connect with the drawwing 
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
			temp.setText(getText());
		}
		//s.addListener(this);
		shapesList.add(temp);
		s.setColor(getColor());
		
	}
	

	
	
	/**
	 * For debugging purposes 
	 */
	public void print()
	{
		for(DShape m : shapesList)
		{
			System.out.println(m);
		}
	}
	
	public void setFont(Font f)
	{
		font = f;
	}
	




	@Override
	public void modelChanged(DShapeModel model) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
