package modules;
import java.io.Serializable;

import exception.FixModel;
import exception.ParkingExceptions;


//  Type of Payment
enum PaymentMethodStatus {
	UNDEFINED, CARD, CASH
}

public class Payment implements Serializable {
	
	private String paymentID;
	private static int count = 0;
	private double amount;
	private PaymentMethodStatus paymentMethod;
	private boolean isPaid;
	private Ticket parkingTicket;

	
	// Constructor - initialized given a parking ticket
	//               object. Sets payment type to default undefined
	//               until value is changed by setting amount paid
	public Payment(Ticket parkingTicket) throws ParkingExceptions {
		this.setPaymentID("Payment" + String.valueOf(++count));
		this.setAmount(0.0);
		this.setPaymentMethod(PaymentMethodStatus.UNDEFINED);
		this.setPaid(false);
		this.setParkingTicket(parkingTicket);
	}

	
	
	// Getters
	public String getPaymentID() {
		return paymentID;
	}
	public double getAmount() {
		return amount;
	}
	public PaymentMethodStatus getPaymentMethod() {
		return paymentMethod;
	}
	public boolean getIsPaid() {
		return isPaid;
	}
	public Ticket getParkingTicket() {
		return parkingTicket;
	}
	
	// Getters via PaymentMethodStatus
	public static PaymentMethodStatus getUndefinedPaymentStatus() {
		return PaymentMethodStatus.UNDEFINED;
	}
	public static PaymentMethodStatus getCashPaymentStatus() {
		return PaymentMethodStatus.CASH;
	}
	public static PaymentMethodStatus getCardPaymentStatus() {
		return PaymentMethodStatus.CARD;
	}
	
	
	
	// Setters
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public void setAmount(double amount) throws ParkingExceptions {
		if (FixModel.isValidInteger((int) amount))
			throw new ParkingExceptions(1);
		this.amount = amount;
	}
	public void setPaymentMethod(PaymentMethodStatus paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public void setParkingTicket(Ticket parkingTicket) {
		this.parkingTicket = parkingTicket;
	}



	public void processPayment(PaymentMethodStatus paymentMethod)  {
		
		try {
			this.setAmount(this.parkingTicket.calculateFee());
		} catch (ParkingExceptions e) {
		
			e.printStackTrace();
		}
		this.setPaymentMethod(paymentMethod);
		this.setPaid(true);
		getParkingTicket().setPaid();
	}
	
	
	public void processPayment(PaymentMethodStatus paymentMethod, double paymentAmount) {
		
		try {
			this.setAmount(paymentAmount);
		} catch (ParkingExceptions e) {
			e.printStackTrace();
		}
		
		this.setPaymentMethod(paymentMethod);
		this.setPaid(true);
		getParkingTicket().setPaid();
	}
	
	
	
	
}
