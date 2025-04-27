package modules;

import java.util.ArrayList;
import exception.*;
import java.io.Serializable;

public class ParkingGarage implements Serializable{
	
	private Address address;
	private int numLevels;
	private ArrayList<ParkingLevel> parkingLevels;
	private EntranceDisplayBoard entranceBoard;   
	
	private EntryKiosk entryKiosk;    // MANY CLIENTS FOR BOTH KIOSKS
	private ExitKiosk exitKiosk;      // - SHARE CAPACITY RESOURCE
	
	
	public ParkingGarage(int numLevels, int numSpacesPerLevel) throws ParkingExceptions {
		// how should we get parking space count
		// maybe if differs per floor 
		//           create ParkingGarage then addParkingLevels
		
		this.setNumLevels(numLevels);
		
		this.parkingLevels = new ArrayList <ParkingLevel>();
		for (int index = 1; index < numLevels+1; index++)
			parkingLevels.add(new ParkingLevel(index, numSpacesPerLevel));
			
		this.entranceBoard = new EntranceDisplayBoard();
		this.entryKiosk = new EntryKiosk();
		this.exitKiosk = new ExitKiosk();
	}
	

	// Getters
	public Address getAddress() {
		return address;
	}
	public int getNumLevels() {
		return numLevels;
	}
	public ArrayList<ParkingLevel> getParkingLevels() {
		return parkingLevels;
	}
	public EntranceDisplayBoard getEntranceBoard() {
		return entranceBoard;
	}
	public EntryKiosk getEntryKiosk() {
		return entryKiosk;
	}
	public ExitKiosk getExitKiosk() {
		return exitKiosk;
	}
	
	
	// Setters
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setNumLevels(int numLevels) throws ParkingExceptions {
		if (!FixModel.isValidInteger(numLevels))
			throw new ParkingExceptions(2, "Invalid Input integer. Must be greater than or equal to zero.");
		this.numLevels = numLevels;
	}
	public void setParkingLevels(ArrayList<ParkingLevel> parkingLevels) {
		this.parkingLevels = parkingLevels;
	}
	public void setEntranceBoard(EntranceDisplayBoard entranceBoard) {
		this.entranceBoard = entranceBoard;
	}
	public void setEntryKiosk(EntryKiosk entryKiosk) {
		this.entryKiosk = entryKiosk;
	}
	public void setExitKiosk(ExitKiosk exitKiosk) {
		this.exitKiosk = exitKiosk;
	}
	
	
	
	public void addParkingLevel(int numSpacesPerLevel) {
		// update count of levels variable
		// 
		int newLevelNum = getNumLevels() + 1;
		try {
			//
			setNumLevels(newLevelNum);
			parkingLevels.add(new ParkingLevel(newLevelNum, numSpacesPerLevel));
		} catch (ParkingExceptions e) {
			System.out.println(e.writemyproblem());
		}
		
		
	}
	
	public synchronized void updateParkingSpace(int parkingLevel, int parkingSpace, boolean spaceToOccupied) {
		/*** to prevent changing the status of a parkingspace by multiple threads and allow concurrency ***/
		
		
		// Search for given parkingSpace within one of the parkingLevels
		// apply changes to parkingSpace
		// 1. Search parkingLevel
		//     2. Search parkingSpace in parkingLevel
		//         3. make changes to parkingSpace occupied state
		
		// - potentially throw ParkingException if status is already equal to change requested by caller
	}
	
	

	@Override
	public String toString() {
		String ls = String.format("\n\t\tParking Garage\nNumber of Levels: %d\nAddress\n_______\n%s\n", getNumLevels(), getAddress());
		for (ParkingLevel level : parkingLevels)
			ls += level;
		return ls;	
	}
	




}
