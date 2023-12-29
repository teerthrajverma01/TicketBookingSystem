package entity;

public class Booking {
	private int booking_id,customer_id,event_id,num_tickets;
	private float total_cost;
	private String booking_date;
	public Booking(int booking_id, int customer_id, int event_id, int num_tickets, float total_cost,
			String booking_date) {
		super();
		this.booking_id = booking_id;
		this.customer_id = customer_id;
		this.event_id = event_id;
		this.num_tickets = num_tickets;
		this.total_cost = total_cost;
		this.booking_date = booking_date;
	}
	public Booking() {
		super();
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getNum_tickets() {
		return num_tickets;
	}
	public void setNum_tickets(int num_tickets) {
		this.num_tickets = num_tickets;
	}
	public float getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(float total_cost) {
		this.total_cost = total_cost;
	}
	public String getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ booking_id : "+booking_id+
				"Customer_id : "+customer_id+
				"Event_id : "+event_id+
				"num_tickets : "+num_tickets+
				"total_coast : "+total_cost+
				"booking_date : "+booking_date+" ]";
	}
	
	
}