import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;



public class DShape {
	
	private DShapeModel m;
	
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
	
	
	
	

}
