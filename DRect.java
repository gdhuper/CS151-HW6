import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class DRect extends DShape {
	
	public DRect(){
		super();
	}
	
	
	
	
	public void draw(Graphics g)
	{
		Random n = new Random();
		int x = n.nextInt(100);
		int y = n.nextInt(100);
		g.drawRect(x, y, 20, 20);
		g.setColor(Color.RED);
		g.fillRect(x, y, 20, 20);
	}

}
