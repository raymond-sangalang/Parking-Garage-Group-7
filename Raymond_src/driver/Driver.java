package driver;
import modules.*;

import java.io.IOException;

import exception.*;
import util.FileIO;

public class Driver {

	public static void main(String[] args) {
		
		try {
			ParkingGarage pg1 = new ParkingGarage(10, 10);
			pg1.setAddress(new Address("streetexample", "cityexample", "stateexample", "zipexample"));

			FileIO fileObj = new FileIO();
			FileIO.writeGarageObject(pg1);
			
			
			// display serialized objects location - with file in system
			System.out.println(FileIO.readGarageObject("log/cityexamplezipexample_parkingGarage.ser").getAddress());
			
			// display serialized objects location - with file NOT in system
			System.out.println(FileIO.readGarageObject("log/cityExzipexample_parkingGarage.ser").getAddress());
			
		} catch(ParkingExceptions e) {
			System.out.println(e.writemyproblem());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
