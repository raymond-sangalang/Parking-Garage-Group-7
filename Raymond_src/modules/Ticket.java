package modules;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
    private int ticketId;
    private LocalDateTime entryTime;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.entryTime = LocalDateTime.now();
    }

    public int getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
