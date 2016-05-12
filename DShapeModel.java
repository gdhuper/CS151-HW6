import java.awt.Color;
import java.awt.Graphics;


public class DShapeModel {
	
	private int X;
	private int Y;
	Graphics g;
	
	
	public DShapeModel()
	{
		this.X = 0;
		this.Y = 0;
		g.setColor(Color.GRAY);
		
		
	}

	public int getX() {
		return X;
	}


	public void setX(int x) {
		X = x;
	}


	public int getY() {
		return Y;
	}


	public void setY(int y) {
		Y = y;
	}


	public Graphics getG() {
		return g;
	}


	public void setG(Graphics g) {
		this.g = g;
	}
	
	

}
