import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.Random;


public class DText extends DShape  {

	Font font;
	Random random = new Random();
	int x = random.nextInt(200);
	int y = random.nextInt(200);
	private String text; // text to draw
	public DText()
	{
	super();
	
	}
	
	
	public void draw(Graphics g)
	{
		
	
	//	g.setFont(font);
		Shape clip = g.getClip();
		Font f = getFont();
		g.setFont(f);
		g.drawString(getText(), x, y);
		
	}
	
	public void setFont(Font f)
	{
		font = f;
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String t)
	{
		text = t;
	}
}
