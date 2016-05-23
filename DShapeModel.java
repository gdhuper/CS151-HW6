	import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;


public class DShapeModel {
	
	private int X;
	private int Y;
	private int width;
	private int height;
	private Rectangle bounds;
	private String text;
	private int ID;
	private boolean selected;
	
	private ArrayList<ModelListener> listeners = new ArrayList<ModelListener>();
	


	private Color c;
	
	public DShapeModel()
	{
		new	DShapeModel(X, Y, width, height, selected, ID);
	}
	
	public DShapeModel(int x, int y, int w, int h, boolean selected, int ID)
	{
		this.X = 10;  // not sure if the x,y and width and height are supposed to be  fixed 
		this.Y = 10;
		this.width = 20;
		this.height = 20; 
		this.bounds = new Rectangle(X, Y, width, height);
		this.c = Color.GRAY;
		selected = false;
		this.ID = ID;
		listeners = new ArrayList<ModelListener>(); //For storing model listener
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getX() {
		return X;
	}


	public void setX(int newX) {
		X = newX;
		notifyChanges();
	}

	public int getID()
	{
		return this.ID;
	}
	
	public void setID(int id)
	{
		this.ID = id; 
	}

	public int getY() {
		return Y;
	}


	public void setY(int newY) {
		Y = newY;
		notifyChanges();
	}


	public Color getColor() {
		return c;
	}
	/**
	 * Get the coordinates of the top left corner
	 * @return the x and y coordinates
	 */
	public Point getPointLocation()
	{
		return bounds.getLocation();
	}
	
	/**
	 * Sets the new location of the top left coordinate
	 * @param p the point to be set as new location 
	 */
	public void setPointLocation(Point p)  //set x and y method sorta!
	{
		bounds.setLocation(p.x, p.y);
	}

	public void setColor(Color newC) {
		this.c = newC;
	}
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
		notifyChanges();
	}
	
	public void addListener(ModelListener l)
	{
		listeners.add(l);
	}
	
	public void removeListener(ModelListener l)
	{
		listeners.remove(l);
	}

	
	public String getTextToDraw()
	{
		return text;
	}
	
	public void setTextToDraw(String s)
	{
		text = s;
		notifyChanges();
	}
	
	public void moveBy(int  dx, int dy)
	{
	
		this.setX(dx);
		this.setY(dy);
		
		notifyChanges();
	}
	

	
	
	public void notifyChanges() {
		for(ModelListener mL : listeners)
		{
			mL.modelChanged(this);
		}
	}
	
	//create a method to notify change to listeners list.
	

}
