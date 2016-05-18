import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class DOval extends DShape  {
	
	
	public DOval()
	{
		super();
	}
	
	/**
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}*/
	
	public void draw(Graphics g)
	{
		g.setColor(getColor());

		g.fillOval(10, 10, 20, 20);
	}

}
