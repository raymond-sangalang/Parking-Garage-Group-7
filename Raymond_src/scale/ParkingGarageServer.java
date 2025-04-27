package scale;

import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import exception.ParkingExceptions;
import exception.FixScale;
import modules.Ticket;
import util.FileIO;

public class ParkingGarageServer {

    private static final int PORT = 12345;

    
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        FileIO.log("Parking Garage Server started on port: " + PORT);

        while (true) {

        	Socket client = serverSocket.accept();
            new Thread(new ClientHandler(client)).start();
        }
    }

}


class ClientHandler implements Runnable {
	
	// 
	private static final String LOG_FILE = "garage_log.txt";
	private static int nextTicketId = 1;
	private static Map<Integer, Ticket> tickets = new HashMap<>();

	// Server variables
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    
    public ClientHandler(Socket clientSocket) throws IOException{

        this.clientSocket = clientSocket;
        this.in = new ObjectInputStream(clientSocket.getInputStream());
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
    }


    @Override
    public void run() {

        try {

            String request = (String) in.readObject();

            if (request.equals("ENTRY")) {
                // In response to a request to enter from entrykiosk
                // create a new ticket for client and save all tickets in a hashmap

                Ticket ticket = new Ticket(nextTicketId++);
                tickets.put(ticket.getTicketId(), ticket);

                out.writeObject(ticket);
                FileIO.log("ENTRY: Ticket " + ticket.getTicketId() + " issued at " + ticket.getEntryTime());
            } 
            else if (request.equals("EXIT")) {
                // In response to a request to exit from exitKiosk
                //  - remove clients ticket from hashmap, then use 
                //  - ticket to calculate total fee for the clients payment

                int ticketId = in.readInt();
                Ticket ticket = tickets.remove(ticketId);
                
                if (!FixScale.isValidTicket(ticket)) {
                	// Ticket is invalid
                	out.writeObject("Invalid or already used ticket.");
                	throw new ParkingExceptions(3);
                }

                // Ticket is validated
                long minutes = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toMinutes();
                double fee = Math.ceil(minutes / 30.0) * 2.0;
                
                String result = "Total time: " + minutes + " mins. Fee: $" + fee;
                out.writeObject(result);
                FileIO.log("EXIT: Ticket " + ticketId + " exited. " + result);

            }
        } 
        catch (ParkingExceptions e) {
        	System.out.println(e.writemyproblem());
        }
        catch (Exception e) {
            e.printStackTrace();
            FileIO.log("Error: " + e.getMessage());
        }

    }
    


}