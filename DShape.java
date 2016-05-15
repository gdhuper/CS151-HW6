import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;



public class DShape {
	
	private DShapeModel m;
	private Font font;
	
//	ArrayList<DShapeModel> shapeList = new ArrayList<DShapeModel>();
	
	
	public DShape()
	{
		m = new DShapeModel();
	}
	
	public Color getColor()
	{
		return m.getColor();
	}
	
	public void setColor(Color c)
	{
		m.setColor(c);
	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(Color.GRAY);
	}
	
	public void setFont(Font f){
		font = f;
	}

	
	
	

}
