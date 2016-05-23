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
	
	 public int lastX, lastY, lastW, lastH;	
	 private Color color; 
	 public final int SIZE = 20;
	 private DShapeModel selectedShape;
	 private boolean print;
	 private boolean smartRepaint;
	 private boolean oldRepaint;
	 private boolean dirty;
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
		
		print = false;
		smartRepaint = false;
		oldRepaint = true;
		
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

				DShapeModel shapeModel = findModel(x, y);
				if(shapeModel != null)
				{
					selectedShape = shapeModel;
				}
				lastX = selectedShape.getX();
				lastY = selectedShape.getY();
			}

		});
				
		addMouseListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
            	if(selectedShape != null)
            	{
            		
            		int dx = e.getX() - lastX;
            		int dy = e.getY() - lastY;
            		
            		lastX = e.getX();
            		lastY = e.getY();
            		
            		selectedShape.moveBy(dx, dy);
            		addShape(selectedShape);
            		
            		repaint(); //
            		
            
            	}
            }
        });
		
		
	}
	
	
	
	public void doMove(DShapeModel d, int dx, int dy)
	{
		if(!smartRepaint)
		{
			d.moveBy(dx, dy);
			repaint();
		}
		else
		{
			if(oldRepaint)
			{
				repaintModel(d);
			}
			
			d.moveBy(dx, dy);
			repaintModel(d);
		}
		setDirty(true);
	}
	
	
	public void repaintModel(DShapeModel model)
	{
		repaint(model.getX() - SIZE/2,  model.getY() - SIZE/2,  SIZE + 1 , SIZE + 1 );
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
		s.addListener(this);
	
		
		
	}
	
	
	public DShapeModel findModel(int x, int y)
	{
		for(int i = modelShape.size() -1 ; i >=0; i-- )
		{
			DShapeModel temp = modelShape.get(i);
			Rectangle bounds = temp.getBounds();
			int centerX = (int) bounds.getX();
			int centerY = (int) bounds.getY();
			
			int wTemp =  (int) bounds.getWidth();
			int hTemp = ( int) bounds.getHeight();

			if (x >= centerX && x <= wTemp && y >= centerY && y <= hTemp)
			{
				return temp;
			}
			
		}
		return null;
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
	
	
	
	

	 public boolean isDirty() {
		return dirty;
	}




	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}




	public DShapeModel getSelectedShape() {
		return selectedShape;
	}




	public void setSelectedShape(DShapeModel selectedShape) {
		this.selectedShape = selectedShape;
	}




	public boolean isPrint() {
		return print;
	}




	public void setPrint(boolean print) {
		this.print = print;
	}




	public boolean isSmartRepaint() {
		return smartRepaint;
	}




	public void setSmartRepaint(boolean smartRepaint) {
		this.smartRepaint = smartRepaint;
	}




	public boolean isOldRepaint() {
		return oldRepaint;
	}




	public void setOldRepaint(boolean oldRepaint) {
		this.oldRepaint = oldRepaint;
	}



	
	 
	 
	
	 
	 

	
	
	

}
