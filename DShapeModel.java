import java.awt.Color;
import java.awt.Graphics;


public class DShapeModel {
	
	private int X;
	private int Y;
	private int width;
	private int height;
	
	private Color c;
	
	
	public DShapeModel()
	{
		this.X = 0;
		this.Y = 0;
		this.width = 0;
		this.height = 0; 
		this.c = Color.GRAY;		
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


	public Color getG() {
		return c;
	}


	public void setG(Color newC) {
		this.c = newC;
	}
	
	

}
