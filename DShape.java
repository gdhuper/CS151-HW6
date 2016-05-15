import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;



public class DShape {
	
	private DShapeModel m;
	private Font font;
	private boolean drawn = false;
	
//	ArrayList<DShapeModel> shapeList = new ArrayList<DShapeModel>();
	
	
	public boolean isDrawn() {
		return drawn;
	}

	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}

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
		g.setColor(Color.GRAY);
	}
	
	public void setFont(Font f){
		font = f;
	}

	
	
	

}
