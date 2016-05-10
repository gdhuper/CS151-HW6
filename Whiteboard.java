import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class Whiteboard extends JFrame{
	
	
	
	public static void main(String[] args)
	{
		Whiteboard w = new Whiteboard();
	}
	
	
	public Whiteboard() 
		{
		
		showGUI();
		}
	
	private void showGUI() {
	
		JFrame frame = new JFrame("Whiteboard");
		frame.setTitle("Whiteboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		Canvas c = new Canvas();
		
		
		
		//The controls Jpanel
		JPanel controls = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		
		//Add shapes button
		JPanel addButtons = new JPanel();
		addButtons.setLayout(new BoxLayout(addButtons, BoxLayout.X_AXIS));
		
		JLabel add = new JLabel("Add");
		add.setBackground(Color.RED);;
		
		JButton rect = new JButton("Rect");
		JButton oval = new JButton("Oval");
		JButton line = new JButton("Line");
		JButton text = new JButton("Text");
		
		JPanel setColor = new JPanel();
		//setColor.setLayout(new BoxLayout(addButtons, BoxLayout.X_AXIS));
		JButton setColr = new JButton("Set Color");
		setColor.add(setColr);
		
		//Edwardian script stuff
		JPanel script = new JPanel();
		//script.setLayout(new BoxLayout(addButtons, BoxLayout.X_AXIS));
		JTextField textField = new JTextField("WhiteBoard!");
		JButton scriptButton = new JButton("Edwardian Script");
		script.add(textField);
		script.add(scriptButton);
		
		JPanel actionButtons = new JPanel();
		actionButtons.setLayout(new FlowLayout());
		
		JButton moveToFront = new JButton("Move To Front");
		JButton moveToBack = new JButton("Move To Back");
		JButton removeShape = new JButton("Remove Shape");
		
		actionButtons.add(moveToFront);
		actionButtons.add(moveToBack);
		actionButtons.add(removeShape);

		
		
		//Add buttons
		addButtons.add(add);
		addButtons.add(rect);
		addButtons.add(oval);
		addButtons.add(line);
		addButtons.add(text);
		//Add buttons

		
		
		controls.add(addButtons);
		controls.add(setColor);
		controls.add(script);
		controls.add(actionButtons);
		
		
		
		
		
		frame.add(controls);
		
		
		

		
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);
		menu.setVisible(true);
		setJMenuBar(menu);
		frame.add(c, BorderLayout.EAST);
		frame.pack();
		c.setSize(400, 400);
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

}
}
