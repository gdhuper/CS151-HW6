import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class DRect extends DShape {
	
	

	Random random = new Random();
	int x = random.nextInt(200);
	int y = random.nextInt(200);
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

		g.fillRect(x, y,  20,  20);
	}

}
