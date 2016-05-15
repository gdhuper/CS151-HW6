import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class DOval extends DShape  {
	
	Random n = new Random();
	int x = n.nextInt(100);
	int y = n.nextInt(100);
	public DOval()
	{
		super();
	}
	
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public void draw(Graphics g)
	{
		
	
		g.drawOval(x, y, 20, 20);
		g.setColor(Color.CYAN);
		g.fillOval(x, y, 20, 20);
	}

}
