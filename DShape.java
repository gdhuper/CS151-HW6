import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;



public class DShape implements ModelListener{
	
	private DShapeModel m;
	private Font font;
	private String text = null;
	private boolean drawn = false;
	
//	ArrayList<DShapeModel> shapeList = new ArrayList<DShapeModel>();
	
	
	
	public DShape()
	{
		m = new DShapeModel();
		drawn = false;
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
		g.setColor(getColor());
	}
		
	public void setText(String c)
	{
		text = c;
	}
	
	public void setFont(Font f){
		font = f;
	}

	@Override
	public void modelChanged(DShapeModel model) {
		
		
	}

	
	
	

}
