import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


public class DText extends DShape  {

	Font font;
	private String text; // text to draw
	public DText()
	{
	super();
	}
	
	
	public void draw(Graphics g)
	{
		
	
	//	g.setFont(font);
	
		g.drawString(getText(), 10, 10);
		
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
