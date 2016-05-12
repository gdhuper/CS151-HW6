import java.awt.Color;
import java.awt.Graphics;


public class DOval extends DShape  {
	
	public DOval()
	{
		super();
	}
	
	public void draw(Graphics g)
	{
		g.drawOval(10, 10, 20, 20);
		g.setColor(Color.CYAN);
		g.fillOval(10, 10, 20, 20);
	}

}
