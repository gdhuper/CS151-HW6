

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class Whiteboard extends JFrame{
	
	public JPanel controls = new JPanel();
	Canvas c;
	Graphics g;
	private String textToPrint;
	
  
    private static int IDTemp = 0;
    private int mode;
   
    private ClientHandler clientHandler; 
    private ServerAccepter serverAccepter; 
    private java.util.List<ObjectOutputStream> outputs =
            new ArrayList<ObjectOutputStream>();
    private ArrayList<JComponent> disableComponentIfClient = new ArrayList<JComponent>();
    JButton server, client;
    


   
	
	
	/**
	 * Main method to launch the Whiteboard 
	 * Launches the Whiteboard GUI 
	 */
	public static void main(String[] args) {
	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {
		}
		for (int i = 0; i < 2; i++) {
			new Whiteboard();
		}

	}
	
	/**
	 * Constructs the default Whiteboard constructor 
	 * Sets up the main GUI and draws it.  
	 */
	public Whiteboard() 
		{
		
		showGUI();
		
		}
	
	/**
	 * Method to set up the main GUI
	 * Creates all the GUI  elements
	 */
	private void showGUI() {
	
		JFrame frame = new JFrame("Whiteboard");
		frame.setTitle("Whiteboard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		c = new Canvas(this); //Creating the canvas in main frame
		frame.add(c, BorderLayout.CENTER);
		
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
		controls.add(Box.createRigidArea(new Dimension(0,20)));

		createActionButtons(); //To create the action buttons
		controls.add(Box.createRigidArea(new Dimension(0,30)));
		
		createNetworkButton();
		controls.add(Box.createRigidArea(new Dimension(0,20)));

		createClearButton();
		controls.add(Box.createRigidArea(new Dimension(0,20)));


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
	 * Helper methods to create the add buttons in the main GUI
	 */
	public void createAddButtons()
	{
		// Add shapes button

		JPanel addButtons = new JPanel();
		addButtons.setLayout(new BoxLayout(addButtons, BoxLayout.LINE_AXIS));
		addButtons.add(Box.createRigidArea(new Dimension(0, 5)));
		JLabel add = new JLabel("Add");
		add.setBackground(Color.RED);

		JButton rect = new JButton("Rect");
		rect.addActionListener(new ActionListener() { // Added an action listener to connect to canvas and then connect canvas to DRect
			public void actionPerformed(ActionEvent e) {
				DRectModel m = new DRectModel();
				m.setID(getIDNumber());
				c.addShape(m);
				c.repaint();
				c.print(); // for debugging 

			}

		});
				JButton oval = new JButton("Oval");
				 oval.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DOval
					 public void actionPerformed(ActionEvent e) { 
						 DOvalModel o = new DOvalModel();
						 o.setID(getIDNumber());
						 c.addShape(o);
						 c.repaint();
						
					 c.print(); //For debugging 
						
						           
						 }

					        
					 });
				JButton line = new JButton("Line");
				 line.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DLine
					 public void actionPerformed(ActionEvent e) { 
						 DLineModel l = new DLineModel();
						 l.setID(getIDNumber());
						 c.addShape(l);
						 c.repaint();

						 c.print(); //for debugging
						           
						 }

					        
					 });
				JButton text = new JButton("Text");
				text.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DText
					 public void actionPerformed(ActionEvent e) { 
						 DTextModel t = new DTextModel();
						 t.setID(getIDNumber());
						 t.setTextToDraw(getText());
						 c.setText(getText());
						 c.addShape(t);
						 c.repaint();
						 
						 c.print(); //for debugging
						           
					 	}      
					 });
				
				
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

				//add buttons to disabled list to later use it to disable  buttons if it runs on client mode
				disableComponentIfClient.add(rect);
				disableComponentIfClient.add(oval);
				disableComponentIfClient.add(line);
				disableComponentIfClient.add(text);
				
				controls.add(addButtons); //Adds button to main controls panel 
	
	}
	
	/**
	 * Helper method to create set color button in main GUI
	 */
	public void setColor()
	{
		JPanel setColor = new JPanel();
		setColor.setLayout(new BoxLayout(setColor, BoxLayout.LINE_AXIS));
		JButton setColr = new JButton("Set Color");
		setColr.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Pick a Color",
						Color.WHITE);
				if (color != null) {
					c.setColor(color); //Fix color setting
				}

			}

		});

		setColor.add(Box.createRigidArea(new Dimension(5, 0)));
		setColor.add(setColr);
		
		disableComponentIfClient.add(setColr);
		controls.add(setColor);

		
	}
	
	/**
	 * Helper method to create text fields to input text and font drop down box on Controls panel
	 */
	public void selectFont()
	{

		JPanel script = new JPanel();
		script.setLayout(new BoxLayout(script, BoxLayout.LINE_AXIS));

		JTextField textField = new JTextField("Enter text here...");
		script.add(Box.createRigidArea(new Dimension(5, 0)));
		textField.getDocument().addDocumentListener(new DocumentListener() // Fix Listening problem
        {

            public void changedUpdate(DocumentEvent arg0) 
            {

            }
            public void insertUpdate(DocumentEvent arg0) 
            {
            	String text = textField.getText(); //Wont work when updated text
				setText(text);
				
            }

            public void removeUpdate(DocumentEvent arg0) 
            {
            	String text = textField.getText();
				setText(text);
				
            }
        });


		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String fonts[] = ge.getAvailableFontFamilyNames();
		JComboBox fontType = new JComboBox(fonts);
		fontType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Font f = (Font) fontType.getSelectedItem();
				c.setFont(f);	// Check how the font change should be handled 
			}
		});
		script.add(textField);
		script.add(fontType);
		controls.add(script);
		disableComponentIfClient.add(fontType);
		disableComponentIfClient.add(textField);

	}
	
	/**
	 * Helper method to create action buttons in the GUI
	 * Buttons to modify shapes on the canvas 
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

		
		// Adding buttons to disabled list to disable them if the canvas runs on client mode
		disableComponentIfClient.add(moveToFront);
		disableComponentIfClient.add(moveToBack);
		disableComponentIfClient.add(removeShape);
		
		controls.add(actionButtons);
	}

	/**
	 * Sets up the networking buttons to handle network and client communication 
	 */
	public void createNetworkButton()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		server = new JButton("Server Start");
		server.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doServer();  //Calls the doServer helper method that activates the Server mode
			}

		});
		client = new JButton("Client Start");
		client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doClient(); //Activates the client mode
			}

		});
		panel.add(Box.createRigidArea(new Dimension(5, 0)));

		panel.add(server);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));

		panel.add(client);

		controls.add(panel);

	}
	
	//**************************Networking Methods*************************************//
	
	  
	/**
	 * Private inner class to read the input messages from the Server 
	 * Referenced from Sample Code from class lectures 
	 */
    private class ClientHandler extends Thread {
         private String name;
         private int port;
         ClientHandler(String name, int port) {
             this.name = name;
             this.port = port;
         }
         public void run() {
             try {
                 // make connection to the server name/port
                 Socket toServer = new Socket(name, port);
                 // get input stream to read from server and wrap in object input stream
                 ObjectInputStream in = new ObjectInputStream(toServer.getInputStream());
                 System.out.println("client: connected!");
                 while (true) {
                     // Get the xml string, decode to a Message object.
                     // Blocks in readObject(), waiting for server to send something.
                     String xmlString = (String) in.readObject();
                     @SuppressWarnings("resource")
					XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xmlString.getBytes()));
                     Message message = (Message) decoder.readObject();

                     // Reads the message and update changes to Clients GUI
                     invokeToGUI(message);
                 }
             }
             catch (Exception ex) { 
                ex.printStackTrace();
             }
        
        }
    } 

    
    
    /**
     * Private inner class for Server that sends data (messages) to client
     * Referenced from Sample code from class lectures 
     */
    class ServerAccepter extends Thread {
        private int port;
        ServerAccepter(int port) {
            this.port = port;
        }
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                   
                    // this blocks, waiting for a Socket to the client
                 Socket toClient = serverSocket.accept();
                    System.out.println("server: got client");
                    // Get an output stream to the client, and add it to
                    // the list of outputs
                   final  ObjectOutputStream o = new ObjectOutputStream(toClient.getOutputStream());
                    if(!outputs.contains(o))
                    {
                    	Thread thread = new Thread(new Runnable(){

							@Override
							public void run() {
								DShapeModel temp = null;
								for(DShape s: c.getList())
								try {
									temp = getModel(s);
									//Encodes the message object into XML format 
									Message message = new Message(Message.add, temp);
									OutputStream oS = new ByteArrayOutputStream();
									XMLEncoder encoder = new XMLEncoder(oS);
									encoder.writeObject(message); 
                    		        encoder.close(); 
                    		        String tempMesg =  (String) oS.toString(); 
                    		       
									o.writeObject(tempMesg);
									o.flush();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							}
                    		
                    	});
                    	thread.start();
                    }
                    addOutput(o);
                }
            } catch (IOException ex) {
                ex.printStackTrace(); 
            }
        }
    }
    
    
    
	//***********************Helper getters and Setters methods for networking part*********************//
    
    /**
     * Assigns an ID number to a shape object
     * @return the id to be assigned to a shape object 
     */
	public static int getIDNumber() {
		return IDTemp++;

	}
	
	/**
	 * To check if the current mode is client
	 * @return true if the current mode is client mode else return false
	 */
	public boolean isClient() {
		boolean temp = false;

		if (this.mode != 2) {
			temp = false;
		} else if (this.mode == 2) {
			temp = true;
		}

		return temp;
	}

	/**
	 * To check if the current mode is Server mode
	 * @return true if the current mode is Server mode else return false 
	 */
	public boolean isServer() {
		boolean temp = false;

		if (this.mode == 1) {
			temp = true;
		} else if (this.mode != 1) {
			temp = false;
		}

		return temp;
	}
	
	/**
	 * Getter method to return the current mode
	 * @return the current mode 
	 */
	public int getMode() {
		return this.mode;
	}
	
	/**
	 * Setter method to set the current mode to a new value (mode)
	 * @param newMode the new mode to be set as current mode 
	 */
	public void setMode(int newMode) {
		this.mode = newMode;
	}
	
//*****************************************************************************************//
	    
    
    /**
     * Helper method to get the model type from a DShape object
     * @param s the Dhsape object to determine the DShapeMOdel object for
     * @return the DShapeModel object 
     */
    public DShapeModel getModel(DShape s)
    {
		DShapeModel temp = null;

		if (s instanceof DRect) {
			temp = new DRectModel();
		}
		if (s instanceof DOval) {
			temp = new DOvalModel();
		}
		if (s instanceof DLine) {
			temp = new DLineModel();
		}
		if (s instanceof DText) {
			temp = new DTextModel();
		}
		return temp;
	}
    
    //******************************************************************************************//
    
    	/**
    	 * Helper inner class to create message objects in terms of model type and command type
    	 */
       	public static class Message {
        public static final String add = "add";
		public static final String remove = "remove";
        public static final String front = "front";
        public static final String back = "back";
        public static final String change = "change";

           public String command;
           public DShapeModel model;
           
           /**
            * Default constructor to create a message object 
            */
           public Message() { 
               command = null; 
               model = null; 
           }

           public Message(String c, DShapeModel m) { 
           this.model = m;
           this.command = c;
           }
           
           /**
            * Returns the command
            * @return the command
            */
           public String getCommand()
           {
        	   return this.command;

           }
           
           /**
            * Sets the command to new value
            * @param c new command to be set
            */
           public void setCommand(String c)
           {
        	   this.command = c;
           }
           
           /**
            * Returns the DShapeModel object used 
            * @return DShapeModel object 
            */
           public DShapeModel getModel() {
               return model;
           }
           
           /**
            * Sets the model type to new value
            * @param m the new value to set model type to 
            */
           public void setModel(DShapeModel m) {
               this.model  = m;
           }
           
         
           
       }

	
	
//**************************Helper methods for networking  buttons*************************************//
	
       
	 /**
	  * Method to handle server when server button is clicked
	  * Hosts the data on a particular port
	  */
    public void doServer() { 
        String result = JOptionPane.showInputDialog("Run server on port", "9128");
        if (result!=null) {
            System.out.println("server: start");
            client.setEnabled(false); 
            server.setEnabled(false);
            this.mode = 1;
            serverAccepter = new ServerAccepter(Integer.parseInt(result.trim()));
            serverAccepter.start();
        }
    }
   
    /**
     * Runs client handles to connect to server
     * Listens from a hosting post specified
     */
    public void doClient() {
       
        String result = JOptionPane.showInputDialog("Connect to host:port", "127.0.0.1:9128");
        if (result!=null) {
            String[] parts = result.split(":");
            System.out.println("client: start");
            client.setEnabled(false); 
            server.setEnabled(false);
            //Disables the controls of client
            for(JComponent comp : disableComponentIfClient) 
       		{
               comp.setEnabled(false); 
       		}
            this.mode = 2;
            clientHandler = new ClientHandler(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            clientHandler.start();
        }
    }
    
    
    /**
     * Adds an object to the outputs arraylist to send to client when needed 
     * @param out the object to be added to the list 
     */
    public synchronized void addOutput(ObjectOutputStream out) { //check
        outputs.add(out);
    }
    
    /**
     * Helper method to manipulate a message in client
     * @param message the message to be read and executed 
     */
    public void invokeToGUI(Message message) { //Add other functionalities 
        final Message temp = message;
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                DShape shape = c.getShapeWithID(temp.getModel().getID()); 
           
                String command = message.getCommand();
                if(command.equals("add"))
                {
                        if(shape == null) 
                        {
                            c.addShape(temp.getModel()); 
                            c.repaint();
                         
                        }
                }
                   // case Message.remove:  
                    //    if(shape != null) 
                      //      canvas.markForRemoval(shape); 
                       // break; 
                    //case Message.back:  
                      //  if(shape != null) 
                       //     canvas.moveToBack(shape); 
                       // break; 
                   // case Message.front:  
                     //   if(shape != null) 
                       //     canvas.moveToFront(shape); 
                        //break; 
                    //case Message.change: 
                  //      if(shape != null) 
                          // shape.getModel().mimic(message.getModel()); 
                      //  updateTableSelection(shape); 
                       // break; 
                   
                } 
            
        }); 
    }
 
    /**
     * Sends the message from server to client
     * @param command the command to follow
     * @param m the model object to be drawn
     */
    public void doSend(String command, DShapeModel m) {
        Message message = new Message(command,  m);
        sendRemote(message);
       
    }
   
    /**
     * Sends message to all outgoing streams 
     * @param message the message to be sent to outgoing streams
     */
    public synchronized void sendRemote(Message message) {
        System.out.println("server: send " + message);
        
        // Convert the message object into an xml string.
        OutputStream memStream = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(memStream);
        encoder.writeObject(message);
        encoder.close();
        String xmlString = memStream.toString();
        // writing xml string to all the clients.
        Iterator<ObjectOutputStream> it = outputs.iterator();
        while (it.hasNext()) {
            ObjectOutputStream out = it.next();
            try {
                out.writeObject(xmlString);
                out.flush();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                it.remove();
            
            }
        }
    }
    
    

    
    //**************************************************************************************//
	
	//**************************************************************************************//
    
	/**
	 * Helper method to create button to clean up the canvas 
	 */
	public void createClearButton()
	{
		JPanel clearPanel = new JPanel();
		clearPanel.setLayout(new BoxLayout(clearPanel, BoxLayout.LINE_AXIS));

		JButton clear = new JButton("Clear Canvas");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.clearCanvas();
			}

		});
		clearPanel.add(Box.createRigidArea(new Dimension(5, 0)));

		clearPanel.add(clear);
		disableComponentIfClient.add(clear);
		controls.add(clearPanel);

	}
	

	/**
	 * Helper method to create a table on control panel in main GUI.
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
	
	//**********************Other Helper methods***************************//
	
	public String getText()
	{
		return textToPrint;
	}
	
	public void setText(String s)
	{
		textToPrint = s;
	}
	
}
