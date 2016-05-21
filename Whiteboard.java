import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
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
import java.util.Date;
import java.util.HashMap;
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
    private ClientHandler clientHandler; 
    private ServerAccepter serverAccepter; 
    private java.util.List<ObjectOutputStream> outputs =
            new ArrayList<ObjectOutputStream>();

	
	
	/**
	 * Main method to launch the whiteboard
	 */
	/**public static void main(String[] args)
	{
		Whiteboard w = new Whiteboard();
	}*/
	
	public static void main(String[] args) {
      Whiteboard a = new Whiteboard();
      Whiteboard b = new Whiteboard();
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
						 //c.setRect();
						 DRectModel m = new DRectModel();
						c.addShape(m);
						
						 //c.addToList(m);
						 c.repaint();

						 
				//	 c.print();
						 
						 	}

					        
					 });
				JButton oval = new JButton("Oval");
				 oval.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DOval
					 public void actionPerformed(ActionEvent e) { 
						 DOvalModel o = new DOvalModel();

						 c.addShape(o);

						// c.addToList(o);
						 c.repaint();
						
				//	 c.print();
						
						           
						 }

					        
					 });
				JButton line = new JButton("Line");
				 line.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DLine
					 public void actionPerformed(ActionEvent e) { 
						 DLineModel l = new DLineModel();
						 c.addShape(l);

						// c.setLine();
						 c.repaint();

				//		c.print();
						           
						 }

					        
					 });
				JButton text = new JButton("Text");
				text.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DRect
					 public void actionPerformed(ActionEvent e) { 
						 DTextModel t = new DTextModel();
						 t.setTextToDraw(getText());
						 c.setText(getText());
						 c.addShape(t);
						 

						 //c.setText();
						 c.repaint();
					

						 
				//		c.print();
						           
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
		setColr.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DRect
			 public void actionPerformed(ActionEvent e) { 
				 Color color = JColorChooser.showDialog(null, "Pick a Color", Color.WHITE);
			     if(color != null)
			     {
			    	c.setColor(color);
			     }
			 
							 }

						        
						 });
					
		
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
				
				JTextField textField = new JTextField("Enter text here...");
				script.add(Box.createRigidArea(new Dimension(5,0)));
				
				textField.addActionListener(new ActionListener() {   // Added an action listener to connect to canvas and then connect canvas to DRect
					 public void actionPerformed(ActionEvent e) { 
						 String text = textField.getText();
						 setText(text);
						    textField.selectAll();
									 }

								        
								 });


				  
			     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
			     String fonts[] = ge.getAvailableFontFamilyNames(); 
			     JComboBox fontType = new JComboBox(fonts); 
			     fontType.addActionListener(new ActionListener() { 
			            public void actionPerformed(ActionEvent e) { 
			            Font f = (Font) fontType.getSelectedItem();
			            c.setFont(f);
			            } 
			        }); 
				script.add(textField);
				script.add(fontType);
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
	 * Networking buttons
	 */
	public void createNetworkButton()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		JButton server = new JButton("Server Start");
		server.addActionListener(new ActionListener() {   // Added an action listener to connect to clear the canvas 
			 public void actionPerformed(ActionEvent e) { 
				 			doServer();
							 }

						        
						 });
		JButton client = new JButton("Client Start");
		client.addActionListener(new ActionListener() {   // Added an action listener to connect to clear the canvas 
			 public void actionPerformed(ActionEvent e) { 
				 			doClient();
							 }

						        
						 });
		panel.add(Box.createRigidArea(new Dimension(5, 0)));

		panel.add(server);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));

		panel.add(client);
		
		controls.add(panel);

	}
	
	//**************************Networking Methods*************************************//
	
	  // Client runs this to handle incoming messages
    // (our client only uses the inputstream of the connection)
    private class ClientHandler extends Thread {
         private String name;
         private int port;
         ClientHandler(String name, int port) {
             this.name = name;
             this.port = port;
         }
     // Connect to the server, loop getting messages
         public void run() {
             try {
                 // make connection to the server name/port
                 Socket toServer = new Socket(name, port);
                 // get input stream to read from server and wrap in object input stream
                 ObjectInputStream in = new ObjectInputStream(toServer.getInputStream());
                 System.out.println("client: connected!");
                 // we could do this if we wanted to write to server in addition
                 // to reading
                 // out = new ObjectOutputStream(toServer.getOutputStream());
                 while (true) {
                     // Get the xml string, decode to a Message object.
                     // Blocks in readObject(), waiting for server to send something.
                     String xmlString = (String) in.readObject();
                     @SuppressWarnings("resource")
					XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xmlString.getBytes()));
                     Message message = (Message) decoder.readObject();

                     // Process the message 
                     invokeToGUI(message);
                 }
             }
             catch (Exception ex) { // IOException and ClassNotFoundException
                ex.printStackTrace();
             }
             // Could null out client ptr.
             // Note that exception breaks out of the while loop,
             // thus ending the thread.
        }
    } 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // Server thread accepts incoming client connections
    class ServerAccepter extends Thread {
        private int port;
        ServerAccepter(int port) {
            this.port = port;
        }
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                    Socket toClient = null;
                    // this blocks, waiting for a Socket to the client
                    toClient = serverSocket.accept();
                    System.out.println("server: got client");
                    // Get an output stream to the client, and add it to
                    // the list of outputs
                    // (our server only uses the output stream of the connection)
                    ObjectOutputStream o = new ObjectOutputStream(toClient.getOutputStream());
                    if(!outputs.contains(o))
                    {
                    	Thread thread = new Thread(new Runnable(){

							@Override
							public void run() {
								DShapeModel temp = new DShapeModel();
								for(DShape s: c.getList())
								{
									if(s instanceof DRect)
									{
										temp = new DRectModel();
									}
									if(s instanceof DOval)
									{
										temp = new DOvalModel();
									}
									if(s instanceof DLine)
									{
										temp = new DLineModel();
									}
									if(s instanceof DText)
									{
										temp = new DTextModel();
									}
									
                    			Message message = new Message(temp);
                    			OutputStream oS = new ByteArrayOutputStream();
                    			XMLEncoder encoder = new XMLEncoder(oS);
                    			 encoder.writeObject(message); 
                    		        encoder.close(); 
                    		       String tempMesg =  oS.toString(); 
                    		       try {
									o.writeObject(tempMesg);
									o.flush();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
    

    
    //***********************************************************************//
 // Struct object just used for communication -- sent on the object stream.
    // Declared "static", so does not contain a pointer to the outer object.
    // Bean style, set up for xml encode/decode.
       public static class Message {
           public String text;
           public DShapeModel model;

           public Message(DShapeModel m) {
             this.model = m;
           }

           public DShapeModel getModel() {
               return model;
           }
           public void setModel(DShapeModel m) {
               this.model  = m;
           }
           
         
           
       }

	
	
//**********************************************************************************//
	
	  // Starts the sever accepter to catch incoming client connections.
    // Wired to Server button.
    public void doServer() {
       // status.setText("Start server");
        String result = JOptionPane.showInputDialog("Run server on port", "912");
        if (result!=null) {
            System.out.println("server: start");
            serverAccepter = new ServerAccepter(Integer.parseInt(result.trim()));
            serverAccepter.start();
        }
    }
    // Runs a client handler to connect to a server.
    // Wired to Client button.
    public void doClient() {
       // status.setText("Start client");
        String result = JOptionPane.showInputDialog("Connect to host:port", "127.0.0.1:912");
        if (result!=null) {
            String[] parts = result.split(":");
            System.out.println("client: start");
            clientHandler = new ClientHandler(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            clientHandler.start();
        }
    }
    
    
    

    // Adds an object stream to the list of outputs
    // (this and sendToOutputs() are synchronzied to avoid conflicts)
    public synchronized void addOutput(ObjectOutputStream out) {
        outputs.add(out);
    }
    
    
    
 // Given a message, puts that message in the local GUI.
    // Can be called by any thread.
    public void invokeToGUI(Message message) {
        final Message temp = message;
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
              c.addShape(message.getModel());
              //c.repaint();
            }
        });
    }

    // Initiate message send -- send both local annd remote (must be on swing thread)
    // Wired to text field.
    public void doSend(DShapeModel m) {
        Message message = new Message(m);
      //  message.setText(field.getText());
      // message.setModel(m);
       // sendLocal(message);
        sendRemote(message);
       // field.setText("");
    }
    
    
    // Appends a message to the local GUI (must be on swing thread)
    public void sendLocal(Message message) {
     //   textArea.setText(textArea.getText() + message.getText() + "\n" + message.getDate() + "\n\n");
    }
    
    
    
    // Sends a message to all of the outgoing streams.
    // Writing rarely blocks, so doing this on the swing thread is ok,
    // although could fork off a worker to do it.
    public synchronized void sendRemote(Message message) {
      //  status.setText("Server send");
        System.out.println("server: send " + message);

        // Convert the message object into an xml string.
        OutputStream memStream = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(memStream);
        encoder.writeObject(message);
        encoder.close();
        String xmlString = memStream.toString();
        // Now write that xml string to all the clients.
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
                // Cute use of iterator and exceptions --
                // drop that socket from list if have probs with it
            }
        }
    }
    
    

    
    //**************************************************************************************//
	
	//**************************************************************************************//
    
	/**
	 * To clean up the canvas
	 */
	public void createClearButton()
	{
		JPanel clearPanel = new JPanel();
		clearPanel.setLayout(new BoxLayout(clearPanel, BoxLayout.LINE_AXIS));

		JButton clear = new JButton("Clear Canvas");
		clear.addActionListener(new ActionListener() {   // Added an action listener to connect to clear the canvas 
			 public void actionPerformed(ActionEvent e) { 
				 			c.clearCanvas();
							 }

						        
						 });
		clearPanel.add(Box.createRigidArea(new Dimension(5, 0)));

		clearPanel.add(clear);
		
		controls.add(clearPanel);

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
	
	//**********************Helper methods***************************//
	
	public String getText()
	{
		return textToPrint;
	}
	
	public void setText(String s)
	{
		textToPrint = s;
	}
	
}
