import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	Canvas c;
	Graphics g;
	
	
	/**
	 * Main method to launch the whiteboard
	 */
	public static void main(String[] args)
	{
		Whiteboard w = new Whiteboard();
	}
	
	/**
	 * Constructs an default whiteboard 
	 */
	public Whiteboard() 
		{
		
		showGUI();
		}
	/**
	 * Method to create the main GUI of the Whiteboard
	 */
	private void showGUI() {
	
		JFrame frame = new JFrame("Whiteboard");
		frame.setTitle("Whiteboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		 c = new Canvas(); //Creating the canvas in main frame
		
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		
		//***********Menu bar**********//
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);
		menu.setVisible(true);
		setJMenuBar(menu);
		//*****************************//
				
		
		
		
		
		//************************Adding components to controls Panel********************************// 
		controls.add(Box.createRigidArea(new Dimension(0,10))); //Add rigid areas between two components

		createAddButtons(); //method call to create  add buttons
		controls.add(Box.createRigidArea(new Dimension(0,30)));

		setColor();   //method call to create set color button in control panel
		controls.add(Box.createRigidArea(new Dimension(0,30)));

		selectFont(); //To create the set font button
		controls.add(Box.createRigidArea(new Dimension(0,30)));

		createActionButtons(); //To create the action buttons
		controls.add(Box.createRigidArea(new Dimension(0,50)));

		createTable();  //Creates a table to display statistics
		//*********************************************************************************************//
		
		
		
		// To align all the components to the left 
		for (Component comp : controls.getComponents()) { 
			 ((JComponent) comp).setAlignmentX(Box.LEFT_ALIGNMENT);
		}
		
		//Adding components to the main frame
		
		frame.add(controls, BorderLayout.WEST);
		frame.add(c);
		frame.pack();
		c.setSize(400, 400);
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

}
	/**
	 * Helper methods to create the add buttons in the main gui
	 */
	public void createAddButtons()
	{
				//Add shapes button
				
				JPanel addButtons = new JPanel();
				addButtons.setLayout(new BoxLayout(addButtons, BoxLayout.LINE_AXIS));
				addButtons.add(Box.createRigidArea(new Dimension(0, 5)));
				JLabel add = new JLabel("Add");
				add.setBackground(Color.RED);;
				
				JButton rect = new JButton("Rect");
				 rect.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DRect
					 public void actionPerformed(ActionEvent e) { 
						 c.setButton();
						 c.repaint();
						
						           
						 }

					        
					 });
				JButton oval = new JButton("Oval");
				JButton line = new JButton("Line");
				JButton text = new JButton("Text");
				
				
				//Add buttons
				addButtons.add(Box.createRigidArea(new Dimension(5,0)));

				addButtons.add(add);
				
				addButtons.add(Box.createRigidArea(new Dimension(5,0)));

				addButtons.add(rect);
				addButtons.add(Box.createRigidArea(new Dimension(5,0)));

				addButtons.add(oval);
				addButtons.add(Box.createRigidArea(new Dimension(5,0)));

				addButtons.add(line);
				addButtons.add(Box.createRigidArea(new Dimension(5,0)));

				addButtons.add(text);
				addButtons.add(Box.createRigidArea(new Dimension(5,0)));

				//Add buttons
				
				
				controls.add(addButtons);
				
		
	}
	/**
	 * Helper method to create set color button in main GUI
	 */
	public void setColor()
	{
		JPanel setColor = new JPanel();
		setColor.setLayout(new BoxLayout(setColor, BoxLayout.LINE_AXIS));
		JButton setColr = new JButton("Set Color");
		setColor.add(Box.createRigidArea(new Dimension(5,0)));

		setColor.add(setColr);
		controls.add(setColor);

		
	}
	
	/**
	 * Helper method to create font button on main gui
	 */
	public void selectFont()
	{
		//Edwardian script stuff
				JPanel script = new JPanel();
				script.setLayout(new BoxLayout(script, BoxLayout.LINE_AXIS));
				
				JTextField textField = new JTextField("WhiteBoard!");
				JButton scriptButton = new JButton("Edwardian Script");
				script.add(Box.createRigidArea(new Dimension(5,0)));

				script.add(textField);
				script.add(Box.createRigidArea(new Dimension(30,0)));

				script.add(scriptButton);
				controls.add(script);
	}
	
	
	/**
	 * Helper method to create action buttons in the GUI
	 */
	public void createActionButtons()
	{
		JPanel actionButtons = new JPanel();
		actionButtons.setLayout(new BoxLayout(actionButtons, BoxLayout.LINE_AXIS));
		
		JButton moveToFront = new JButton("Move To Front");
		JButton moveToBack = new JButton("Move To Back");
		JButton removeShape = new JButton("Remove Shape");
		
		actionButtons.add(Box.createRigidArea(new Dimension(5, 0)));

		actionButtons.add(moveToFront);
		actionButtons.add(Box.createRigidArea(new Dimension(5, 0)));

		actionButtons.add(moveToBack);
		actionButtons.add(Box.createRigidArea(new Dimension(5, 0)));

		actionButtons.add(removeShape);
		actionButtons.add(Box.createRigidArea(new Dimension(5, 0)));

		
		controls.add(actionButtons);

	}
	

	/**
	 * Helper method to create a table on control panel in main gui.
	 * The table is going to be used for displaying shapes' statistics. 
	 */
	public void createTable()
	{
		
		JPanel dataTable = new JPanel();
		String[] col = {"X", "Y", "Width", "Height"};
		//DefaultTableModel model = new DefaultTableModel();
		//model.setColumnIdentifiers(col); 
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 4; }
	          public String getColumnName(int index)
	          {
	        	  return col[index]; 
	          }
	          public int getRowCount() { return 30;}
	          public Object getValueAt(int row, int col)
	          { 
	        	  return ""; 
	        }
	      };
	     JTable table = new JTable(dataModel);
	      JScrollPane scrollpane = new JScrollPane(table);
	      dataTable.add(scrollpane);
	      
	      table.setFillsViewportHeight(true);
	      controls.add(scrollpane);
	}
	
	
}
