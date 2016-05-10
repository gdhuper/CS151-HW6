import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;


public class Canvas extends JPanel{
	
	
	
	public Canvas()
	{
		createCanvas();
	}
	
	
	
	
	public void createCanvas()
	{
		
		//JPanel panel = new JPanel();
		
		setBackground(Color.WHITE);
		
		setPreferredSize(new Dimension(400, 400));
		setVisible(true);
		//setLayout(new BorderLayout());
		
				
		
		
	}
	
	
	

}
