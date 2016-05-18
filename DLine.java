import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


class DLine extends DShape{
	
	public DLine()
	{
		super();
	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(getColor());
		g.drawLine(10, 10,  20,  20);
		
		
	}
}