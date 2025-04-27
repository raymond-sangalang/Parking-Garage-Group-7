package modules;
import java.io.Serializable;

public class Payment implements Serializable {
	private String paymentID;
	private static int count = 0;
	private double amount;
	private PaymentMethodStatus paymentMethod;
	private boolean isPaid;
	private Ticket parkingTicket;
	
	public Payment(Ticket parkingTicket) {
		this.setPaymentID("Payment" + String.valueOf(++count));
		this.setAmount(0.0);
		this.setPaymentMethod(PaymentMethodStatus.UNDEFINED);
		this.setPaid(false);
		this.setParkingTicket(parkingTicket);
	}
	
	public void processPayment(PaymentMethodStatus paymentMethod) {
		this.setAmount(this.parkingTicket.calculateFee());
		this.setPaymentMethod(paymentMethod);
		this.setPaid(true);
	}
	
	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentMethodStatus getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethodStatus paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Ticket getParkingTicket() {
		return parkingTicket;
	}

	public void setParkingTicket(Ticket parkingTicket) {
		this.parkingTicket = parkingTicket;
	}
	
	
}
