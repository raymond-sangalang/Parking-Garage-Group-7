package modules;
import java.io.Serializable;

public class ExitKiosk implements Serializable {
    private String id;
    private static int count = 0;
    private Gate gate;
    
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Gate getGate() {
		return gate;
	}

	public void setGate(Gate gate) {
		this.gate = gate;
	}
}
