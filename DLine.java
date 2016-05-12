import java.awt.Color;
import java.awt.Graphics;


class DLine extends DShape{
	
	public DLine()
	{
		super();
	}
	
	
	public void draw(Graphics g)
	{
		g.drawLine(10, 10, 20, 20);
		g.setColor(Color.CYAN);
	}
}