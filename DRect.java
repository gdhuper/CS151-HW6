import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class DRect extends DShape {
	
	

	
	public DRect(){
		super();
	}
	
/**	public int getX()
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

		g.fillRect(10, 10, 20, 20);
	}

}
