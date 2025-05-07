package modules;

import java.time.LocalDateTime;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class SystemLog {
	
    private String logID;
    private String eventDetails;
    private LocalDateTime timestamp;
    private String eventType; // INFO, ERROR, WARNING, SECURITY
    private String userID;
    private String hardwareID;

    private static List<SystemLog> logList = new ArrayList<>();
    private static int count = 0;

    // Constructor
    public SystemLog(String eventDetails, String eventType, String userID, String hardwareID) {
        this.logID = "LOG" + (++count);
        this.eventDetails = eventDetails;
        this.timestamp = LocalDateTime.now();
        this.eventType = eventType;
        this.userID = userID;
        this.hardwareID = hardwareID;
    }

    // Overloaded log creation methods
    public static void recordEvent(String eventDetails, String eventType) {
        SystemLog log = new SystemLog(eventDetails, eventType, null, null);
        logList.add(log);
        System.out.println("Log recorded: " + log.logID + " – " + log.eventDetails);
    }

    

    public static void recordEvent(String eventDetails, String eventType, String userID, String hardwareID) {
        SystemLog log = new SystemLog(eventDetails, eventType, userID, hardwareID);
        logList.add(log);
        System.out.println("Log recorded: " + log.logID + " – " + log.eventDetails);
    }

    
    // Get logs by type
    public static List<SystemLog> getLogsByType(String eventType) {
        List<SystemLog> filtered = new ArrayList<>();
        for (SystemLog log : logList) 
            if (log.eventType.equalsIgnoreCase(eventType)) 
                filtered.add(log);

        return filtered;
    }

    // Get logs by date range
    public static List<SystemLog> getLogsByDateRange(LocalDateTime from, LocalDateTime to) {
        List<SystemLog> filtered = new ArrayList<>();
        for (SystemLog log : logList) 
            if (log.timestamp.isAfter(from) && log.timestamp.isBefore(to)) 
                filtered.add(log); 
        
        return filtered;
    }


    // Getters
    public String getLogID() {
        return logID;
    }
    public String getEventDetails() {
        return eventDetails;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getEventType() {
        return eventType;
    }
    public String getUserID() {
        return userID;
    }
    public String getHardwareID() {
        return hardwareID;
    }
    public static List<SystemLog> getAllLogs() {
        return logList;
    }
}
