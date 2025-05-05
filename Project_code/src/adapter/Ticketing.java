package adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modules.Ticket;

public interface Ticketing {
	
    public Ticket issueTicket();
    public boolean validateExit(Integer ticketID);

    public Set<Integer> getActiveTicketIDs();
}
