import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class DOval extends DShape  {
	
	Random random = new Random();
	int x = random.nextInt(200);
	int y = random.nextInt(200);
	
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

		g.fillOval(x, y, 20, 20);
	}

}
