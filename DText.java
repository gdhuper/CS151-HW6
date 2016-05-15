import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


public class DText extends DShape  {

	Font font;
	
	public DText()
	{
	super();
	}
	
	public void draw(Graphics g)
	{
		
		Random n = new Random();
		int x = n.nextInt(100);
		int y = n.nextInt(100);
		g.setFont(font);
	
		g.drawString("WhiteBoard!", x, y);
		
	}
	
	public void setFont(Font f)
	{
		font = f;
	}
	
	public Font getFont()
	{
		return font;
	}
}
