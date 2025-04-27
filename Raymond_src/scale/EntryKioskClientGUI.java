package scale;

import modules.Ticket;
import javax.swing.*;
import java.io.*;
import java.net.*;



public class EntryKioskClientGUI {

    // GUI variables
    private JFrame frame;
    private JTextArea outputArea;

    private Ticket ticket;

    // Client variables
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public EntryKioskClientGUI() {

        this.frame = new JFrame("Entry Kiosk");


        JButton entryButton = new JButton("Print Ticket");
        this.outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        entryButton.addActionListener(e -> requestEntry());

        JPanel panel = new JPanel();
        panel.add(entryButton);
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    private void requestEntry() {
        // event to enter parking garage and to obtain a new ticket

        try {
        	
        	this.socket = new Socket("localhost", 12345);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            
            out.writeObject("ENTRY");
            this.ticket = (Ticket) in.readObject();
            outputArea.setText("\tTicket Issued:\nTicket ID: " + ticket.getTicketId() + "\nEntry Time: " + ticket.getEntryTime());
        } 
        catch (Exception e) {
            // Display a connection issue
            outputArea.setText("ERROR: failed to connect to the server.");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new EntryKioskClientGUI();
    }
    
    
}
