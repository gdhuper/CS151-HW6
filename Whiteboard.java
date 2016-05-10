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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Whiteboard extends JFrame{
	
	public JPanel controls = new JPanel();
	
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
		
		
		//Menu bar
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);
		menu.setVisible(true);
		setJMenuBar(menu);
		//********************************//
		
		controls.setLayout(new BorderLayout());
		
		
		JPanel setColor = new JPanel();
		//setColor.setLayout(new BoxLayout(addButtons, BoxLayout.X_AXIS));
		JButton setColr = new JButton("Set Color");
		setColor.add(setColr);
		
		
		
		
		//Adding components to controls Panel 
		createAddButtons();
		controls.add(setColor);
		selectFont();
		createActionButtons();
		createTable();
		
		
		
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		frame.add(controls);
		
		
		
		frame.add(c, BorderLayout.EAST);
		frame.pack();
		c.setSize(400, 400);
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

}
	public void createAddButtons()
	{
		//Add shapes button
				JPanel addButtons = new JPanel();
				addButtons.setLayout(new BoxLayout(addButtons, BoxLayout.X_AXIS));
				
				JLabel add = new JLabel("Add");
				add.setBackground(Color.RED);;
				
				JButton rect = new JButton("Rect");
				JButton oval = new JButton("Oval");
				JButton line = new JButton("Line");
				JButton text = new JButton("Text");
				
				
				//Add buttons
				addButtons.add(add);
				addButtons.add(rect);
				addButtons.add(oval);
				addButtons.add(line);
				addButtons.add(text);
				//Add buttons
				
				controls.add(addButtons);
		
	}
	
	public void createActionButtons()
	{
		JPanel actionButtons = new JPanel();
		actionButtons.setLayout(new FlowLayout());
		
		JButton moveToFront = new JButton("Move To Front");
		JButton moveToBack = new JButton("Move To Back");
		JButton removeShape = new JButton("Remove Shape");
		
		actionButtons.add(moveToFront);
		actionButtons.add(moveToBack);
		actionButtons.add(removeShape);
		
		controls.add(actionButtons);

	}
	
	public void selectFont()
	{
		//Edwardian script stuff
				JPanel script = new JPanel();
				//script.setLayout(new BoxLayout(addButtons, BoxLayout.X_AXIS));
				JTextField textField = new JTextField("WhiteBoard!");
				JButton scriptButton = new JButton("Edwardian Script");
				script.add(textField);
				script.add(scriptButton);
				controls.add(script);
	}
	
	public void createTable()
	{
		String[] col = {"X", "Y", "Width", "Height"};
		//DefaultTableModel model = new DefaultTableModel();
		//model.setColumnIdentifiers(col);
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 4; }
	          public String getColumnName(int index)
	          {
	        	  return col[index];
	          }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col)
	          { 
	        	  return ""; 
	        }
	      };
	     JTable table = new JTable(dataModel);
	      JScrollPane scrollpane = new JScrollPane(table);
	      controls.add(scrollpane);
	      
	      table.setFillsViewportHeight(true);

	}
	
	
}
