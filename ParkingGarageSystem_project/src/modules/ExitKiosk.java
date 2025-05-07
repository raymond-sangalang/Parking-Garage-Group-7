package modules;

import java.io.Serializable;

import util.FileIO;

public class ExitKiosk implements Serializable {
	
    private static final long serialVersionUID = -5762735825299829729L;
	private String id;
    private static int count = 0;
    private Gate gate;
    
    private ParkingGarage parkingGarage;                  // 1-to-1
    

    public ExitKiosk() {
    	this.setId("ExitKiosk" + String.valueOf(++count));
    	this.setGate(new Gate());
    }
    
    public ExitKiosk(Gate gate) {
    	this.setId("ExitKiosk" + String.valueOf(++count));
    	this.setGate(gate);
    }

   

	public boolean scanTicket(Ticket ticket) {
		
    	if (ticket != null && ticket.getParkingTicketStatus() == TicketStatus.ACTIVE) {
    		System.out.println("Ticket scanned!");
    		return true;
    	} else {
    		System.out.println("Invalid ticket!");
    		return false;    		
    	}
    }
	
	
	
	public double scanTicket_toExit(Ticket ticket) {
		
    	if (ticket != null && ticket.getParkingTicketStatus() == TicketStatus.ACTIVE) {
    		gate.openGate();
    		FileIO.log("Ticket Scanned!");
    		System.out.println("Ticket scanned!");
    		parkingGarage.exitParkingGarage();
    		gate.closeGate();
    		return ticket.calculateFee();
    	} else {
    		
    		FileIO.log("Invalid or used ticket! <   max clients credit options (=   >");
    		System.out.println("Invalid or used ticket! <   max clients credit options (=   >");
    		return -1;    		 
    	}
    }

	
	
	// Getters
	public String getId() {
		return id;
	}
	public Gate getGate() {
		return gate;
	}
	public ParkingGarage getParkingGarage() {
		return parkingGarage;
	}
	
	// Setters
	public void setId(String id) {
		this.id = id;
	}
	public void setGate(Gate gate) {
		this.gate = gate;
	}
	public void setParkingGarage(ParkingGarage parkingGarage) {
		this.parkingGarage = parkingGarage;
	}
	
	// Gate Operations
	public void openGate() {
		getGate().openGate();
	}
	public void closeGate() {
		getGate().closeGate();
	}
	
	
	
	public String toString() {
		return getId();
	}
}