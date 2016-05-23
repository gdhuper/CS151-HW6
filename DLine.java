import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.Random;


class DLine extends DShape{
	
	Random random = new Random();
	int x = random.nextInt(200);
	int y = random.nextInt(200);
	
	
	public DLine()
	{
		super();
	}
	
	
	
	public void draw(Graphics g)
	{
		
		g.setColor(getColor());
		g.drawLine(x, y,  x + 40, x + 20);
		
		
	}
}