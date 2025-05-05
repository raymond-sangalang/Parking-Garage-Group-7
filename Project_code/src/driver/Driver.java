package driver;
import modules.*;
import personel.Admin;
import adapter.*;
import java.io.IOException;

import exception.*;
import util.FileIO;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			ParkingGarageSystem buildGarage = new BuildParkingGarage();
			
			
			// 1st parking garage
			Address address1 = new Address("8474 Nicolls St.", "Huntsville", "AL", "35803");
			buildGarage.BuildGarage(address1, 5, 5);

			
			//  2nd parking garage
			Address address2 = new Address("98 East St Margarets Drive", "Winter Park", "FL", "32792");
			buildGarage.BuildGarage(address2, 10, 5);

			
			
			// print 1st again
			System.out.println("1st Parking Garage location: \n"+buildGarage.getGarage(address1).getAddress()+"\n\n");
			
			
			Admin admin1 = new Admin("name1");
			Admin admin2 = new Admin("name2");
			Admin admin3 = new Admin("name3");
			Admin admin4 = new Admin("name4");
	
			
			
			buildGarage.addAdmin(admin1);
			buildGarage.addAdmin(admin3);
			buildGarage.addAdmin(admin4);
			
			
			
//			buildGarage.addParkingLevel(admin1, address1, 5);
//			buildGarage.addParkingLevel(admin2, address1, 5);
			
			buildGarage.addParkingSpace(admin1, address1, 5, 3);
			System.out.println("\tadded 3 parking spaces to level 5!!!\n\n");
//			buildGarage.addParkingSpace(admin2, address2, 5, 3);
			// test levelOut of Bounds
			buildGarage.addParkingSpace(admin1, address1, 6, 3);
			
			System.out.println("\tReached end of try-block!!!");
			
		} catch(ParkingExceptions e) {
			System.out.println(e.writemyproblem());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
//	ParkingGarage pg1 = new ParkingGarage(10, 10);
//	pg1.setAddress(new Address("streetexample", "cityexample", "stateexample", "zipexample"));
//
//	FileIO fileObj = new FileIO();
//	FileIO.writeGarageObject(pg1);
//	
//	
//	// display serialized objects location - with file in system
//	System.out.println(FileIO.readGarageObject("log/cityexamplezipexample_parkingGarage.ser").getAddress());
//	
//	// display serialized objects location - with file NOT in system
//	System.out.println(FileIO.readGarageObject("log/cityExzipexample_parkingGarage.ser").getAddress());

}
