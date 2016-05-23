import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Canvas extends JPanel implements ModelListener{
	
	 public int oldX, oldY, oldW, oldH;	
	 private Color color; 
	 private DShapeModel selectedShape;
	 Whiteboard wB;
	 Font font;
	 private String text;
	 private ArrayList<DShape> shapesList = new ArrayList<DShape>();
	 private ArrayList<DShapeModel> modelShape = new ArrayList<DShapeModel>();

	 
	 

	
	
	public Canvas(Whiteboard w)
	{
		createCanvas();
		this.wB = w;
	}
	
	
	
	
	public void createCanvas()
	{
		
		//JPanel panel = new JPanel();
		
		setBackground(Color.WHITE);
		
		setPreferredSize(new Dimension(400, 400));
		setLayout(new BorderLayout());
		setVisible(true);
		
		
		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				mousePressed(e);
			}
			
		});
		
		// To get the point where mouse was clicked
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				for (DShapeModel s : modelShape) {
					Rectangle temp = s.getBounds();
					int tempX = (int) temp.getX();
					int tempY = (int) temp.getY();
					int wTemp = (int) temp.getWidth();
					int hTemp = (int) temp.getHeight();

					if (x >= tempX && x <= tempY && y >= tempY && y <= tempY) {
						s.setSelected(true);
						selectedShape  = s; 
						
					}

				}

			}

		});
				
		addMouseListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
            	if(selectedShape != null)
            	{
            		int lastX = selectedShape.getX();
            		int lastY = selectedShape.getY();
            		int dx = e.getX() - lastX;
            		int dy = e.getY() - lastY;
            		
            		selectedShape.moveBy(dx, dy);
            		
            		
            		repaint();
            		
            
            	}
            }
        });
		
		
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
	public void addShape(DShapeModel s) // connect with the drawing 
	{
		if(wB.isClient() == false)
		{
			modelShape.add(s);
			
		}
		
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
			
		if(wB.getMode() == 1)
		{
			wB.doSend(Whiteboard.Message.add, s);
		}
		
		
		
	}
	

	 public DShape getShapeWithID(int ID)
	 {
		 for(DShape s: shapesList)
		 {
			 if(s.getModelID() == ID)
			 {
				 return s;
			 }
		 }
		 return null;
	 }
	 
	 
	    /**
		 * Override the paint component to draw the shape.
		 */
		public void paintComponent(Graphics g)
		{
			
			super.paintComponent(g);
			g.setColor(getColor());
			
			
			for(DShape s : shapesList)
			{
				s.draw(g);
			}
		
		}
	
	
	/**
	 * For debugging purposes 
	 */
	public void print()
	{
		for(DShapeModel m : modelShape)
		{
			System.out.println(m.getID());
		}
	}
	
	public void setFont(Font f)
	{
		font = f;
	}
	
	public ArrayList<DShape> getList()
	{
		return shapesList;
	}
	
	public ArrayList<DShapeModel> getModelList()
	{
		return modelShape;
	}



	@Override
	public void modelChanged(DShapeModel model) {
		if(wB.isServer() == true)
		{
			wB.doSend(Whiteboard.Message.change, model);
		}
		
	}
	
	
	

}
