import java.awt.Color;
import java.awt.Graphics;


public class DRect extends DShape {
	
	public DRect(){
		super();
	}
	
	public void draw(Graphics g)
	{
		g.drawRect(10, 10, 20, 20);
		g.setColor(Color.CYAN);
		g.fillRect(10, 10, 20, 20);
	}

}
