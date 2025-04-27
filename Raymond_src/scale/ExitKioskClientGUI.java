package scale;

import javax.swing.*;
import java.io.*;
import java.net.*;



public class ExitKioskClientGUI {

	// GUI variables
    private JFrame frame;
    
    private JLabel label;
    private JButton exitButton;
    private JTextField ticketField;
    private JTextArea outputArea;
    private JPanel panel;
    
    // Client Variables
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ExitKioskClientGUI() {
    	
        this.frame = new JFrame("Exit Kiosk");
        
        
        this.label = new JLabel("Enter Ticket ID:");
        this.ticketField = new JTextField(10);
        this.exitButton = new JButton("Request Exit");
        this.outputArea = new JTextArea(5, 30);
        
        outputArea.setEditable(false);
        exitButton.addActionListener(e -> requestExit());

        this.panel = new JPanel();
        panel.add(label);
        panel.add(ticketField);
        panel.add(exitButton);
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void requestExit() {
    	
        try {
        	
        	socket = new Socket("localhost", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            		
            out.writeObject("EXIT");
            out.writeInt(Integer.parseInt(ticketField.getText()));
            out.flush();

            String response = (String) in.readObject();
            outputArea.setText(response);

        } catch (Exception e) {
            outputArea.setText("Error connecting to server or invalid input.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new ExitKioskClientGUI();
    }
    
}
