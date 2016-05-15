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
		Random n = new Random();
		int x = n.nextInt(100);
		int y = n.nextInt(100);
		g.drawLine(x, y, x+ 20, y + 20);
		
		g.setColor(Color.CYAN);
	}
}