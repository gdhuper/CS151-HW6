import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class DShapeModel {
	
	private int X;
	private int Y;
	private int width;
	private int height;
	private Rectangle bounds;
	
	private ArrayList<ModelListener> list;
	


	private Color c;
	
	
	public DShapeModel()
	{
		this.X = 10;  // not sure if the x,y and width and height are supposed to be  fixed 
		this.Y = 10;
		this.width = 20;
		this.height = 20; 
		this.bounds = new Rectangle(X, Y, width, height);
		this.c = Color.GRAY;
		list = new ArrayList<ModelListener>(); //For storing model listener
	}

	public int getX() {
		return X;
	}


	public void setX(int newX) {
		X = newX;
	}


	public int getY() {
		return Y;
	}


	public void setY(int newY) {
		Y = newY;
	}


	public Color getColor() {
		return c;
	}


	public void setColor(Color newC) {
		this.c = newC;
	}
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	

}
